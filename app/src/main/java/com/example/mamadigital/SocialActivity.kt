package com.example.mamadigital

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SocialActivity : AppCompatActivity() {

    // Replace these with Mama Mboga's real details
    private val phoneNumber = "254725623367S"
    private val instagramUrl = "https://instagram.com/mamamboga"
    private val facebookUrl = "https://facebook.com/mamamboga"
    private val twitterUrl = "https://twitter.com/mamamboga"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social)
        title = "Contact & Social"

        findViewById<Button>(R.id.btnWhatsapp).setOnClickListener {
            openLink("https://wa.me/$phoneNumber?text=" + Uri.encode("Hello Mama Mboga, I would like to ask about your vegetables."))
        }
        findViewById<Button>(R.id.btnInstagram).setOnClickListener { openLink(instagramUrl) }
        findViewById<Button>(R.id.btnFacebook).setOnClickListener { openLink(facebookUrl) }
        findViewById<Button>(R.id.btnTwitter).setOnClickListener { openLink(twitterUrl) }

        findViewById<Button>(R.id.btnCall).setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:+$phoneNumber")))
        }

        findViewById<Button>(R.id.btnShare).setOnClickListener {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            share.putExtra(
                Intent.EXTRA_TEXT,
                "Order fresh vegetables from Mama Mboga with the Mama Digital app!"
            )
            startActivity(Intent.createChooser(share, "Share via"))
        }
    }

    private fun openLink(url: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: Exception) {
            Toast.makeText(this, "Could not open the link", Toast.LENGTH_SHORT).show()
        }
    }
}