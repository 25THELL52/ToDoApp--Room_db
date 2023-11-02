package com.example.todoapp

import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.Model.Task
import com.example.todoapp.data.local.StringListConverter
import com.example.todoapp.data.repository.ToDoAppRepository
import com.example.todoapp.databinding.FragmentDetailBinding
import com.example.todoapp.databinding.ViewBottomSheetDialogBinding
import com.example.todoapp.viewModel.DetailViewModel
import com.example.todoapp.viewModel.ToDoListViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.text.Typography.greater

@Suppress("INFERRED_TYPE_VARIABLE_INTO_POSSIBLE_EMPTY_INTERSECTION")
@AndroidEntryPoint
class DetailFragment : Fragment() {


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

        binding = FragmentDetailBinding.inflate(inflater, container, false)
        detailAdapter = DetailAdapter()
        binding.TaskDetailsRv.adapter = detailAdapter
        binding.TaskDetailsRv.layoutManager = LinearLayoutManager(context)

        detailViewModel = ViewModelProvider(
            this, DetailViewModel.DetailViewModelFactory(repository)
        )[DetailViewModel::class.java]

        getCurrentTask()?.let { detailAdapter.setList(it.taskDetailsList) }


        binding.addTaskDetailFbtn.setOnClickListener {
            showBottomSheetDialog()
        }


             detailViewModel._currentTaskDetailsLiveData.observe(viewLifecycleOwner,
                Observer {
                    Log.d("message", "inside detailFragment ${it.joinToString("/")}")

                    Log.d("message","datasetChanged 2")


                        detailAdapter.setList(it)

                    Log.d("message","adapter list size 2: ${detailAdapter.itemCount}")

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
                bottomSheetBinding.TitleTv.text = "New task detail of task : ${getCurrentTask()?.Title}"
                bottomSheetBinding.newInfoEt.hint = "Add New Detail Here"
                bottomSheetBinding.SaveBtn.setOnClickListener {
                    Log.d("message","currentTask: ${getCurrentTask()}")

                    if(!bottomSheetBinding.newInfoEt.text.toString().isNullOrBlank()) {
                        getCurrentTask()?.let { it2 ->
                            detailViewModel.editTask(
                                it2,
                                bottomSheetBinding.newInfoEt.text.toString()
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

    private fun getCurrentTask()=   detailViewModel.getTaskWithId(arguments?.getInt("current_task_id"))


}