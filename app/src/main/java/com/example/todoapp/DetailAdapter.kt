package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.DetailAdapter.DetailViewHolder
import com.example.todoapp.Model.Task

class DetailAdapter : RecyclerView.Adapter<DetailViewHolder>() {

    private var taskDetailsList:List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_list_item, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {

        holder.TaskDetailtv.text = taskDetailsList[position]

    }
    override fun getItemCount(): Int {
        return taskDetailsList.size
    }

    fun setList(taskDetailList: List<String>) {
        this@DetailAdapter.taskDetailsList = taskDetailList
        notifyDataSetChanged()
    }

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var TaskDetailtv = itemView.findViewById<TextView>(R.id.TaskDetailtv)

    }
}