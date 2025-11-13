package com.example.minispel

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class StartActivity : AppCompatActivity() {
    private lateinit var inputNameSa : TextInputEditText

    private lateinit var player : Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_start)

        inputNameSa = findViewById(R.id.input_name_as)
        val loginButtonSa = findViewById<Button>(R.id.login_button_as)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            player = intent.getSerializableExtra("player", Player::class.java)!!
        } else {
            player = intent.getSerializableExtra("player") as Player
        }

        inputNameSa.setText(player.name)


        loginButtonSa.setOnClickListener {

            if (inputNameSa.text.isNullOrEmpty()){
                Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            player.name = inputNameSa.text.toString()

            val resultIntent = Intent().apply {
                putExtra("player_updated", player)
            }
            setResult(RESULT_OK,resultIntent)
            finish()

        }

    }
}