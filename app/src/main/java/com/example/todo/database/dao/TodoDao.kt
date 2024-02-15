package com.example.todo.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todo.database.model.Todo

@Dao
interface TodoDao {
    @Insert
    fun insertTodo(todo:Todo)
    @Update
    fun updateTodo(todo:Todo)
    @Delete
    fun deleteTodo(todo:Todo)
    @Query("SELECT * FROM Todo")
    fun getAllTodo():List<Todo>
    @Query("SELECT * FROM Todo WHERE date= :date")
    fun getTodoByDate(date:Long):List<Todo>

}