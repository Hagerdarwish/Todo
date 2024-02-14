package com.example.todo.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.R
import com.example.todo.TodoAdabter
import com.example.todo.database.room.TodoRoomDataBase
import com.example.todo.databinding.FragmentListBinding
import com.example.todo.databinding.TodoItemBinding


class ListFragment : Fragment() {

lateinit var binding: FragmentListBinding
var adapter=TodoAdabter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding=FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerview.adapter=adapter
        refreshData()
    }

    @SuppressLint("SuspiciousIndentation")
     fun refreshData() {
      var list=  TodoRoomDataBase.getInstance(requireActivity().applicationContext).todoDao().getAllTodo()
        adapter.update(list)

        Log.e("Listfragment","data is refreshed")
    }


}