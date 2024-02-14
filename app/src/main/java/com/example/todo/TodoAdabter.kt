package com.example.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todo.database.model.Todo
import com.example.todo.databinding.TodoItemBinding

class TodoAdabter(var todoList: List<Todo>):Adapter<TodoAdabter.MyViewHolder>(){
    class MyViewHolder(val binding: TodoItemBinding):ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        var binding:TodoItemBinding=TodoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
fun update(updatedList:List<Todo>){
    todoList=updatedList
    notifyDataSetChanged()

}
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var todoData=todoList[position]
        holder.binding.tvItemTitle.text=todoData.title
        holder.binding.tvDescription.text=todoData.description


    }
}