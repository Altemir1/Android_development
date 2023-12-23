package com.example.finalproject.Main_part

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.finalproject.Access_part.MainActivity
import com.example.finalproject.R
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class BottomNavigation : AppCompatActivity() {


    private val randomCharacterFragment = RandomCharacterFragment()
    private val characterListFragment  = CharacterListFragment()
    private val searchCharacterFragment = SearchCharacterFragment()

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_character_list -> showFragment(characterListFragment)
                R.id.navigation_search_character -> showFragment(searchCharacterFragment)
                R.id.navigation_random_character -> showFragment(randomCharacterFragment)
            }
            true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_navigation)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // Show the initial fragment
        showFragment(characterListFragment)
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
