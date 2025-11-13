package com.example.minispel

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreboardActivity : AppCompatActivity() {

    private lateinit var scoreTextSbA : TextView

    private var addWins = 0
    private var addLoses = 0

    private val subWin = 0
    private val subLoses = 0

    private val multiWin = 0
    private val multiLoses = 0

    private val divWin = 0
    private val divLoses = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_scoreboard)

        scoreTextSbA = findViewById(R.id.add_score_text_asb)

        getAddScores()

    }

    fun getAddScores (){

        val sharedPref = getSharedPreferences("addition_score", MODE_PRIVATE)
        addWins = sharedPref.getInt("add_wins", 0)
        addLoses = sharedPref.getInt("add_loses", 0)

        scoreTextSbA.text = "Addition (+) \n Wins : $addWins \n Loses : $addLoses"
    }
}