package com.example.mamadigital

object Lang {
    var swahili = false

    fun t(english: String, swahiliText: String): String {
        return if (swahili) swahiliText else english
    }
}