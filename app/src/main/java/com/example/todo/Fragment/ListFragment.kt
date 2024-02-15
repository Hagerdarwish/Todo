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
import com.example.todo.database.model.Todo
import com.example.todo.database.room.TodoRoomDataBase
import com.example.todo.databinding.FragmentListBinding
import com.example.todo.databinding.TodoItemBinding
import com.example.todo.timeInMillis
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.Calendar


class ListFragment : Fragment() {

lateinit var binding: FragmentListBinding
var adapter=TodoAdabter(listOf())
    var selectedDate= CalendarDay.today()


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
        binding.calendarView.selectedDate=selectedDate
        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            selectedDate=date
            refreshData()
        }
        refreshData()
    }

    @SuppressLint("SuspiciousIndentation")
     fun refreshData() {
      var list=  TodoRoomDataBase.getInstance(requireActivity().applicationContext).todoDao().getTodoByDate(selectedDate.timeInMillis())
        adapter.update(list)

        Log.e("Listfragment","data is refreshed")
    }


}