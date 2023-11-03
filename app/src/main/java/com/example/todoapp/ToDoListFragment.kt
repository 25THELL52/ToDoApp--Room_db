package com.example.todoapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.todoapp.Model.Task
import com.example.todoapp.Model.TaskDetail
import com.example.todoapp.data.repository.ToDoAppRepository
import com.example.todoapp.databinding.FragmentToDoListBinding
import com.example.todoapp.databinding.ViewBottomSheetDialogBinding
import com.example.todoapp.viewModel.ToDoListViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ToDoListFragment : Fragment(), MyView {

    private lateinit var binding: FragmentToDoListBinding
    private lateinit var toDoListViewModel: ToDoListViewModel
    private val toDoListAdapter = ToDoListAdapter(this)
    @Inject
    lateinit var repository: ToDoAppRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val view = inflater.inflate(R.layout.fragment_to_do_list, container, false)
        binding = FragmentToDoListBinding.inflate(inflater, container, false)
        binding.TasksListRv.layoutManager = GridLayoutManager(context, 2)
        binding.TasksListRv.adapter = toDoListAdapter

        toDoListViewModel = ViewModelProvider(
            this,
            ToDoListViewModel.ToDoListViewModelFactory(repository)
        )[ToDoListViewModel::class.java]


        binding.addTaskFbtn.setOnClickListener{

            showBottomSheetDialog()
        }

        toDoListViewModel.getAllTasks().observe(viewLifecycleOwner, Observer {
            toDoListAdapter.setList(it)
            Log.d("message","datasetChanged")
            Log.d("message","adapter list size : ${toDoListAdapter.itemCount}")


        })


        (activity as MainActivity?)?.setActionBarTitle("Tasks")

        // Inflate the layout for this fragment
        return binding.root
    }

     fun showBottomSheetDialog() {

        context?.let {
            BottomSheetDialog(it).apply {
                val bottomSheetBinding = ViewBottomSheetDialogBinding.inflate(layoutInflater)
                bottomSheetBinding.TitleTv.text = "New Task"
                bottomSheetBinding.newInfoEt.hint = "Add New Task Here"
                bottomSheetBinding.SaveBtn.setOnClickListener {

                    if(!bottomSheetBinding.newInfoEt.text.toString().isNullOrBlank()) {
                        toDoListViewModel.addNewTask(Task(Title = bottomSheetBinding.newInfoEt.text.toString()))
                        this.dismiss()
                    }
                }
                this.setContentView(bottomSheetBinding.root)
                this.show()
            }
        }
    }

    override fun setItemViewOnClickListener(itemView: View,task: Task) {

        itemView.setOnClickListener {

            val bundle = Bundle()
            bundle.putInt("current_task_id",task.id)

            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_toDoListFragment_to_detailFragment,bundle) }

        }
    }

    override fun setDeleteImageButtonOnClickListener(imageButton: View, task: Task) {
        imageButton.setOnClickListener {
            deleteTask(task)
        }
    }

    override fun getTaskDetailsListFromTaskDetailsIdsList(taskDetailsIdsList: List<String>): List<TaskDetail> {
                   return toDoListViewModel.getTaskDetailsListFromTaskDetailsIdsList(taskDetailsIdsList)
    }

    private fun deleteTask(task: Task) {
        toDoListViewModel.deleteTask(task)
    }


}
