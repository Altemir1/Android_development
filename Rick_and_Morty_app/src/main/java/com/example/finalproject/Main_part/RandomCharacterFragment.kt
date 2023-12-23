package com.example.finalproject.Main_part
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.finalproject.Main_part.Character
import com.example.finalproject.Main_part.NetworkService
import com.example.finalproject.Main_part.RickAndMortyApiService
import com.example.finalproject.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class RandomCharacterFragment : Fragment() {

    private lateinit var generateButton: ImageButton
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
        return inflater.inflate(R.layout.fragment_random_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateButton = view.findViewById(R.id.generateButton)
        resultContainer = view.findViewById(R.id.resultContainer)
        resultImageView = view.findViewById(R.id.resultImageView)
        fullNameTextView = view.findViewById(R.id.fullNameTextView)
        statusTextView = view.findViewById(R.id.statusTextView)
        speciesTextView = view.findViewById(R.id.speciesTextView)
        typeTextView = view.findViewById(R.id.typeTextView)
        genderTextView = view.findViewById(R.id.genderTextView)


        generateButton.setOnClickListener {
            // Fetch a random character and display details
            fetchRandomCharacter()
        }
    }

    private fun fetchRandomCharacter() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val retrofit = NetworkService.getRetrofitInstance()
                val service = retrofit.create(RickAndMortyApiService::class.java)

                // Fetch a random character
                val randomPage = (1..34).random() // Assuming there are 34 pages (adjust as needed)
                val response = service.getCharacters(randomPage)

                // Select a random character from the response
                val randomCharacter = response.results.random()

                // Update UI on the main thread
                withContext(Dispatchers.Main) {
                    displayCharacterDetails(randomCharacter)
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

    private fun displayCharacterDetails(character: Character) {
        // Load image using Picasso
        Picasso.get().load(character.image).into(resultImageView)

        // Set detailed information
        fullNameTextView.text = "Name: ${character.name}"
        statusTextView.text = "Status: ${character.status}"
        speciesTextView.text = "Species: ${character.species}"
        typeTextView.text = "Type: ${character.type}"
        genderTextView.text = "Gender: ${character.gender}"

        // Make the resultContainer visible
        resultContainer.visibility = View.VISIBLE
    }
}
