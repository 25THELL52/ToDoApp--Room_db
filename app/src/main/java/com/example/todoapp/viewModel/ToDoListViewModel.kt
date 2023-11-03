package com.example.todoapp.viewModel

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
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(private val toDoAppRepository: ToDoAppRepository) :
    CommunViewModel(toDoAppRepository) {




    fun addNewTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            toDoAppRepository.addNewTask(task)
        }

    }

    fun deleteTask(task: Task) {

        CoroutineScope(Dispatchers.IO).launch {
            toDoAppRepository.deleteTaskWithId(task.id)        }


    }

    fun getAllTasks(): LiveData<List<Task>> {
        return toDoAppRepository.getAllTasks()
    }

    class ToDoListViewModelFactory (private val repository: ToDoAppRepository)

        : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ToDoListViewModel(repository) as T
        }

    }
}
