package com.example.todoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.todoapp.Model.Task
import com.example.todoapp.Model.TaskDetail

@Database(entities = [Task::class,TaskDetail::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class ToDoAppDataBase: RoomDatabase() {

   abstract  fun getDao(): ToDoAppDao
}