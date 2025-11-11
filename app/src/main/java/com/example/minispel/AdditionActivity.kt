package com.example.minispel

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class AdditionActivity : AppCompatActivity() {

    private lateinit var questionTextAdA : TextView

    private lateinit var winLoseTextAdA : TextView

    private lateinit var spinnerDifficultyAdA : Spinner

    var firstNumber  = 0
    var secondNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addition)

        spinnerDifficultyAdA = findViewById(R.id.spinnerDifficultyAcA)
        questionTextAdA = findViewById(R.id.questionTextAcA)
        winLoseTextAdA = findViewById(R.id.winLoseTextAcA)

        spinner()

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

        when (position) {
            0 -> {
                Toast.makeText(this, "Your choice ' Easy ' ",Toast.LENGTH_SHORT).show()
                firstNumber = (1..10).random()
                secondNumber = (1..10).random()
                questionTextAdA.text = getString(R.string.addition_question, firstNumber, secondNumber)
            }
            1 -> {
                Toast.makeText(this, "Your choice ' Medium ' ",Toast.LENGTH_SHORT).show()
                firstNumber = (1..20).random()
                secondNumber = (1..30).random()
                questionTextAdA.text = getString(R.string.addition_question, firstNumber, secondNumber)
            }
            2 -> {
                Toast.makeText(this, "Your choice ' Hard ' ",Toast.LENGTH_SHORT).show()
                firstNumber = (1..30).random()
                secondNumber = (1..40).random()
                questionTextAdA.text = getString(R.string.addition_question, firstNumber, secondNumber)
            }
            3 -> {
                Toast.makeText(this, "Your choice ' SuperHard ' ",Toast.LENGTH_SHORT).show()
                firstNumber = (1..100).random()
                secondNumber = (1..350).random()
                questionTextAdA.text = getString(R.string.addition_question, firstNumber, secondNumber)
            }
        }
    }

}