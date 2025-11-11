package com.example.minispel

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


class AdditionActivity : AppCompatActivity() {

    private lateinit var questionTextAdA : TextView

    private lateinit var winLoseTextAdA : TextView

    private lateinit var spinnerDifficultyAdA : Spinner
    private lateinit var resultViewAdA : TextView
    private lateinit var answerTextAdA : TextInputEditText
    private var currentDifficulty = 0
    var firstNumber  = 0
    var secondNumber = 0
    var correctAnswer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addition)

        spinnerDifficultyAdA = findViewById(R.id.spinnerDifficultyAcA)
        questionTextAdA = findViewById(R.id.questionTextAcA)
        winLoseTextAdA = findViewById(R.id.resultViewAcA)
        answerTextAdA = findViewById(R.id.answerInputAda)

        spinner()

        val enterButtonAdA = findViewById<Button>(R.id.enterButtonAcA)
        val backButtonAdA = findViewById<Button>(R.id.backButtonAcA)

        enterButtonAdA.setOnClickListener {

            if (answerTextAdA.text.isNullOrEmpty()){
                Toast.makeText(this,"Field cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
                handleAnswer()
                answerTextAdA.text?.clear()
            setDifficulty(currentDifficulty )
        }

        backButtonAdA.setOnClickListener {
            finish()
        }
    }

    private fun handleAnswer() {
        resultViewAdA = findViewById(R.id.resultViewAcA)


        val userAnswerText = answerTextAdA.text.toString()
        val userAnswer = userAnswerText.toIntOrNull()

        val sharedPref = getSharedPreferences("addition_score", MODE_PRIVATE)

        var wins = sharedPref.getInt("add_wins", 0)
        var loses = sharedPref.getInt("add_loses", 0)

        if (userAnswer == correctAnswer) {
            wins++
            winLoseTextAdA.text = getString(R.string.correct_answer)
        } else {
            loses++
            winLoseTextAdA.text = getString(R.string.wrong_answer)
        }

        sharedPref.edit().apply {
            putInt("add_wins", wins)
            putInt("add_loses", loses)
            apply()
        }


    }

    private fun spinner() {
        val categories =
            arrayOf("Easy", "Medium", "Hard", "SuperHard")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerDifficultyAdA.adapter = spinnerAdapter

        spinnerDifficultyAdA.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                correctAnswer = firstNumber + secondNumber
                questionTextAdA.text = getString(R.string.addition_question, firstNumber, secondNumber)
            }
            1 -> {
                Toast.makeText(this, "Your choice ' Medium ' ",Toast.LENGTH_SHORT).show()
                firstNumber = (10..20).random()
                secondNumber = (10..30).random()
                correctAnswer = firstNumber + secondNumber
                questionTextAdA.text = getString(R.string.addition_question, firstNumber, secondNumber)
            }
            2 -> {
                Toast.makeText(this, "Your choice ' Hard ' ",Toast.LENGTH_SHORT).show()
                firstNumber = (10..30).random()
                secondNumber = (10..40).random()
                correctAnswer = firstNumber + secondNumber
                questionTextAdA.text = getString(R.string.addition_question, firstNumber, secondNumber)
            }
            3 -> {
                Toast.makeText(this, "Your choice ' SuperHard ' ",Toast.LENGTH_SHORT).show()
                firstNumber = (30..100).random()
                secondNumber = (20..350).random()
                correctAnswer = firstNumber + secondNumber
                questionTextAdA.text = getString(R.string.addition_question, firstNumber, secondNumber)
            }
        }
    }

}