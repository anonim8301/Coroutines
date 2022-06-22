package com.example.coroutines

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Started in thread: ${Thread.currentThread().name} ")
            val answer = doNetworkCall()
            withContext(Dispatchers.Main) {
                binding.tvCenter.text = answer
                Log.d(TAG, "Text changed in thread: ${Thread.currentThread().name} ")
            }
        }
        Log.d(TAG, "Hello from the Main thread: ${Thread.currentThread().name} ")
    }

    suspend fun doNetworkCall(): String {
        delay(2000L)
        return "The answer"
    }
}