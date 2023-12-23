// SearchCharacterFragment.kt

package com.example.finalproject.Main_part

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.finalproject.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class SearchCharacterFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var resultContainer: View

    private lateinit var resultImageView: ImageView
    private lateinit var fullNameTextView: TextView
    private lateinit var statusTextView: TextView
    private lateinit var speciesTextView: TextView
    private lateinit var typeTextView: TextView
    private lateinit var genderTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_character, container, false)

        nameEditText = view.findViewById(R.id.nameEditText)
        searchButton = view.findViewById(R.id.searchButton)
        resultContainer = view.findViewById(R.id.resultContainer)

        resultImageView = view.findViewById(R.id.resultImageView)
        fullNameTextView = view.findViewById(R.id.fullNameTextView)
        statusTextView = view.findViewById(R.id.statusTextView)
        speciesTextView = view.findViewById(R.id.speciesTextView)
        typeTextView = view.findViewById(R.id.typeTextView)
        genderTextView = view.findViewById(R.id.genderTextView)

        searchButton.setOnClickListener {
            val characterName = nameEditText.text.toString().trim()
            if (characterName.isNotEmpty()) {
                searchCharacter(characterName)
            }
        }

        return view
    }

    private fun searchCharacter(characterName: String) {
        // Use Retrofit to fetch detailed information about the character
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val retrofit = NetworkService.getRetrofitInstance()
                val service = retrofit.create(RickAndMortyApiService::class.java)
                val response = service.searchCharacter(characterName)

                withContext(Dispatchers.Main) {
                    // Display the detailed information in the resultContainer
                    displayCharacterDetails(response.results)
                }
            } catch (e: HttpException) {
                // Handle HTTP errors
                e.printStackTrace()
            } catch (e: Throwable) {
                // Handle other errors
                e.printStackTrace()
            }
        }
    }

    private fun displayCharacterDetails(characters: List<Character>) {
        if (characters.isNotEmpty()) {
            // Load image using Picasso
            Picasso.get().load(characters[0].image).into(resultImageView)

            // Set detailed information
            fullNameTextView.text = "Name: ${characters[0].name}"
            statusTextView.text = "Status: ${characters[0].status}"
            speciesTextView.text = "Species: ${characters[0].species}"
            typeTextView.text = "Type: ${characters[0].type}"
            genderTextView.text = "Gender: ${characters[0].gender}"

            // Make the resultContainer visible
            resultContainer.visibility = View.VISIBLE
        } else {
            // Handle the case where no characters were found
            // You can show a message or handle it according to your app's requirements
            resultContainer.visibility = View.GONE
        }
    }
}
