package com.example.mamadigital

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val items: MutableList<CartItem>,
    private val onChange: () -> Unit
) : RecyclerView.Adapter<CartAdapter.VH>() {

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val emoji: TextView = v.findViewById(R.id.tvCartEmoji)
        val name: TextView = v.findViewById(R.id.tvCartName)
        val price: TextView = v.findViewById(R.id.tvCartPrice)
        val qty: TextView = v.findViewById(R.id.tvQty)
        val minus: Button = v.findViewById(R.id.btnMinus)
        val plus: Button = v.findViewById(R.id.btnPlus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.emoji.text = item.product.emoji
        holder.name.text = item.product.name
        holder.price.text = "KES ${item.product.price * item.qty}"
        holder.qty.text = item.qty.toString()

        holder.plus.setOnClickListener {
            item.qty++
            notifyDataSetChanged()
            onChange()
        }
        holder.minus.setOnClickListener {
            item.qty--
            if (item.qty <= 0) Cart.remove(item)
            notifyDataSetChanged()
            onChange()
        }
    }

    override fun getItemCount() = items.size
}