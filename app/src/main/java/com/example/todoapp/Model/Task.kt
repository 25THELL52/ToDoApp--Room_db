package com.example.todoapp.Model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tasks")
data class Task(@PrimaryKey(autoGenerate = true) val id : Int =0, @ColumnInfo(name="TITLE") val Title : String, @ColumnInfo(name="TaskDetailsIdsList") val taskDetailsIdsList : List<String> = listOf())
{}