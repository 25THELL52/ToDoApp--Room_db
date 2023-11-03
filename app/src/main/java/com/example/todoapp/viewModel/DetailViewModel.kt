package com.example.todoapp.viewModel

import android.telecom.Call.Details
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todoapp.Model.Task
import com.example.todoapp.Model.TaskDetail
import com.example.todoapp.data.repository.ToDoAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val toDoAppRepository: ToDoAppRepository) :
    CommunViewModel(toDoAppRepository) {


    private val currentTaskDetailsLiveData = MutableLiveData<List<TaskDetail>>()
    val _currentTaskDetailsLiveData: LiveData<List<TaskDetail>>
        get() = currentTaskDetailsLiveData


    fun editTask(task: Task, taskDetailId: String) {


        lateinit var list: List<TaskDetail>
        val job = CoroutineScope(Dispatchers.IO).launch {
            toDoAppRepository.editTask(task, taskDetailId)
            list =
                getTaskDetailsListFromTaskDetailsIdsList(toDoAppRepository.getTaskWithId(task.id).taskDetailsIdsList)

            Log.e("message", "inside the job : ${currentTaskDetailsLiveData.value}")

        }

        runBlocking { job.join() }

        currentTaskDetailsLiveData.value = list
        Log.e("message", "the new detail list : ${currentTaskDetailsLiveData.value}")

    }


    fun getTaskWithId(id: Int?): Task? {

        var task: Task? = null
        val job = CoroutineScope(Dispatchers.IO).launch {
            task = toDoAppRepository.getTaskWithId(id)

        }

        runBlocking { job.join() }
        return task
    }

    fun addTaskDetail(taskDetail: TaskDetail) {

        viewModelScope.launch(Dispatchers.IO) {
            toDoAppRepository.addTaskDetail(taskDetail)

        }

    }

    fun editTaskDetail(taskDetail: TaskDetail, isChecked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            toDoAppRepository.editTaskDetail(taskDetail, isChecked)
        }

    }


    class DetailViewModelFactory(private val repository: ToDoAppRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailViewModel(repository) as T
        }
    }

}