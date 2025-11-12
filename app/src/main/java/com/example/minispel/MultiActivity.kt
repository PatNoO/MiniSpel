package com.example.minispel

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MultiActivity : AppCompatActivity() {
    private lateinit var questionTextMuA : TextView

    private lateinit var winLoseTextMuA : TextView

    private lateinit var spinnerDifficultyMuA : Spinner
    private lateinit var resultViewMuA : TextView
    private lateinit var answerTextMuA : TextInputEditText
    private var currentDifficulty = 0
    var firstNumber  = 0
    var secondNumber = 0
    var correctAnswer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi)

        spinnerDifficultyMuA = findViewById(R.id.spinnerDifficultyAcA)
        questionTextMuA = findViewById(R.id.questionTextAcA)
        winLoseTextMuA = findViewById(R.id.resultViewAcA)
        answerTextMuA = findViewById(R.id.answerInputAda)

        spinner()

        val enterButtonMuA = findViewById<Button>(R.id.enterButtonAcA)
        val backButtonMuA = findViewById<Button>(R.id.backButtonAcA)

        enterButtonMuA.setOnClickListener {

            if (answerTextMuA.text.isNullOrEmpty()){
                Toast.makeText(this,"Field cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            handleAnswer()
            answerTextMuA.text?.clear()
            setDifficulty(currentDifficulty )
        }

        backButtonMuA.setOnClickListener {
            finish()
        }
    }

    private fun handleAnswer() {
        resultViewMuA = findViewById(R.id.resultViewAcA)


        val userAnswerText = answerTextMuA.text.toString()
        val userAnswer = userAnswerText.toIntOrNull()

        val sharedPref = getSharedPreferences("multiplication_score", MODE_PRIVATE)

        var wins = sharedPref.getInt("multi_wins", 0)
        var loses = sharedPref.getInt("multi_loses", 0)

        if (userAnswer == correctAnswer) {
            wins++
            winLoseTextMuA.text = getString(R.string.correct_answer)
        } else {
            loses++
            winLoseTextMuA.text = getString(R.string.wrong_answer)
        }

        sharedPref.edit().apply {
            putInt("multi_wins", wins)
            putInt("multi_loses", loses)
            apply()
        }


    }

    private fun spinner() {
        val categories =
            arrayOf("Easy", "Medium", "Hard", "SuperHard")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerDifficultyMuA.adapter = spinnerAdapter

        spinnerDifficultyMuA.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

    fun setDifficulty (position : Int) {

        currentDifficulty = position

        when (position) {
            0 -> {
                Toast.makeText(this, "Your choice ' Easy ' ",Toast.LENGTH_SHORT).show()
                firstNumber = (1..10).random()
                secondNumber = (1..10).random()
                correctAnswer = firstNumber * secondNumber
                questionTextMuA.text = getString(R.string.multip_question, firstNumber, secondNumber)
            }
            1 -> {
                Toast.makeText(this, "Your choice ' Medium ' ",Toast.LENGTH_SHORT).show()
                firstNumber = (10..20).random()
                secondNumber = (10..30).random()
                correctAnswer = firstNumber * secondNumber
                questionTextMuA.text = getString(R.string.multip_question, firstNumber, secondNumber)
            }
            2 -> {
                Toast.makeText(this, "Your choice ' Hard ' ",Toast.LENGTH_SHORT).show()
                firstNumber = (10..30).random()
                secondNumber = (10..40).random()
                correctAnswer = firstNumber * secondNumber
                questionTextMuA.text = getString(R.string.multip_question, firstNumber, secondNumber)
            }
            3 -> {
                Toast.makeText(this, "Your choice ' SuperHard ' ",Toast.LENGTH_SHORT).show()
                firstNumber = (30..100).random()
                secondNumber = (20..350).random()
                correctAnswer = firstNumber * secondNumber
                questionTextMuA.text = getString(R.string.multip_question, firstNumber, secondNumber)
            }
        }
    }
}