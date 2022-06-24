package com.example.coroutines

import android.os.Bundle
import android.util.Log
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


        val job = GlobalScope.launch(Dispatchers.Default) {
            val answer1 = async { networkCall1() }
            val answer2 = async { networkCall2() }
            Log.d(TAG, answer1.await())
            Log.d(TAG, answer2.await())
        }
    }

    suspend fun networkCall1():String{
        delay(2000L)
        return "Answer 1"
    }

    suspend fun networkCall2():String{
        delay(2000L)
        return "Answer 2"
    }
}