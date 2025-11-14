package com.example.minispel

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
/**
 * ScoreboardActivity displays the player's results for each math category
 * and allows the user to reset saved scores. It also resets the player's
 * total wins and losses when requested.
 *
 * @property player The player object received from MainActivity.
 * @property addScoreTextSbA TextView showing addition wins and losses.
 * @property subScoreTextSbA TextView showing subtraction wins and losses.
 * @property multiScoreTextSbA TextView showing multiplication wins and losses.
 * @property divScoreTextSbA TextView showing division wins and losses.
 */
class ScoreboardActivity : AppCompatActivity() {
    private lateinit var player : Player
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
        // Load saved scores from SharedPreferences
        getAddScores()
        getSubScores()
        getMultiScores()
        getDivScores()

        // Reset all scores and player's total score
        resetScoreButtonSbA.setOnClickListener {
            resetAddScorePref()
            resetSubScorePref()
            resetMultiScorePref()
            resetDivScorePref()
            resetPlayerScore()

            Toast.makeText(this, "ScoreBoard is clear", Toast.LENGTH_SHORT).show()

        }

        backScoreButtonSbA.setOnClickListener {
            finish()
        }

    }
    /**
     * Resets the player's total wins and losses and sends the updated player object back.
     *
     * @return RESULT_OK with the updated player instance.
     */
    fun resetPlayerScore() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            player = intent.getSerializableExtra("player", Player::class.java)!!
        }else {
            player = intent.getSerializableExtra("player") as Player
        }

        player.wins = 0
        player.loses = 0

        val intentResult = Intent().apply {
            putExtra("player_updated", player)
        }
        setResult(RESULT_OK, intentResult)
    }
    /**
     * Clears stored addition scores in SharedPreferences.
     */
    fun resetAddScorePref() {
        val sharedPref = getSharedPreferences("addition_score", MODE_PRIVATE)


        sharedPref.edit().apply {
            putInt("add_wins", 0)
            putInt("add_loses", 0)
            apply()
        }
    }
    /**
     * Clears stored subtraction scores in SharedPreferences.
     */
    fun resetSubScorePref() {
        val sharedPref = getSharedPreferences("subtraction_score", MODE_PRIVATE)

        sharedPref.edit().apply {
            putInt("sub_wins", 0)
            putInt("sub_loses", 0)
            apply()
        }
    }
    /**
     * Clears stored multiplication scores in SharedPreferences.
     */
    fun resetMultiScorePref() {
        val sharedPref = getSharedPreferences("multiplication_score", MODE_PRIVATE)

        sharedPref.edit().apply {
            putInt("multi_wins", 0)
            putInt("multi_loses", 0)
            apply()
        }
    }
    /**
     * Clears stored division scores in SharedPreferences.
     */
    fun resetDivScorePref() {
        val sharedPref = getSharedPreferences("division_score", MODE_PRIVATE)

        sharedPref.edit().apply {
            putInt("div_wins", 0)
            putInt("div_loses", 0)
            apply()
        }
    }
    /**
     * Loads and displays addition scores.
     */
    fun getAddScores (){
        val sharedPref = getSharedPreferences("addition_score", MODE_PRIVATE)
        addWins = sharedPref.getInt("add_wins", 0)
        addLoses = sharedPref.getInt("add_loses", 0)

        addScoreTextSbA.text = "Addition (+) \n Wins : $addWins \n Loses : $addLoses"
    }
    /**
     * Loads and displays subtraction scores.
     */
    fun getSubScores (){
        val sharedPref = getSharedPreferences("subtraction_score", MODE_PRIVATE)
        subWins = sharedPref.getInt("sub_wins", 0)
        subLoses = sharedPref.getInt("sub_loses", 0)

        subScoreTextSbA.text = "Subtraction (-) \n Wins : $subWins \n Loses : $subLoses"
    }
    /**
     * Loads and displays multiplication scores.
     */
    fun getMultiScores (){
        val sharedPref = getSharedPreferences("multiplication_score", MODE_PRIVATE)
        multiWins = sharedPref.getInt("multi_wins", 0)
        multiLoses = sharedPref.getInt("multi_loses", 0)

        multiScoreTextSbA.text = "Multiplication (*) \n Wins : $multiWins \n Loses : $multiLoses"
    }
    /**
     * Loads and displays division scores.
     */
    fun getDivScores () {
        val sharedPref = getSharedPreferences("division_score", MODE_PRIVATE)
        divWins = sharedPref.getInt("div_wins",0)
        divLoses = sharedPref.getInt("div_loses",0)

        divScoreTextSbA.text = "Division (/) \n Wins : $divWins \n Loses : $divLoses"

    }

}