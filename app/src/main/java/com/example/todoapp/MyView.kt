package com.example.todoapp

import android.view.View
import com.example.todoapp.Model.Task
import com.example.todoapp.Model.TaskDetail

interface MyView {

    fun setItemViewOnClickListener(itemView:  View,task: Task)
    fun setDeleteImageButtonOnClickListener(imageButton: View, task: Task)
    abstract fun getTaskDetailsListFromTaskDetailsIdsList(taskDetailsIdsList: List<String>): List<TaskDetail>

}
