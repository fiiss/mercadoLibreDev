package com.fiiss.mercadolibredev.splash.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.fiiss.mercadolibredev.databinding.SplashMainBinding
import com.fiiss.mercadolibredev.home.view.HomeActivity

class SplashActivity : AppCompatActivity() {

    private val waitTime: Long = 3000
    private lateinit var binding: SplashMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        execute()
    }

    private fun execute() {
        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, waitTime)
    }

}