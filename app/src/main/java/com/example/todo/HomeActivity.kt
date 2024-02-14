package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.todo.Fragment.AddFabFragment
import com.example.todo.Fragment.ListFragment
import com.example.todo.Fragment.SettingFragment
import com.example.todo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    var listFragment=ListFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
       showFragment(listFragment)
        init()
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