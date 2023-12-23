package com.example.finalproject.Access_part
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.R

class EvilMortyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evil_morty)

        Handler().postDelayed({
            finish()
        }, 10000) // 10000 milliseconds (10 seconds)
    }
}
