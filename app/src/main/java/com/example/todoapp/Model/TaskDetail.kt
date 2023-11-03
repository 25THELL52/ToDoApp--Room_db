package com.example.todoapp.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName="TaskDetail")
data class TaskDetail(@PrimaryKey(autoGenerate = false) val id : String =UUID.randomUUID().toString(), @ColumnInfo(name="content")val content: String, @ColumnInfo(name="isChecked")val isChecked:Boolean = false)
