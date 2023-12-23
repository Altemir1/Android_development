package com.example.finalproject.Main_part

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.squareup.picasso.Picasso

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var characters: List<Character> = emptyList()

    fun setCharacters(characters: List<Character>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }


    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)

        // Set OnClickListener to toggle visibility of expanded view
        holder.itemView.setOnClickListener {
            holder.toggleExpandedView()
        }
    }

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val characterImageView: ImageView = itemView.findViewById(R.id.characterImageView)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val expandedView: View = itemView.findViewById(R.id.expandedView)
        private var isExpanded: Boolean = false

        init {
            // Set OnClickListener to toggle visibility of expanded view
            itemView.setOnClickListener {
                toggleExpandedView()
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(character: Character) {
            // Load image using Picasso
            Picasso.get().load(character.image).into(characterImageView)
            nameTextView.text = character.name

            // Set visibility of basic information based on the expanded state
            characterImageView.visibility = if (isExpanded) View.GONE else View.VISIBLE
            nameTextView.visibility = if (isExpanded) View.GONE else View.VISIBLE

            // Bind detailed information to expanded view
            val statusTextView: TextView = itemView.findViewById(R.id.statusTextView)
            val speciesTextView: TextView = itemView.findViewById(R.id.speciesTextView)
            val typeTextView: TextView = itemView.findViewById(R.id.typeTextView)
            val genderTextView: TextView = itemView.findViewById(R.id.genderTextView)

            // Load image using Picasso

            // Set detailed information
            statusTextView.text = "Status: ${character.status}"
            speciesTextView.text = "Species: ${character.species}"
            typeTextView.text = "Type: ${character.type}"
            genderTextView.text = "Gender: ${character.gender}"

            // Set other detailed information TextViews
        }

        public fun toggleExpandedView() {
            // Toggle the expanded state
            isExpanded = !isExpanded

            // Toggle visibility of expanded view
            expandedView.visibility = if (isExpanded) View.VISIBLE else View.GONE
        }
    }
}
