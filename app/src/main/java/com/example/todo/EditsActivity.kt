package com.example.todo

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.core.widget.addTextChangedListener

import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.todo.database.model.Todo
import com.example.todo.database.room.TodoRoomDataBase
import com.example.todo.databinding.ActivityEditsBinding
import kotlinx.coroutines.launch
import java.util.Calendar
import kotlin.properties.Delegates

 class EditsActivity : AppCompatActivity() {
    lateinit var binding:ActivityEditsBinding

     var editDate= Calendar.getInstance()
    var idList :Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditsBinding.inflate(layoutInflater)
        setContentView(binding.root)

Log.e("validate","${validate()}")

    saveBtnClick()

datePicker()



    }
     private fun updateTodoInDatabase(todo: Todo) {
         val repository = TodoRoomDataBase.getInstance(applicationContext).todoDao().updateTodo(todo)


     }
     private fun saveBtnClick(){


         binding.btnSave.setOnClickListener {
             //title update
             var title = binding.etTitle.text.toString()

             Log.e("edit", " title is $title")
             //description update
             val description = binding.etDescription.text.toString()
             Log.e("edit", " description is $description")
             // get selected id from adabter
             idList = intent.getStringExtra("id")?.toInt()
             Log.e("edit", " id is $idList")
             //clear the time from extension function
             editDate.clearTime()
             var todoModel = Todo(
                 title = title,
                 description = description,
                 isDone = false,
                 date = editDate.timeInMillis,
                 id = idList!!
             )

             updateTodoInDatabase(todoModel)

             startActivity(Intent(this,HomeActivity::class.java))




         }}

     fun datePicker() {
         binding.tvEditDate.text =
             "${editDate.get(Calendar.DAY_OF_MONTH)}/${editDate.get(Calendar.MONTH) + 1}/${
                 editDate.get(Calendar.YEAR)
             }"
         binding.tvEditDate.setOnClickListener {
             val datePicker =
                 DatePickerDialog(
                     this, object : DatePickerDialog.OnDateSetListener {
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
     fun validate():Boolean{
         var isValid=true
         if ( binding.etTitle.text.toString().isEmpty()){
             binding.etTitle.error="enter value"
             isValid=false

         }else {
             binding.etTitle.error=null
         }
         if ( binding.etDescription.text.toString().isEmpty()){
             binding.etDescription.error="enter value"
             isValid=false

         }
         else {
             binding.etDescription.error=null
         }
        return isValid
     }
}