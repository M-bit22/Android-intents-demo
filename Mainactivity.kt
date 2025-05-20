package com.example.intentsapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    
    private lateinit var editTextInput: EditText
    private lateinit var buttonGoToSecondActivity: Button
    private lateinit var buttonOpenWebPage: Button
    private lateinit var buttonShareText: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Initialize UI components
        editTextInput = findViewById(R.id.editTextInput)
        buttonGoToSecondActivity = findViewById(R.id.buttonGoToSecondActivity)
        buttonOpenWebPage = findViewById(R.id.buttonOpenWebPage)
        buttonShareText = findViewById(R.id.buttonShareText)
        
        // Set up button click listeners
        setupClickListeners()
    }
    
    private fun setupClickListeners() {
        // Explicit intent to navigate to SecondActivity
        buttonGoToSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            
            // Pass data to the second activity
            val message = editTextInput.text.toString()
            if (message.isNotEmpty()) {
                intent.putExtra("MESSAGE_KEY", message)
            }
            
            startActivity(intent)
        }
        
        // Implicit intent to open a web page
        buttonOpenWebPage.setOnClickListener {
            val webpage = Uri.parse("https://www.example.com")
            val intent = Intent(Intent.ACTION_VIEW, webpage)
            
            // Verify that an app exists to handle this intent
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
        
        // Implicit intent to share text
        buttonShareText.setOnClickListener {
            val shareText = getString(R.string.share_text)
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareText)
            }
            
            // Create a chooser for the user to select which app to use
            val chooserTitle = "Share via"
            val chooser = Intent.createChooser(intent, chooserTitle)
            
            // Verify that an app exists to handle this intent
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(chooser)
            }
        }
    }
}
