package com.example.todoapp.viewModel

import android.telecom.Call.Details
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todoapp.Model.Task
import com.example.todoapp.data.repository.ToDoAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val toDoAppRepository: ToDoAppRepository) :
    ViewModel() {



    private val currentTaskDetailsLiveData = MutableLiveData<List<String>>()
    val _currentTaskDetailsLiveData: LiveData<List<String>>
        get()= currentTaskDetailsLiveData


    fun editTask(task: Task, taskDetail: String) {

        CoroutineScope(Dispatchers.IO).launch {
            toDoAppRepository.editTask(task, taskDetail)
            withContext(Dispatchers.Main) {
                currentTaskDetailsLiveData.value =getTaskWithId(task.id)?.taskDetailsList
            }
        }


    }



    fun getTaskWithId(id: Int?): Task? {

        var task: Task? = null
        val job = CoroutineScope(Dispatchers.IO).launch {
            task = toDoAppRepository.getTaskWithId(id)

        }

        runBlocking { job.join() }
        return task
    }


    class DetailViewModelFactory(private val repository: ToDoAppRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailViewModel(repository) as T
        }
    }
}