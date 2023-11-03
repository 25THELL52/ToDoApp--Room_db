package com.example.todoapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.Model.TaskDetail
import com.example.todoapp.data.repository.ToDoAppRepository
import com.example.todoapp.databinding.FragmentDetailBinding
import com.example.todoapp.databinding.ViewBottomSheetDialogBinding
import com.example.todoapp.viewModel.DetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@Suppress("INFERRED_TYPE_VARIABLE_INTO_POSSIBLE_EMPTY_INTERSECTION")
@AndroidEntryPoint
class DetailFragment : Fragment(), MyViewDetail {


    lateinit var binding: FragmentDetailBinding
    lateinit var detailViewModel: DetailViewModel
    lateinit var detailAdapter: DetailAdapter

    @Inject
    lateinit var repository: ToDoAppRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // val view = inflater.inflate(R.layout.fragment_detail, container, false)

        Log.e("message","listOf(0).jointostring result ${listOf(0).joinToString(separator = "|")}")


        binding = FragmentDetailBinding.inflate(inflater, container, false)
        detailAdapter = DetailAdapter(this)
        binding.TaskDetailsRv.adapter = detailAdapter
        binding.TaskDetailsRv.layoutManager = LinearLayoutManager(context)

        detailViewModel = ViewModelProvider(
            this, DetailViewModel.DetailViewModelFactory(repository)
        )[DetailViewModel::class.java]



        getCurrentTask()?.let {
            val taskDetailsList = detailViewModel.getTaskDetailsListFromTaskDetailsIdsList(it.taskDetailsIdsList)
            detailAdapter.setList(taskDetailsList) }


        binding.addTaskDetailFbtn.setOnClickListener {
            showBottomSheetDialog()
        }


        detailViewModel._currentTaskDetailsLiveData.observe(viewLifecycleOwner,
            Observer {
                Log.d("message", "inside detailFragment ${it.joinToString("/")}")

                Log.d("message", "datasetChanged 2")


                detailAdapter.setList(it)

                Log.d("message", "adapter list size 2: ${detailAdapter.itemCount}")

                //Log.d("message","adapter list element is: ${it[0].isBlank()}")


            })

        //detailAdapter.setList(StringListConverter.stringToStringList(it[0])) })


        (activity as MainActivity?)?.setActionBarTitle(getCurrentTask()!!.Title)






        return binding.root
    }

    private fun showBottomSheetDialog() {
        context?.let {
            BottomSheetDialog(it).apply {
                val bottomSheetBinding = ViewBottomSheetDialogBinding.inflate(layoutInflater)
                bottomSheetBinding.TitleTv.text =
                    "New task detail of task : ${getCurrentTask()?.Title}"
                bottomSheetBinding.newInfoEt.hint = "Add New Detail Here"
                bottomSheetBinding.SaveBtn.setOnClickListener {
                    Log.d("message", "currentTask: ${getCurrentTask()}")

                    if (!bottomSheetBinding.newInfoEt.text.toString().isNullOrBlank()) {
                        getCurrentTask()?.let { it2 ->


                            val taskDetail: TaskDetail =
                                TaskDetail(content = bottomSheetBinding.newInfoEt.text.toString())
                            detailViewModel.addTaskDetail(taskDetail)
                            Log.e("message","taskDetailId : ${ detailAdapter.itemCount.toString() }")
                            detailViewModel.editTask(
                                it2,
                                (taskDetail.id)
                            )
                        }
                    }
                    this.dismiss()
                }
                setContentView(bottomSheetBinding.root)
                show()
            }
        }
    }

    private fun getCurrentTask() =
        detailViewModel.getTaskWithId(arguments?.getInt("current_task_id"))

     override fun editTaskDetailCheckState(taskDetail: TaskDetail, isChecked: Boolean) {
detailViewModel.editTaskDetail(taskDetail,isChecked)
    }


}