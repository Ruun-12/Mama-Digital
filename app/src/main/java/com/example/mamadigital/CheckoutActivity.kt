package com.example.mamadigital

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.RadioButton

class CheckoutActivity : AppCompatActivity() {

    // Mama Mboga's WhatsApp number: 254 then the number without the first 0
    private val mamaNumber = "254712345678"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        title = Lang.t("Checkout", "Lipa")
        findViewById<EditText>(R.id.etName).hint = Lang.t("Your name", "Jina lako")
        findViewById<EditText>(R.id.etPhone).hint = Lang.t("Phone number", "Namba ya simu")
        findViewById<EditText>(R.id.etLocation).hint =
            Lang.t("Delivery location (estate, house number)", "Mahali pa kupelekewa (mtaa, nyumba)")
        findViewById<Button>(R.id.btnSend).text =
            Lang.t("Send order via WhatsApp", "Tuma oda kupitia WhatsApp")
        findViewById<RadioButton>(R.id.rbCash).text = Lang.t("Cash on delivery", "Pesa taslimu")

        findViewById<TextView>(R.id.tvOrderTotal).text =
            Lang.t("Total: KES ${Cart.total()}", "Jumla: KES ${Cart.total()}")

        findViewById<Button>(R.id.btnSend).setOnClickListener {
            val name = findViewById<EditText>(R.id.etName).text.toString().trim()
            val phone = findViewById<EditText>(R.id.etPhone).text.toString().trim()
            val location = findViewById<EditText>(R.id.etLocation).text.toString().trim()

            if (name.isEmpty() || phone.isEmpty() || location.isEmpty()) {
                Toast.makeText(this, "Please fill in all the details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val rg = findViewById<RadioGroup>(R.id.rgPayment)
            val payment = if (rg.checkedRadioButtonId == R.id.rbMpesa) "M-Pesa" else "Cash on delivery"

            val message = Cart.summary(name, phone, location, payment)
            val url = "https://wa.me/$mamaNumber?text=" + Uri.encode(message)

            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            } catch (e: Exception) {
                Toast.makeText(this, "WhatsApp is not available on this device", Toast.LENGTH_LONG).show()
            }
        }
    }
}