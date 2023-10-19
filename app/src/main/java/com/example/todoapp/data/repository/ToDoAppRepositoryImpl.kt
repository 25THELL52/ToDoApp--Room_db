package com.example.todoapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.Model.Task
import com.example.todoapp.data.local.ToDoAppDao
import javax.inject.Inject

class ToDoAppRepositoryImpl @Inject constructor(private val toDoAppDao: ToDoAppDao) :
    ToDoAppRepository {
    override  fun addNewTask(task: Task) {
        toDoAppDao.addNewTask(task)
        Log.d("message","inside addNewTask()")

    }

    override  fun editTask(task: Task, taskDetail: String) {
        toDoAppDao.addNewTask(task.copy(taskDetailsList = task.taskDetailsList + taskDetail))

        Log.d("message","before adding $taskDetail : $task.taskDetailsList.joinToString()")
        val myTask = task.copy(taskDetailsList = task.taskDetailsList + taskDetail)
        Log.d("message"," after adding $taskDetail : $myTask.taskDetailsList.joinToString()")
    }

    override  fun deleteTaskWithId(id: Int) {
        toDoAppDao.deleteTaskWithId(id)
    }

    override fun getTaskWithId(id: Int?):Task {
       return toDoAppDao.getTaskWithId(id)   }

    override  fun getAllTasks(): LiveData<List<Task>> {

        return toDoAppDao.getAllTasks()
    }

    override fun getTaskDetails(task: Task):LiveData<List<String>> {

        return toDoAppDao.getTaskDetails(task.id)

    }


}