package com.example.todoapp

import android.view.View
import com.example.todoapp.Model.Task

interface MyView {

    fun setItemViewOnClickListener(itemView:  View,task: Task)
    fun setDeleteImageButtonOnClickListener(imageButton: View, task: Task)

}
