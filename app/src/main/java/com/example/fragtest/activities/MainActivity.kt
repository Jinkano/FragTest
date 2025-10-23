package com.example.fragtest.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fragtest.R
import com.example.fragtest.adapters.ViewPagerAdapter
import com.example.fragtest.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity()
{
    /**/
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        /**/
        // 1. Crear el adaptador y asignarlo a ViewPager2
        val viewPagerAdapter = ViewPagerAdapter(this)
        binding.idVpBody.adapter = viewPagerAdapter

        // 2. Vincular TabLayout y ViewPager2 con TabLayoutMediator
        TabLayoutMediator(binding.idTlHeaderTitle, binding.idVpBody) { tab, position ->
            // Asigna el texto a cada pestaña
            tab.text = when (position) {
                0 -> "Reproductor"
                1 -> "Grupos"
                else -> null
            }
        }.attach()


    }/*End onCreate*/
}