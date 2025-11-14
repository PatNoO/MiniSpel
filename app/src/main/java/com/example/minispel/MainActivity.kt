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
    private lateinit var winLoseInfoTextMa : TextView
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
            showWinLose()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, StartActivity::class.java)
        intent.putExtra("player", player)
        startLauncher.launch(intent)
        onPause()

        spinnerMa = findViewById(R.id.spinnerAm)
        headTextMa = findViewById(R.id.headTextAm)
        winLoseInfoTextMa = findViewById(R.id.win_lose_info_text_am)

        showGreeting()
    }

    //----End of onCreate---//

    fun showWinLose () {
        winLoseInfoTextMa.text = getString(R.string.total_wins_loses, player?.wins, player?.loses)
    }

    fun showGreeting () {
        headTextMa.text = getString(R.string.greeting, player?.name)
    }

    override fun onResume() {
        super.onResume()
        spinner()
    }


    private fun spinner() {
        val categories =
            arrayOf(
                "Category",
                "Addition (+)",
                "Subtration (-)",
                "Multiplikation (*)",
                "division (/)",
                "ScoreBoard"
            )
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerMa.adapter = spinnerAdapter

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
            0 -> {}
            1 -> {
                val intent = Intent(this, AddActivity::class.java)
                intent.putExtra("player", player)
                startLauncher.launch(intent)
            }

            2 -> {
                val intent = Intent(this, SubActivity::class.java)
                intent.putExtra("player", player)
                startLauncher.launch(intent)
            }

            3 -> {
                val intent = Intent(this, MultiActivity::class.java)
                intent.putExtra("player", player)
                startLauncher.launch(intent)
            }

            4 -> {
                val intent = Intent(this, DivActivity::class.java)
                intent.putExtra("player", player)
                startLauncher.launch(intent)
            }

            5 -> {
                val intent = Intent(this, ScoreboardActivity::class.java)
                intent.putExtra("player", player)
                startLauncher.launch(intent)
            }
        }

    }

}