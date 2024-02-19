package com.example.todo

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.daimajia.swipe.SwipeLayout
import com.example.todo.Fragment.ListFragment
import com.example.todo.database.model.Todo
import com.example.todo.database.room.TodoRoomDataBase
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



        val swipeLayout =holder.binding.swipeLayout
        swipeLayout.addSwipeListener(object  : SwipeLayout.SwipeListener{
            override fun onStartOpen(layout: SwipeLayout?) {

            }

            override fun onOpen(layout: SwipeLayout?) {
                var item=holder.itemView
                TodoRoomDataBase.getInstance(item.context).todoDao().deleteTodo(todoData)
                Log.e("swipeLayout","the index is ${holder.itemView.context} and context is ${todoData}")
                   update(todoList- todoData)
                    Log.e("todoList","the todoList is ${todoList} and context is ${todoList- todoData}")


            }

            override fun onStartClose(layout: SwipeLayout?) {

            }

            override fun onClose(layout: SwipeLayout?) {

            }

            override fun onUpdate(layout: SwipeLayout?, leftOffset: Int, topOffset: Int) {

            }

            override fun onHandRelease(layout: SwipeLayout?, xvel: Float, yvel: Float) {

            }

        })




    }



}