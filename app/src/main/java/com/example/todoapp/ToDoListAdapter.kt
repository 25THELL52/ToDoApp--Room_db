package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Model.Task
import com.example.todoapp.ToDoListAdapter.ToDoListViewHolder

class ToDoListAdapter(private val myView:MyView
) : RecyclerView.Adapter<ToDoListViewHolder>() {

     private var tasksList : List<Task> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todolist_item, parent, false)
        return ToDoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        holder.Task_Title_list_fg.text = tasksList[position].Title
        holder.Brief_task_detail.text = tasksList[position].
        taskDetailsList.joinToString(separator = "\n")
        myView.setOnClickListener(holder.itemView,tasksList[position])


    }
    override fun getItemCount(): Int {
        return tasksList.size
    }
    fun setList(tasksList : List<Task>){
        this@ToDoListAdapter.tasksList = tasksList
        notifyDataSetChanged()

    }

    inner class ToDoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        val Task_Title_list_fg = itemView.findViewById<TextView>(R.id.Task_Title_list_fg)
        val Brief_task_detail = itemView.findViewById<TextView>(R.id.Brief_task_detail)

    }
}