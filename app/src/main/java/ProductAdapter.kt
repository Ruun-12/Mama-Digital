package com.example.mamadigital

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val items: List<Product>,
    private val onAdd: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.VH>() {

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val emoji: TextView = v.findViewById(R.id.tvEmoji)
        val name: TextView = v.findViewById(R.id.tvName)
        val price: TextView = v.findViewById(R.id.tvPrice)
        val add: Button = v.findViewById(R.id.btnAdd)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val p = items[position]
        holder.emoji.text = p.emoji
        holder.name.text = p.name
        holder.price.text = "KES ${p.price} ${p.unit}"
        holder.add.text = Lang.t("Add to cart", "Weka kikapuni")
        holder.add.setOnClickListener { onAdd(p) }
    }

    override fun getItemCount() = items.size
}