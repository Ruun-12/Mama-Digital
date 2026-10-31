package com.example.mamadigital

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.rvProducts)
        rv.layoutManager = GridLayoutManager(this, 2)
        adapter = ProductAdapter(ProductData.products) { product ->
            Cart.add(product)
            Toast.makeText(
                this,
                Lang.t("${product.name} added. Total: KES ${Cart.total()}",
                    "${product.name} imewekwa. Jumla: KES ${Cart.total()}"),
                Toast.LENGTH_SHORT
            ).show()
        }
        rv.adapter = adapter

        findViewById<Button>(R.id.btnViewCart).setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
        findViewById<Button>(R.id.btnSocial).setOnClickListener {
            startActivity(Intent(this, SocialActivity::class.java))
        }
        findViewById<Button>(R.id.btnRecipe).setOnClickListener {
            startActivity(Intent(this, RecipeActivity::class.java))
        }
        findViewById<Button>(R.id.btnLang).setOnClickListener {
            Lang.swahili = !Lang.swahili
            applyLanguage()
            adapter.notifyDataSetChanged()
        }

        applyLanguage()
    }

    override fun onResume() {
        super.onResume()
        applyLanguage()
    }

    private fun applyLanguage() {
        findViewById<TextView>(R.id.tvBanner).text =
            Lang.t("🥬 Fresh today at Mama Mboga!", "🥬 Mbichi leo kwa Mama Mboga!")
        findViewById<Button>(R.id.btnViewCart).text = Lang.t("View Cart", "Tazama Kikapu")
        findViewById<Button>(R.id.btnSocial).text = Lang.t("Contact & Follow Us", "Wasiliana Nasi")
        findViewById<Button>(R.id.btnRecipe).text = Lang.t("🍳 Recipe of the Day", "🍳 Mapishi ya Leo")
        findViewById<Button>(R.id.btnLang).text = Lang.t("Kiswahili", "English")
    }
}
