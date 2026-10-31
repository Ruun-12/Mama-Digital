package com.example.mamadigital
data class CartItem(val product: Product, var qty: Int)

object Cart {
    val items = mutableListOf<CartItem>()

    fun add(product: Product) {
        val existing = items.find { it.product.name == product.name }
        if (existing != null) existing.qty++ else items.add(CartItem(product, 1))
    }

    fun remove(item: CartItem) { items.remove(item) }

    fun clear() { items.clear() }

    fun count() = items.sumOf { it.qty }

    fun total() = items.sumOf { it.product.price * it.qty }

    fun summary(name: String = "", phone: String = "", location: String = "", payment: String = ""): String {
        val lines = items.joinToString("\n") {
            "${it.qty} x ${it.product.name} = KES ${it.product.price * it.qty}"
        }
        return "Hello Mama Mboga, I would like to order:\n$lines\n" +
                "Total: KES ${total()}\n\n" +
                "Name: $name\nPhone: $phone\nLocation: $location\nPayment: $payment"
    }
}