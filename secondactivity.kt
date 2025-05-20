package com.example.intentsapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class SecondActivity : AppCompatActivity() {
    
    private lateinit var textViewReceivedMessage: TextView
    private lateinit var buttonSendResult: Button
    private lateinit var buttonBackToMain: Button
    private lateinit var textViewResultFromThird: TextView
    
    // Register activity result launcher
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val resultMessage = data?.getStringExtra("RESULT_MESSAGE") ?: "No result data"
            textViewResultFromThird.text = resultMessage
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        
        // Initialize UI components
        textViewReceivedMessage = findViewById(R.id.textViewReceivedMessage)
        buttonSendResult = findViewById(R.id.buttonSendResult)
        buttonBackToMain = findViewById(R.id.buttonBackToMain)
        textViewResultFromThird = findViewById(R.id.textViewResultFromThird)
        
        // Retrieve data from intent
        displayReceivedMessage()
        
        // Set up button click listeners
        setupClickListeners()
    }
    
    private fun displayReceivedMessage() {
        // Get the message from the intent extras
        val receivedMessage = intent.getStringExtra("MESSAGE_KEY")
        
        // Display the message or a default message if none was received
        if (!receivedMessage.isNullOrEmpty()) {
            textViewReceivedMessage.text = "Received message: $receivedMessage"
        } else {
            textViewReceivedMessage.text = "No message was provided"
        }
    }
    
    private fun setupClickListeners() {
        // Button to send result (opens ResultActivity)
        buttonSendResult.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            resultLauncher.launch(intent)
        }
        
        // Button to go back to MainActivity
        buttonBackToMain.setOnClickListener {
            finish() // Simply close this activity to return to MainActivity
        }
    }
}
