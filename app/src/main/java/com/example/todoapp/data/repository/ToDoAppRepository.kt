package com.example.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.Model.Task
import com.example.todoapp.Model.TaskDetail

interface ToDoAppRepository {

    fun addNewTask(task: Task)
    fun deleteTaskWithId(id: Int)
    fun getTaskWithId(id: Int?): Task
    fun getAllTasks(): LiveData<List<Task>>
    fun addTaskDetail(taskDetail: TaskDetail)


    fun editTask(task: Task, taskDetailId: String)
     fun getTaskDetailWithId(taskDetailId: String): TaskDetail
     fun editTaskDetail(taskDetail: TaskDetail, checked: Boolean)
}