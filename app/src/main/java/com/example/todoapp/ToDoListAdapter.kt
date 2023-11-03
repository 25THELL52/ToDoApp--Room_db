package com.example.todoapp

import android.graphics.drawable.Icon
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Model.Task
import com.example.todoapp.ToDoListAdapter.ToDoListViewHolder

class ToDoListAdapter(
    private val myView: MyView
) : RecyclerView.Adapter<ToDoListViewHolder>() {

    private var tasksList: List<Task> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todolist_item, parent, false)
        return ToDoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        holder.Task_Title_list_fg.text = tasksList[position].Title
        val taskDetailsIdsList = tasksList[position].taskDetailsIdsList

        if (taskDetailsIdsList.isNotEmpty()) {
            Log.e("message", "inside isNotEmpty() if")

            holder.Brief_task_detail.text =
                myView.getTaskDetailsListFromTaskDetailsIdsList(taskDetailsIdsList)
                    .joinToString(separator = "\n") {
                        it.content
                    }
        }


        myView.setItemViewOnClickListener(holder.itemView, tasksList[position])
        myView.setDeleteImageButtonOnClickListener(holder.delete_task_imb, tasksList[position])
    }


    override fun getItemCount(): Int {
        return tasksList.size
    }

    fun setList(tasksList: List<Task>) {
        this@ToDoListAdapter.tasksList = tasksList
        notifyDataSetChanged()

    }

    inner class ToDoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val Task_Title_list_fg = itemView.findViewById<TextView>(R.id.Task_Title_list_fg)
        val Brief_task_detail = itemView.findViewById<TextView>(R.id.Brief_task_detail)
        val delete_task_imb = itemView.findViewById<ImageButton>(R.id.delete_task_imb)

    }
}