package com.example.todoapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todoapp.Model.Task

@Dao
interface ToDoAppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addNewTask(newTask: Task)

    @Delete
     fun deleteTask(task: Task)

    @Query("SELECT * FROM Tasks")
     fun getAllTasks():LiveData<List<Task>>

     @Query("SELECT TaskDetailsList FROM tasks WHERE id= :id")
     fun getTaskDetails(id:Int):LiveData<List<String>>

    @Query("SELECT *  FROM  Tasks WHERE id= :id")
    fun getTaskWithId(id:Int?):Task



    @Query("DELETE  FROM  Tasks WHERE id= :id")
     fun deleteTaskWithId(id:Int)



}