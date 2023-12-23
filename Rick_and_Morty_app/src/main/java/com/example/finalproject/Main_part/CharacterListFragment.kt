package com.example.finalproject.Main_part

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class CharacterListFragment : Fragment() {

    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character_list, container, false)

        // Initialize RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        characterAdapter = CharacterAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = characterAdapter

        // Fetch characters from the API and update the adapter
        lifecycleScope.launch {
            fetchCharacters()
        }

        return view
    }

    private suspend fun fetchCharacters() {
        val retrofit = NetworkService.getRetrofitInstance()
        val service = retrofit.create(RickAndMortyApiService::class.java)

        try {
            // Fetch characters from the first 3 pages
            val characters = mutableListOf<Character>()
            for (page in 1..2) {
                val pageResponse = service.getCharacters(page)
                val results = pageResponse.results ?: emptyList() // Handle null results
                characters.addAll(results)
            }

            // Log characters for debugging
            Log.d("CharacterListFragment", "Fetched characters: $characters")

            // Update the adapter with characters
            withContext(Dispatchers.Main) {
                characterAdapter.setCharacters(characters)
            }
        } catch (e: Exception) {
            // Log the error for debugging
            Log.e("CharacterListFragment", "Error fetching characters: ${e.message}")

        }
    }
}
