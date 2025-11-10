package com.example.minispel

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

lateinit var spinnerMa: Spinner
lateinit var questionTxtViewMa: TextView
lateinit var userAnswerEditMa: EditText
lateinit var winLoseTxtViewMa: TextView
lateinit var nameEditMa: EditText
var correctAnswer: Int = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        spinnerMa = findViewById(R.id.spinnerAm)
        questionTxtViewMa = findViewById(R.id.questionTxtViewAm)
        userAnswerEditMa = findViewById(R.id.userAnswerEditAm)
        nameEditMa = findViewById(R.id.nameEditAm)
        winLoseTxtViewMa = findViewById(R.id.winLoseTxtViewAm)

        spinner()

        val enterButtonMa = findViewById<Button>(R.id.enterButtonAm)

        enterButtonMa.setOnClickListener {
            handleAnswer()
        }

    }

    private fun spinner() {
        val categories =
            arrayOf("Addition (+)", "Subtration (-)", "Multiplikation (*)", "division(/)")
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
        val firstNr = (1..20).random()
        val secondNr = (1..20).random()

        when (position) {
            0 -> {
                Toast.makeText(this, "Addition (+)", Toast.LENGTH_SHORT).show()
                correctAnswer = firstNr + secondNr
                questionTxtViewMa.text = "$firstNr + $secondNr .. = ?"

            }

            1 -> {
                Toast.makeText(this, "Subtration (-)", Toast.LENGTH_SHORT).show()
                correctAnswer = firstNr - secondNr
                questionTxtViewMa.text = "$firstNr - $secondNr .. = ?"

            }

            2 -> {
                Toast.makeText(this, "Multiplikatin (*)", Toast.LENGTH_SHORT).show()
                correctAnswer = firstNr * secondNr
                questionTxtViewMa.text = "$firstNr * $secondNr .. = ?"

            }

            3 -> {
                Toast.makeText(this, "division (/)", Toast.LENGTH_SHORT).show()
                correctAnswer = firstNr / secondNr
                questionTxtViewMa.text = "$firstNr / $secondNr .. = ?"

            }

        }

    }

    fun handleAnswer() {
        val answeredCorrect = checkWin()


        if (answeredCorrect) {
            winLoseTxtViewMa.text = "Jippie du svarade rätt"
        } else {
            winLoseTxtViewMa.text = "Attans du svarade fel "

        }
        spinner()
    }

    fun checkWin(): Boolean {
        val userAnswerText = userAnswerEditMa.text.toString()
        val userAnswer = userAnswerText.toIntOrNull()

        if (userAnswer == null) {
            Toast.makeText(this, "Skriv in ett tal först!", Toast.LENGTH_SHORT).show()
            return false
        }

        return userAnswer == correctAnswer
    }

}