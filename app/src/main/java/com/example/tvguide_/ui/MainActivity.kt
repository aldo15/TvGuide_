package com.example.tvguide_.ui

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tvguide_.R
import com.example.tvguide_.ui.modules.showdatils.ui.ShowDetailsFragment
import com.example.tvguide_.databinding.ActivityMainBinding
import com.example.tvguide_.ui.modules.data.model.ShowItem
import com.example.tvguide_.ui.modules.schedule.ui.ScheduleFragment

class MainActivity : AppCompatActivity(), IEventReturnMainCallBack {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        loadFragment(ScheduleFragment(this))
        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        setContentView(binding.root)
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun loadFragment(fragment:Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    override fun CommunicationMain(showItem: ShowItem) {
        replaceFragment(ShowDetailsFragment(showItem))
    }
}