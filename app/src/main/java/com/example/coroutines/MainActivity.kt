package com.example.coroutines

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.coroutines.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


data class Person(
    val name: String = "",
    val age: Int = -1,
)

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"

    override fun onStart() {
        super.onStart()
        FirebaseApp.initializeApp(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tutorialDocument = Firebase.firestore
            .collection("coroutines")
            .document("tutorial")
        val collectionPath = Firebase.firestore
            .collection("coroutines")

        val peter = Person("Peter", 29)
        val robert = Person("Robert", 20)
        lifecycleScope.launch(Dispatchers.IO) {
            collectionPath.document("AceCard").set(robert).await()
            tutorialDocument.set(peter).await()
            val person = tutorialDocument.get().await().toObject(Person::class.java)
            withContext(Dispatchers.Main) {
                binding.tvMain.text = "Name: ${person?.name} , Age: ${person?.age}"
            }
        }
    }
}