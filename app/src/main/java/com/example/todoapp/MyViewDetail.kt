package com.example.todoapp

import com.example.todoapp.Model.TaskDetail

interface MyViewDetail {
    abstract fun editTaskDetailCheckState(taskDetail: TaskDetail, isChecked: Boolean)
}