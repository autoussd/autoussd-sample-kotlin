package com.autoussd.sample.kotlin

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
    }

    override fun onDestroy() {
        super.onDestroy()

        // TODO #2 Dispose AutoUssd SDK
    }

    @Suppress("UNUSED_PARAMETER")
    fun completeTransaction(v: View) {
        // Get references to the EditText components in the view
        val recipientNumberInput = findViewById<EditText>(R.id.numberInput)
        val amountInput = findViewById<EditText>(R.id.amountInput)
        val referenceInput = findViewById<EditText>(R.id.referenceInput)

        // Get the recipient number, amount and reference from the EditText components
        val recipientNumber = recipientNumberInput.text.toString()
        val amount = amountInput.text.toString()
        val reference = referenceInput.text.toString()

        // Check if any of the values are empty and display a toast message if so
        if (recipientNumber.isEmpty() || amount.isEmpty() || reference.isEmpty()) {
            return Toast.makeText(this, "All fields are required", Toast.LENGTH_LONG).show()
        }

        // TODO #1: Execute session with the AutoUssd SDK
    }
}