package com.autoussd.sample.kotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.autoussd.AutoUssd
import com.autoussd.models.Result

class PaymentActivity : AppCompatActivity() {
    /* Instance of the AutoUssd SDK */
    private lateinit var autoUssd: AutoUssd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        /* In the new instance of the AutoUssd SDK, define a result callback */
        autoUssd = AutoUssd(this, object : Result.Callback {
            override fun onSessionCount(count: Int) {
                TODO("Not yet implemented")
            }

            override fun onSessionResult(result: Result) {
                when (result.status) {
                    Result.Status.COMPLETED -> {
                        /* Session execution was successful, Perform success actions */
                        Toast.makeText(this@PaymentActivity, "Success!", Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        Log.d("MainActivity", result.sessionId)
                        Log.d("MainActivity", result.status.toString())
                        Log.d("MainActivity", result.description)
                        Log.d(
                            "MainActivity",
                            if (result.lastContent != null) result.lastContent!! else ""
                        )
                        /* Session execution failed. Log error and display message to user */
                        Toast.makeText(
                            this@PaymentActivity,
                            "Something went wrong",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        /* Call dispose to cleanup */
        autoUssd.dispose()
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

        /* Execute session having this id */
        autoUssd.executeSession(
            "60a53f240000000000000000",
            arrayOf(
                recipientNumber,
                recipientNumber,
                amount,
                reference
            )
        )
    }
}