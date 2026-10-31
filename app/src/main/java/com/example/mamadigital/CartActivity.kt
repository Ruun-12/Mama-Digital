package com.example.mamadigital

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        title = Lang.t("Your Cart", "Kikapu Chako")

        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val rv = findViewById<RecyclerView>(R.id.rvCart)

        fun updateTotal() {
            tvTotal.text = if (Cart.items.isEmpty())
                Lang.t("Your cart is empty", "Kikapu chako ni tupu")
            else Lang.t("Total: KES ${Cart.total()}", "Jumla: KES ${Cart.total()}")
        }

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = CartAdapter(Cart.items) { updateTotal() }
        updateTotal()

        val btnCheckout = findViewById<Button>(R.id.btnCheckout)
        btnCheckout.text = Lang.t("Checkout", "Lipa")
        btnCheckout.setOnClickListener {
            if (Cart.items.isEmpty()) {
                Toast.makeText(
                    this,
                    Lang.t("Your cart is empty", "Kikapu chako ni tupu"),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                startActivity(Intent(this, CheckoutActivity::class.java))
            }
        }
    }
}