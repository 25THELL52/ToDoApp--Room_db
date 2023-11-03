package com.example.todoapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.Model.Task
import com.example.todoapp.Model.TaskDetail
import com.example.todoapp.data.local.ToDoAppDao
import javax.inject.Inject

class ToDoAppRepositoryImpl @Inject constructor(private val toDoAppDao: ToDoAppDao) :
    ToDoAppRepository {
    override  fun addNewTask(task: Task) {
        toDoAppDao.addNewTask(task)
        Log.d("message","inside addNewTask()")

    }

    override  fun editTask(task: Task, taskDetailId: String) {
        toDoAppDao.addNewTask(task.copy(taskDetailsIdsList = task.taskDetailsIdsList + taskDetailId))

        Log.d("message","before adding $taskDetailId : $task.taskDetailsList.joinToString()")
        val myTask = task.copy(taskDetailsIdsList = task.taskDetailsIdsList + taskDetailId)
        Log.d("message"," after adding $taskDetailId : $myTask.taskDetailsList.joinToString()")
    }

    override fun getTaskDetailWithId(taskDetailId: String): TaskDetail {
         return   toDoAppDao.getTaskDetailWithId(taskDetailId)

    }

    override fun editTaskDetail(taskDetail: TaskDetail, isChecked: Boolean) {
        toDoAppDao.addTaskDetail(taskDetail.copy(isChecked = isChecked ))
    }

    override  fun deleteTaskWithId(id: Int) {
        toDoAppDao.deleteTaskWithId(id)
    }

    override fun getTaskWithId(id: Int?):Task {
       return toDoAppDao.getTaskWithId(id)   }

    override  fun getAllTasks(): LiveData<List<Task>> {

        return toDoAppDao.getAllTasks()
    }
    /*

    override fun getTaskDetailsIds(task: Task):LiveData<List<Int>> {

        return toDoAppDao.getTaskDetailsIds(task.id)

    }

     */

    override fun addTaskDetail(taskDetail: TaskDetail) {
    toDoAppDao.addTaskDetail(taskDetail)    }


}