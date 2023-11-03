package com.example.todoapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todoapp.Model.Task
import com.example.todoapp.Model.TaskDetail

@Dao
interface ToDoAppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addNewTask(newTask: Task)

    @Delete
     fun deleteTask(task: Task)

    @Query("SELECT * FROM Tasks")
     fun getAllTasks():LiveData<List<Task>>

     /*
     @Query("SELECT TaskDetailsIdsList FROM Tasks WHERE id= :id")
     fun getTaskDetailsIds(id:Int):LiveData<List<Int>>


      */
    @Query("SELECT *  FROM  Tasks WHERE id= :id")
    fun getTaskWithId(id:Int?):Task



    @Query("DELETE  FROM  Tasks WHERE id= :id")
     fun deleteTaskWithId(id:Int)

     //********************** TaskDetail table dao methods


    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addTaskDetail(taskDetail: TaskDetail)
    @Query("SELECT *  FROM  TaskDetail WHERE id= :id")
    fun getTaskDetailWithId(id:String?):TaskDetail

}