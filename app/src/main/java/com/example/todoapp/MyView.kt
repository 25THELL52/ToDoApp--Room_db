package com.example.todoapp

import android.view.View
import com.example.todoapp.Model.Task

interface MyView {

    fun setOnClickListener(holder: View,task: Task)

}
