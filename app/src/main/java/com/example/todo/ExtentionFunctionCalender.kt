package com.example.todo

import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.Calendar

fun CalendarDay.timeInMillis():Long{

    var calender=Calendar.getInstance()
    calender.clearTime()
    calender.set(this.year,this.month - 1,this.day)
    return calender.timeInMillis
}
fun Calendar.clearTime(){
    this.clear(Calendar.HOUR)
    this.clear(Calendar.MILLISECOND)
    this.clear(Calendar.SECOND)
    this.clear(Calendar.MINUTE)

}