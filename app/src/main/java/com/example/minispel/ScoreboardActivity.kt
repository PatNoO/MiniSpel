package com.example.minispel

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ScoreboardActivity : AppCompatActivity() {

    private lateinit var addScoreTextSbA : TextView
    private lateinit var subScoreTextSbA : TextView
    private lateinit var multiScoreTextSbA : TextView
    private lateinit var divScoreTextSbA : TextView

    private var addWins = 0
    private var addLoses = 0
    private var subWins = 0
    private var subLoses = 0
    private var multiWins = 0
    private var multiLoses = 0
    private var divWins = 0
    private var divLoses = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_scoreboard)

        addScoreTextSbA = findViewById(R.id.add_score_text_asb)
        subScoreTextSbA = findViewById(R.id.sub_score_text_asb)
        multiScoreTextSbA = findViewById(R.id.multi_score_text_asb)
        divScoreTextSbA = findViewById(R.id.div_score_text_asb)
        val resetScoreButtonSbA = findViewById<Button>(R.id.reset_score_button_asb)
        val backScoreButtonSbA = findViewById<Button>(R.id.back_score_button_asb)

        getAddScores()
        getSubScores()
        getMultiScores()
        getDivScores()

        resetScoreButtonSbA.setOnClickListener {

            resetScoreboardPref()
        }

        backScoreButtonSbA.setOnClickListener {
            finish()
        }

    }
    fun resetScoreboardPref() {
        val sharedPrefMa = getSharedPreferences("addition_score", MODE_PRIVATE)

        addWins = 0
        addLoses = 0
        sharedPrefMa.edit().apply {
            putInt("add_wins", 0)
            putInt("add_loses", 0)
            apply()
        }

        Toast.makeText(this, "ScoreBoard is clear", Toast.LENGTH_SHORT).show()
    }
    fun getAddScores (){
        val sharedPref = getSharedPreferences("addition_score", MODE_PRIVATE)
        addWins = sharedPref.getInt("add_wins", 0)
        addLoses = sharedPref.getInt("add_loses", 0)

        addScoreTextSbA.text = "Addition (+) \n Wins : $addWins \n Loses : $addLoses"
    }
    fun getSubScores (){
        val sharedPref = getSharedPreferences("subtraction_score", MODE_PRIVATE)
        subWins = sharedPref.getInt("sub_wins", 0)
        subLoses = sharedPref.getInt("sub_loses", 0)

        subScoreTextSbA.text = "Subtraction (-) \n Wins : $subWins \n Loses : $subLoses"
    }

    fun getMultiScores (){
        val sharedPref = getSharedPreferences("multiplication_score", MODE_PRIVATE)
        multiWins = sharedPref.getInt("multi_wins", 0)
        multiLoses = sharedPref.getInt("multi_loses", 0)

        multiScoreTextSbA.text = "Multiplication (*) \n Wins : $multiWins \n Loses : $multiLoses"
    }

    fun getDivScores () {
        val sharedPref = getSharedPreferences("division_score", MODE_PRIVATE)
        divWins = sharedPref.getInt("div_wins",0)
        divLoses = sharedPref.getInt("div_wins",0)

        divScoreTextSbA.text = "Division (/) \n Wins : $divWins \n Loses : $divLoses"

    }

}