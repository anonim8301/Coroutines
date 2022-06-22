package com.example.coroutines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, " Before runBlocking")
        runBlocking {
            launch(Dispatchers.IO) {
                delay(2000L)
                Log.d(TAG, " IO coroutine 1")
            }
            launch(Dispatchers.IO) {
                delay(2000L)
                Log.d(TAG, " IO coroutine 2")
            }
            Log.d(TAG, " Start runBlocking")
            delay(5000L)
            Log.d(TAG, "End runBlocking")
        }
        Log.d(TAG, "After runBlocking")
    }

}