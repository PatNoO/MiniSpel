package com.example.minispel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

private lateinit var inputNameSa : TextInputEditText

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_start)

        inputNameSa = findViewById(R.id.input_name_as)
        val loginButtonSa = findViewById<Button>(R.id.login_button_as)

        loginButtonSa.setOnClickListener {
            finish()

        }

    }
}