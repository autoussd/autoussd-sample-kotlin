package com.autoussd.sample.kotlin

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.autoussd.AutoUssd
import com.autoussd.models.Result

class MainActivity : AppCompatActivity() {
    /* Instance of the AutoUssd SDK */
    private lateinit var autoUssd: AutoUssd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* In the new instance of the AutoUssd SDK, define a result callback */
        autoUssd = AutoUssd(this) {
            when (it.status) {
                Result.Status.COMPLETED -> {
                    /* Session execution was successful, Perform success actions */
                    Toast.makeText(this@MainActivity, "Success!", Toast.LENGTH_LONG).show()
                }
                else -> {
                    /* Session execution failed. Log error and display message to user */
                    Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        /* Call dispose to cleanup */
        autoUssd.dispose()
    }

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

        /* Execute session having this id */
        autoUssd.executeSession(
            "81c9bc38-173e-4b14-aee1-a2d1749dfee0",
            arrayOf(
                recipientNumber,
                amount,
                reference
            )
        )
    }
}