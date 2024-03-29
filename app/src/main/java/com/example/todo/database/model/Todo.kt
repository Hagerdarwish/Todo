package com.example.todo.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0 ,
    @ColumnInfo
    var title:String?,
    @ColumnInfo
    var description:String?,
    @ColumnInfo
    var date:Long?,
    @ColumnInfo
    var isDone:Boolean?,

)
