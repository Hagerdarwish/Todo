package com.example.todo

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.SwipeLayout.SwipeListener
import com.example.todo.Fragment.AddFabFragment
import com.example.todo.Fragment.ListFragment
import com.example.todo.Fragment.SettingFragment
import com.example.todo.database.model.Todo
import com.example.todo.database.room.TodoRoomDataBase
import com.example.todo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    var listFragment=ListFragment()

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

       showFragment(listFragment)
        init()
       // swipeInit()
    }

    private fun init() {
        binding.bottomBar.setOnItemSelectedListener {
            if (it.itemId == R.id.list_item) {

               showFragment(listFragment)

            }else if (it.itemId == R.id.setting_item) {
                showFragment(SettingFragment())

            }
            return@setOnItemSelectedListener true
    }
        binding.btnFab.setOnClickListener{
            val fragmentView=AddFabFragment{
                listFragment.refreshData()
            }
            fragmentView.show(supportFragmentManager,"")
        }
}
    fun showFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace( R.id.framelayout,fragment)
            .commit()

    }





}