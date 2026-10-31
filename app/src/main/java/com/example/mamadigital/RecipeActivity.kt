package com.example.mamadigital

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class RecipeActivity : AppCompatActivity() {

    data class Recipe(val emoji: String, val name: String, val body: String)

    private val recipes = listOf(
        Recipe(
            "🥬", "Sukuma Wiki Stir-fry",
            "Ingredients:\nSukuma wiki, onions, tomatoes, salt, cooking oil\n\n" +
                    "Steps:\n1. Fry chopped onions until golden.\n2. Add tomatoes and cook until soft.\n" +
                    "3. Add sliced sukuma wiki and a pinch of salt.\n4. Cook for 5 minutes and serve with ugali."
        ),
        Recipe(
            "🍅", "Fresh Kachumbari",
            "Ingredients:\nTomatoes, onions, coriander, lemon, salt\n\n" +
                    "Steps:\n1. Slice tomatoes and onions thinly.\n2. Mix with chopped coriander.\n" +
                    "3. Add lemon juice and salt.\n4. Serve fresh with nyama choma or pilau."
        ),
        Recipe(
            "🥚", "Spinach Omelette",
            "Ingredients:\nEggs, spinach, onions, salt, oil\n\n" +
                    "Steps:\n1. Fry onions and spinach for 2 minutes.\n2. Beat the eggs with salt.\n" +
                    "3. Pour eggs over the spinach.\n4. Cook until firm and serve with bread."
        ),
        Recipe(
            "🥕", "Carrot and Tomato Soup",
            "Ingredients:\nCarrots, tomatoes, onions, water, salt\n\n" +
                    "Steps:\n1. Boil carrots, tomatoes and onions in water for 20 minutes.\n" +
                    "2. Blend until smooth.\n3. Add salt and heat for 2 more minutes.\n4. Serve warm."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        title = "Recipe of the Day"

        val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val recipe = recipes[day % recipes.size]

        findViewById<TextView>(R.id.tvRecipeEmoji).text = recipe.emoji
        findViewById<TextView>(R.id.tvRecipeName).text = recipe.name
        findViewById<TextView>(R.id.tvRecipeBody).text = recipe.body
    }
}