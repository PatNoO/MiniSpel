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

class SubActivity : AppCompatActivity() {
    private lateinit var player : Player
    private lateinit var questionTextSuA: TextView

    private lateinit var winLoseTextSuA: TextView

    private lateinit var spinnerDifficultySuA: Spinner
    private lateinit var resultViewSuA: TextView
    private lateinit var answerTextSuA: TextInputEditText
    private var currentDifficulty = 0
    var firstNumber = 0
    var secondNumber = 0
    var correctAnswer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sub)

        spinnerDifficultySuA = findViewById(R.id.spinnerDifficultyAcA)
        questionTextSuA = findViewById(R.id.questionTextAcA)
        winLoseTextSuA = findViewById(R.id.resultViewAcA)
        answerTextSuA = findViewById(R.id.answerInputAda)

        spinner()

        val enterButtonSuA = findViewById<Button>(R.id.enterButtonAcA)
        val backButtonSuA = findViewById<Button>(R.id.backButtonAcA)

        enterButtonSuA.setOnClickListener {

            if (answerTextSuA.text.isNullOrEmpty()) {
                Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            handleAnswer()
            answerTextSuA.text?.clear()
            setDifficulty(currentDifficulty)
        }

        backButtonSuA.setOnClickListener {
            finish()
        }
    }

    //----End of onCreate---//

    private fun handleAnswer() {
        resultViewSuA = findViewById(R.id.resultViewAcA)


        val userAnswerText = answerTextSuA.text.toString()
        val userAnswer = userAnswerText.toIntOrNull()

        val sharedPref = getSharedPreferences("subtraction_score", MODE_PRIVATE)

        var wins = sharedPref.getInt("sub_wins", 0)
        var loses = sharedPref.getInt("sub_loses", 0)

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
            winLoseTextSuA.text = getString(R.string.correct_answer)
        } else {
            loses++
            player.loses ++
            winLoseTextSuA.text = getString(R.string.wrong_answer)
        }

        sharedPref.edit().apply {
            putInt("sub_wins", wins)
            putInt("sub_loses", loses)
            apply()
        }


    }

    private fun spinner() {
        val categories =
            arrayOf("Easy", "Medium", "Hard", "SuperHard")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerDifficultySuA.adapter = spinnerAdapter

        spinnerDifficultySuA.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                correctAnswer = firstNumber - secondNumber
                questionTextSuA.text = getString(R.string.sub_question, firstNumber, secondNumber)
            }

            1 -> {
                Toast.makeText(this, "Your choice ' Medium ' ", Toast.LENGTH_SHORT).show()
                firstNumber = (10..20).random()
                secondNumber = (10..30).random()
                correctAnswer = firstNumber - secondNumber
                questionTextSuA.text = getString(R.string.sub_question, firstNumber, secondNumber)
            }

            2 -> {
                Toast.makeText(this, "Your choice ' Hard ' ", Toast.LENGTH_SHORT).show()
                firstNumber = (10..30).random()
                secondNumber = (10..40).random()
                correctAnswer = firstNumber - secondNumber
                questionTextSuA.text = getString(R.string.sub_question, firstNumber, secondNumber)
            }

            3 -> {
                Toast.makeText(this, "Your choice ' SuperHard ' ", Toast.LENGTH_SHORT).show()
                firstNumber = (30..100).random()
                secondNumber = (20..350).random()
                correctAnswer = firstNumber - secondNumber
                questionTextSuA.text = getString(R.string.sub_question, firstNumber, secondNumber)
            }
        }
    }
}