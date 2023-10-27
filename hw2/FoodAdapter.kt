package com.example.lab2
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.databinding.ActivityMainBinding



abstract class FoodAdapter(private val foodList : ArrayList<Food>):RecyclerView.Adapter<FoodAdapter.FoodViewHolder>(){

    class FoodViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        private lateinit var foodList : ArrayList<Food>
        private lateinit var foodAdapter : FoodAdapter
        private lateinit var binding : ActivityMainBinding

        val foodImage : ImageView = itemView.findViewById(R.id.iv_pizza)
        val foodName : TextView = itemView.findViewById(R.id.tv_pizza_text)


    }
    override fun getItemCount() = foodList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(R.layout.pizza_item, parent,false)
        return FoodViewHolder(viewLayout)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentFood = foodList[position]
        holder.foodImage.setImageResource(currentFood.foodImage)
        holder.foodName.text = currentFood.foodName
    }

    private fun foodListItems(){
        foodList.add(Food(R.drawable.pizza1,"chicken Pizza"))
        foodList.add(Food(R.drawable.pizza2,"Beef Chicken"))
        foodList.add(Food(R.drawable.noodles1,"normal Pizza"))
        foodList.add(Food(R.drawable.noodles2,"sweet Chicken"))
    }

}



