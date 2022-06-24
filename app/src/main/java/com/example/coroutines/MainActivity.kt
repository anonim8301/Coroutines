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
            withTimeout(2000){
                for (i in 4..100) {
                    if (isActive){
                        Log.d(TAG, "The result for $i: ${fib(i)}")
                    }
                    else{
                        Log.d(TAG, "Loop was canceled")
                    }
                }
            }
        }
    }

    private fun fib(n: Int): Long {
        return if (n == 0) 0
        else if (n == 1) 1
        else fib(n - 1) + fib(n - 2)
    }

}