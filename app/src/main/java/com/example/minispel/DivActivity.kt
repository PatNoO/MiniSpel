package com.example.minispel

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


class DivActivity : AppCompatActivity() {

    private lateinit var player : Player
    private lateinit var questionTextDiA: TextView
    private lateinit var winLoseTextDiA: TextView
    private lateinit var spinnerDifficultyDiA: Spinner
    private lateinit var resultViewDiA: TextView
    private lateinit var answerTextDiA: TextInputEditText
    private var currentDifficulty = 0
    var firstNumber = 0
    var secondNumber = 0
    var correctAnswer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_div)

        spinnerDifficultyDiA = findViewById(R.id.spinnerDifficultyAcA)
        questionTextDiA = findViewById(R.id.questionTextAcA)
        winLoseTextDiA = findViewById(R.id.resultViewAcA)
        answerTextDiA = findViewById(R.id.answerInputAda)

        spinner()

        val enterButtonDiA = findViewById<Button>(R.id.enterButtonAcA)
        val backButtonDiA = findViewById<Button>(R.id.backButtonAcA)

        enterButtonDiA.setOnClickListener {

            if (answerTextDiA.text.isNullOrEmpty()) {
                Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            handleAnswer()
            answerTextDiA.text?.clear()
            setDifficulty(currentDifficulty)
        }

        backButtonDiA.setOnClickListener {
            finish()
        }
    }

    //----End of onCreate---//

    private fun handleAnswer() {
        resultViewDiA = findViewById(R.id.resultViewAcA)


        val userAnswerText = answerTextDiA.text.toString()
        val userAnswer = userAnswerText.toIntOrNull()

        val sharedPref = getSharedPreferences("division_score", MODE_PRIVATE)

        var wins = sharedPref.getInt("div_wins", 0)
        var loses = sharedPref.getInt("div_loses", 0)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            player = intent.getSerializableExtra("player", Player::class.java)!!
        } else {
            player = intent.getSerializableExtra("player") as Player
        }

        val intentResult = Intent().apply {
            putExtra("player_updated", player)
        }
        setResult(RESULT_OK, intentResult)

        if (userAnswer == correctAnswer) {
            wins++
            player.wins ++
            winLoseTextDiA.text = getString(R.string.correct_answer)
        } else {
            loses++
            player.loses ++
            winLoseTextDiA.text = getString(R.string.wrong_answer)
        }

        sharedPref.edit().apply {
            putInt("div_wins", wins)
            putInt("div_loses", loses)
            apply()
        }


    }

    private fun spinner() {
        val categories =
            arrayOf("Easy", "Medium", "Hard", "SuperHard")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerDifficultyDiA.adapter = spinnerAdapter

        spinnerDifficultyDiA.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long

            ) {
                setDifficulty(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    fun setDifficulty(position: Int) {

        currentDifficulty = position

        when (position) {
            0 -> {
                Toast.makeText(this, "Your choice ' Easy ' ", Toast.LENGTH_SHORT).show()
                firstNumber = (1..10).random()
                secondNumber = (1..10).random()
                correctAnswer = firstNumber / secondNumber
                questionTextDiA.text = getString(R.string.div_question, firstNumber, secondNumber)
            }

            1 -> {
                Toast.makeText(this, "Your choice ' Medium ' ", Toast.LENGTH_SHORT).show()
                firstNumber = (10..20).random()
                secondNumber = (10..30).random()
                correctAnswer = firstNumber / secondNumber
                questionTextDiA.text = getString(R.string.div_question, firstNumber, secondNumber)
            }

            2 -> {
                Toast.makeText(this, "Your choice ' Hard ' ", Toast.LENGTH_SHORT).show()
                firstNumber = (10..30).random()
                secondNumber = (10..40).random()
                correctAnswer = firstNumber / secondNumber
                questionTextDiA.text = getString(R.string.div_question, firstNumber, secondNumber)
            }

            3 -> {
                Toast.makeText(this, "Your choice ' SuperHard ' ", Toast.LENGTH_SHORT).show()
                firstNumber = (30..100).random()
                secondNumber = (20..350).random()
                correctAnswer = firstNumber / secondNumber
                questionTextDiA.text = getString(R.string.div_question, firstNumber, secondNumber)
            }
        }
    }

}