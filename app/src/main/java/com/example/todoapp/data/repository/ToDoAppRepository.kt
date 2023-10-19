package com.example.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.Model.Task

interface ToDoAppRepository {

     fun addNewTask(task: Task)
     fun editTask(task: Task,taskDetail:String)
     fun deleteTaskWithId(id:Int)
    fun getTaskWithId(id:Int?): Task
    fun getAllTasks(): LiveData<List<Task>>
     fun getTaskDetails(task: Task): LiveData<List<String>>


}