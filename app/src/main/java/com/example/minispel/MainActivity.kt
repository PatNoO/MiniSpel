package com.example.minispel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var player: Player? = Player ("Default",0,0)
    private lateinit var spinnerMa: Spinner


    private lateinit var headTextMa: TextView

    private val startLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
        if (result.resultCode == RESULT_OK) {

            val playerUpdated = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getSerializableExtra("player_updated", Player::class.java)
            }else {
                result.data?.getSerializableExtra("player_updated") as Player
            }

            player = playerUpdated
            showGreeting()
        }
    }

//    private var

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, StartActivity::class.java)
        intent.putExtra("player", player)
        startLauncher.launch(intent)
        onPause()

        spinnerMa = findViewById(R.id.spinnerAm)
        headTextMa = findViewById(R.id.headTextAm)

        showGreeting()
    }

    //----End of onCreate---//

    fun showGreeting () {
        headTextMa.text = getString(R.string.greeting, player?.name)
    }

    override fun onResume() {
        super.onResume()
        spinner()

//        val sharedPrefMa = getSharedPreferences("addition_score", MODE_PRIVATE)
//        wins = sharedPrefMa.getInt("add_wins", 0)
//        loses = sharedPrefMa.getInt("add_loses", 0)

    }

    fun resetSharedPref() {
        val sharedPrefMa = getSharedPreferences("math_score", MODE_PRIVATE)

//        wins = 0
//        loses = 0
        sharedPrefMa.edit().apply {
            putInt("wins", 0)
            putInt("loses", 0)
            apply()
        }

        Toast.makeText(this, "ScoreBoard is clear", Toast.LENGTH_SHORT).show()
    }

    private fun spinner() {
        val categories =
            arrayOf(
                "Category",
                "Addition (+)",
                "Subtration (-)",
                "Multiplikation (*)",
                "division (/)"
            )
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerMa.adapter = spinnerAdapter
        spinnerMa.dropDownVerticalOffset = spinnerMa.height

        spinnerMa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long


            ) {
                setNewQuestion(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setNewQuestion(position: Int) {

        when (position) {
            0 -> {

            }

            1 -> {
//                Toast.makeText(this, "Addition (+)", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AddActivity::class.java)
                startActivity(intent)
            }

            2 -> {
//                Toast.makeText(this, "Addition (+)", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SubActivity::class.java)
                startActivity(intent)
            }

            3 -> {
//                Toast.makeText(this, "Addition (+)", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MultiActivity::class.java)
                startActivity(intent)
            }

            4 -> {
//                Toast.makeText(this, "Addition (+)", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DivActivity::class.java)
                startActivity(intent)
//
            }

        }

    }
/*
var addWins = 0
    var addLoses = 0

    val subWin = 0
    val subLoses = 0

    val multiWin = 0
    val multiLoses = 0

    val divWin = 0
    val divLoses = 0
 */

}