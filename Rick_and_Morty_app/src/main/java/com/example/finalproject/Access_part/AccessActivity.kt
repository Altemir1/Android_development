package com.example.finalproject.Access_part
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.Main_part.BottomNavigation
import com.example.finalproject.R
class AccessActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_access)

        // Display a message and a button to navigate back to MainActivity
        val userInput = intent.getStringExtra("userInput")
        val messageTextView: TextView = findViewById(R.id.accessText)
        val proceedButton: Button = findViewById(R.id.procceedButton)

        messageTextView.text = "You are welcome $userInput! To proceed to the Citadel, press the button."

        proceedButton.setOnClickListener {
            // Show loading page before navigating to BottomNavigation
            showLoadingPage()

            // Simulate a delay (you can replace this with your actual loading logic)
            Handler(Looper.getMainLooper()).postDelayed({
                navigateToBottomNavigation()
            }, 2000) // Simulated delay of 2 seconds
        }
    }

    private fun showLoadingPage() {
        // Display your loading page activity
        val loadingIntent = Intent(this, LoadingActivity::class.java)
        startActivity(loadingIntent)
    }

    private fun navigateToBottomNavigation() {
        // Navigate to BottomNavigation after the loading delay
        val intent = Intent(this, BottomNavigation::class.java)
        startActivity(intent)
        finish()
    }
}
