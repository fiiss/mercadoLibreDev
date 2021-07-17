package com.fiiss.mercadolibredev.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fiiss.mercadolibredev.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setSupportActionBar(binding.toolbar)
        setContentView(view)
    }

}