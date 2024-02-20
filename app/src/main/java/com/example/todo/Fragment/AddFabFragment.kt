package com.example.todo.Fragment

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.annotation.NonNull
import androidx.core.widget.addTextChangedListener
import com.example.todo.R
import com.example.todo.clearTime
import com.example.todo.database.model.Todo
import com.example.todo.database.room.TodoRoomDataBase
import com.example.todo.databinding.FragmentAddFabBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar


class AddFabFragment(private var updateBtn :() -> Unit) : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddFabBinding
    var selectedDate=Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentAddFabBinding.inflate(inflater,container,false)
        binding.btnAdd.setOnClickListener {
            if (validate()){
                var title=binding.etTitle.text.toString()
                var discription=binding.etDescription.text.toString()
                selectedDate.clearTime()
                Log.e("fab","selectedDate${selectedDate.timeInMillis}")
                var todoModel= Todo(title=title, description = discription, isDone = false, date = selectedDate.timeInMillis)
                TodoRoomDataBase.getInstance(requireActivity().applicationContext).todoDao().insertTodo(todoModel)

                dismiss()
                updateBtn.invoke()


            }
        }
        binding.etTitle.addTextChangedListener {
            validate()
        }
        binding.etDescription.addTextChangedListener {
            validate()
        }
        binding.tvDate.text="${selectedDate.get(Calendar.DAY_OF_MONTH)}/${selectedDate.get(Calendar.MONTH)+1}/${selectedDate.get(Calendar.YEAR)}"
        binding.tvDate.setOnClickListener{
            val datePicker=DatePickerDialog(requireActivity(),object :OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    selectedDate.set(Calendar.YEAR,year)
                    selectedDate.set(Calendar.MONTH,month)
                    selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                    binding.tvDate.text="${dayOfMonth}/${month+1}/${year}"

                }
            },selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH))
            //to day time in general not selected date due to i determine it
            datePicker.datePicker.minDate=Calendar.getInstance().timeInMillis

        datePicker.show()


        }


        return binding.root
    }

    //private fun DatePickerDialog(requireActivity: FragmentActivity, onDateSetListener: DatePickerDialog.OnDateSetListener): DatePickerDialog {



    fun validate():Boolean {
    var isValid:Boolean=true
    if (binding.etTitle.text.toString().isEmpty()) {
        binding.textInputLayoutTitle.error="please enter title"
        isValid=false

    }else{
        binding.textInputLayoutTitle.error=null
    }
    if (binding.textInputLayoutDescription.editText!!.text.toString().isEmpty()) {
        binding.textInputLayoutDescription.error="please enter description"
        isValid=false

    }else{
        binding.textInputLayoutDescription.error=null
    }
   return isValid

}
}