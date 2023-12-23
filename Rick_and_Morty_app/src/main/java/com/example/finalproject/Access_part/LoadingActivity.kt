package com.example.finalproject.Access_part
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.R

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        val portalImageView: ImageView = findViewById(R.id.portalImageView)

        // Create a rotation animation
        val rotateAnimation = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotateAnimation.duration = 2000 // Adjust the duration as needed
        rotateAnimation.interpolator = LinearInterpolator()
        rotateAnimation.repeatCount = Animation.INFINITE // Infinite rotation

        // Start the rotation animation
        portalImageView.startAnimation(rotateAnimation)

        // Simulate a loading delay and then finish the activity
        Handler().postDelayed({
            finish()
        }, 5000) // 5000 milliseconds (5 seconds)
    }
}
