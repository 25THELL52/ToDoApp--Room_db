package com.example.todoapp

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.DetailAdapter.DetailViewHolder
import com.example.todoapp.Model.TaskDetail

class DetailAdapter(private val myViewDetail: MyViewDetail) : RecyclerView.Adapter<DetailViewHolder>() {

    private var taskDetailsList:List<TaskDetail> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_list_item, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {

        Log.e("message","isTaskDetailNull ?: ${taskDetailsList.isNullOrEmpty()}")
        Log.e("message",": $taskDetailsList")

        if(!taskDetailsList.isNullOrEmpty()){
        holder.TaskDetailtv.text = taskDetailsList[position].content
        holder.checkTaskDetail.isChecked = taskDetailsList[position].isChecked
        holder.checkTaskDetail.setOnClickListener { myViewDetail.editTaskDetailCheckState(taskDetailsList[position],holder.checkTaskDetail.isChecked)


        }

    }}
    override fun getItemCount(): Int {
        return taskDetailsList.size
    }

    fun setList(taskDetailList: List<TaskDetail>) {
        this@DetailAdapter.taskDetailsList = taskDetailList
        notifyDataSetChanged()
    }

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var TaskDetailtv = itemView.findViewById<TextView>(R.id.TaskDetailtv)
        var checkTaskDetail = itemView.findViewById<CheckBox>(R.id.check_task_detail)

    }
}