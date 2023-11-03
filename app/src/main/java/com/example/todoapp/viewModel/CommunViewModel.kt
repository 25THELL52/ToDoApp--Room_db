package com.example.todoapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.todoapp.Model.TaskDetail
import com.example.todoapp.data.repository.ToDoAppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


 abstract class CommunViewModel (private val toDoAppRepository: ToDoAppRepository) : ViewModel()  {

    fun getTaskDetailsListFromTaskDetailsIdsList(taskDetailsIdsList: List<String>?): List<TaskDetail> {
        val taskDetailsList: MutableList<TaskDetail> = mutableListOf()

        val job = CoroutineScope(Dispatchers.IO).launch {

            if (!taskDetailsIdsList.isNullOrEmpty()) {
                for (taskDetailId in taskDetailsIdsList) {
                    Log.e("message", "inside for conversion method the detail id: $taskDetailId")

                    taskDetailsList.add(
                        toDoAppRepository.getTaskDetailWithId(taskDetailId)
                    )
                }
            }

        }

        runBlocking { job.join() }
        Log.e("message", "the list after conversion : $taskDetailsList")

        return taskDetailsList
    }


 }