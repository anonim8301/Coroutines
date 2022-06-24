package com.example.coroutines

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMain.setOnClickListener {
            lifecycleScope.launch {
                while (true) {
                    delay(1000)
                    Log.d(TAG, "Still running...")
                }
            }
            GlobalScope.launch {
                delay(5000)
                Intent(this@MainActivity, Second::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }
}