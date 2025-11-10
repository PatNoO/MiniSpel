package com.example.minispel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
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
import androidx.core.content.edit

private lateinit var spinnerMa: Spinner
private lateinit var questionTxtViewMa: TextView
private lateinit var userAnswerEditMa: EditText
private lateinit var winLoseTxtViewMa: TextView
private lateinit var nameEditMa: EditText
private var wins : Int = 0
private var loses : Int = 0
private var correctAnswer: Int = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        spinnerMa = findViewById(R.id.spinnerAm)
        questionTxtViewMa = findViewById(R.id.questionTxtViewAm)
        userAnswerEditMa = findViewById(R.id.userAnswerEditAm)
        nameEditMa = findViewById(R.id.nameEditAm)
        winLoseTxtViewMa = findViewById(R.id.winLoseTxtViewAm)


//        spinner()

        val enterButtonMa = findViewById<Button>(R.id.enterButtonAm)

        enterButtonMa.setOnClickListener {

            if (userAnswerEditMa.text.isEmpty()) {
                Toast.makeText(this, "Skriv in ett tal först!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            handleAnswer()
            userAnswerEditMa.text.clear()

        }

    }

    //----End of onCreate---//


    override fun onResume(){
        super.onResume()
        spinner()

        val sharedPrefMa = getSharedPreferences("math_score", Context.MODE_PRIVATE)
        wins =sharedPrefMa.getInt("wins", 0)
        loses = sharedPrefMa.getInt("loses",0)

        winLoseTxtViewMa.text = getString(R.string.wins_loses, wins, loses)

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
                questionTxtViewMa.text = getString(R.string.addition_question, firstNr, secondNr)

            }

            1 -> {
                Toast.makeText(this, "Subtration (-)", Toast.LENGTH_SHORT).show()
                correctAnswer = firstNr - secondNr
                questionTxtViewMa.text = getString(R.string.subtrack_question, firstNr, secondNr)

            }

            2 -> {
                Toast.makeText(this, "Multiplikatin (*)", Toast.LENGTH_SHORT).show()
                correctAnswer = firstNr * secondNr
                questionTxtViewMa.text =
                    getString(R.string.multiplication_question, firstNr, secondNr)

            }

            3 -> {
                Toast.makeText(this, "division (/)", Toast.LENGTH_SHORT).show()
                correctAnswer = firstNr / secondNr
                questionTxtViewMa.text = getString(R.string.division_question, firstNr, secondNr)

            }

        }

    }

    fun handleAnswer() {
        val answeredCorrect = checkWin()

//        if (!answeredCorrect){
//            loses ++
//        } else {
//            wins ++
//        }

        val intent = Intent(this, AnswerActivity::class.java)
        intent.putExtra("answeredCorrect", answeredCorrect )

        startActivity(intent)

    }

    fun checkWin(): Boolean {
        val userAnswerText = userAnswerEditMa.text.toString()
        val userAnswer = userAnswerText.toIntOrNull()

        return userAnswer == correctAnswer
    }

}