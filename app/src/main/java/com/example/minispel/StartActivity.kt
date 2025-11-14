package com.example.minispel

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
/**
 * StartActivity allows the user to enter their player name before starting the game.
 * The updated player object is then sent back to MainActivity.
 *
 * @property inputNameSa Text input field for entering player name.
 * @property player The player object passed from MainActivity.
 */
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

        // Save name and return to MainActivity
        loginButtonSa.setOnClickListener {

            if (inputNameSa.text.isNullOrEmpty()){
                Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Update player's name
            player.name = inputNameSa.text.toString()

            /**
             * Returns the updated player object to MainActivity.
             *
             * @return RESULT_OK along with the modified Player object.
             */
            val resultIntent = Intent().apply {
                putExtra("player_updated", player)
            }
            setResult(RESULT_OK,resultIntent)
            finish()

        }

    }
}