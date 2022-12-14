package com.example.phonetask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phonetask.databinding.ActivityMainBinding
import com.example.phonetask.presenter.main_screen.MainScreenFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.place_holder, MainScreenFragment())
            .commit()
    }

}