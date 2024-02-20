package com.example.todo.Fragment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.todo.R
import com.example.todo.TodoAdabter
import com.example.todo.database.model.Todo
import com.example.todo.database.room.TodoRoomDataBase
import com.example.todo.databinding.FragmentEditsBinding
import java.util.Calendar
import kotlin.properties.Delegates


class EditsFragment : Fragment(),TodoAdabter.EditBtnClicked {
    lateinit var binding: FragmentEditsBinding
    var editDate= Calendar.getInstance()
     var listId: Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentEditsBinding.inflate(inflater,container, false)
        // Inflate the layout for this fragment
        binding.btnSave.setOnClickListener {
        var title=binding.etTitle.text.toString()
        var description=binding.etDescription.text.toString()

            var todoModel= Todo(title=title, description = description, isDone = false, date = editDate.timeInMillis, id = listId)

            TodoRoomDataBase.getInstance(requireActivity().applicationContext).todoDao().updateTodo(todoModel)
        }
        return binding.root
    }

fun datePicker() {
    binding.tvEditDate.text =
        "${editDate.get(Calendar.DAY_OF_MONTH)}/${editDate.get(Calendar.MONTH) + 1}/${
            editDate.get(Calendar.YEAR)
        }"
    binding.tvEditDate.setOnClickListener {
        val datePicker =
            DatePickerDialog(
                requireActivity(), object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(
                        view: DatePicker?,
                        year: Int,
                        month: Int,
                        dayOfMonth: Int
                    ) {
                        editDate.set(Calendar.YEAR, year)
                        editDate.set(Calendar.MONTH, month)
                        editDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        binding.tvEditDate.text = "${dayOfMonth}/${month + 1}/${year}"

                    }
                }, editDate.get(Calendar.YEAR),
                editDate.get(Calendar.MONTH),
                editDate.get(Calendar.DAY_OF_MONTH)
            )
        //to day time in general not selected date due to i determine it
        datePicker.datePicker.minDate = Calendar.getInstance().timeInMillis

        datePicker.show()
    }
}

    override fun clicked(id: Int) {
        listId=id
    }
}