package com.example.intentsapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ResultActivity : AppCompatActivity() {
    
    private lateinit var editTextResultMessage: EditText
    private lateinit var buttonSendResultBack: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        
        // Initialize UI components
        editTextResultMessage = findViewById(R.id.editTextResultMessage)
        buttonSendResultBack = findViewById(R.id.buttonSendResultBack)
        
        // Set up button click listener
        setupClickListener()
    }
    
    private fun setupClickListener() {
        buttonSendResultBack.setOnClickListener {
            // Get the result message from EditText
            val resultMessage = editTextResultMessage.text.toString()
            
            // Create an intent to return data to the calling activity
            val resultIntent = Intent().apply {
                putExtra("RESULT_MESSAGE", 
                    if (resultMessage.isNotEmpty()) resultMessage else "No message entered")
            }
            
            // Set the result and finish this activity
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
