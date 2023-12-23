package com.example.finalproject.Access_part

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.R


class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var submitButton: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        submitButton = findViewById<Button>(R.id.submitButton)

        submitButton.setOnClickListener(View.OnClickListener {
            val userInput = editText.getText().toString().trim { it <= ' ' }

            // Simulate a loading page for 5 seconds
            showLoadingPage()

            // Check the input data and navigate to the appropriate decisive page
            checkAndNavigate(userInput)
        })
    }

    private fun showLoadingPage() {
        // Display your loading page activity
        val loadingIntent = Intent(this, LoadingActivity::class.java)
        startActivity(loadingIntent)
    }

    private fun checkAndNavigate(userInput: String) {
        // Simulate a delay before navigating to the decisive page
        val handler = Handler()
        handler.postDelayed({ // Check the input data and navigate to the appropriate decisive page
            if ("Evil Morty".equals(userInput, ignoreCase = true)) {
                openEvilMortyPage()
            } else {
                openAccessPage(userInput)
            }
        }, 5000) // 5000 milliseconds (5 seconds)
    }

    private fun openEvilMortyPage() {
        val evilMortyIntent = Intent(this, EvilMortyActivity::class.java)
        startActivity(evilMortyIntent)
        finish() // Finish the current activity to prevent going back to it
    }

    private fun openAccessPage(userInput: String) {
        val accessIntent = Intent(this, AccessActivity::class.java)
        accessIntent.putExtra("userInput", userInput)
        startActivity(accessIntent)
        finish() // Finish the current activity to prevent going back to it
    }
}
