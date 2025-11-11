package com.example.minispel

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
import com.google.android.material.button.MaterialSplitButton

private lateinit var spinnerMa: Spinner
private lateinit var questionTxtViewMa: TextView
private lateinit var userAnswerEditMa: EditText
private lateinit var winLoseTxtViewMa: TextView
private lateinit var nameEditMa: EditText
private lateinit var materialSplitButton: MaterialSplitButton
private var wins : Int = 0
private var loses : Int = 0
private var correctAnswer: Int = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        spinnerMa = findViewById(R.id.spinnerDifficultyAcA)
        questionTxtViewMa = findViewById(R.id.questionTxtViewAm)
        userAnswerEditMa = findViewById(R.id.userAnswerEditAm)
//        nameEditMa = findViewById(R.id.nameEditAm)
        winLoseTxtViewMa = findViewById(R.id.winLoseTxtViewAm)


//        spinner()

        val enterButtonMa = findViewById<Button>(R.id.enterButtonAcA)

//        enterButtonMa.setOnClickListener {
//
//            if (userAnswerEditMa.text.isEmpty()) {
//                Toast.makeText(this, "Skriv in ett tal först!", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            handleAnswer()
//            userAnswerEditMa.text.clear()
//
//        }


    }

    //----End of onCreate---//


    override fun onResume(){
        super.onResume()
        spinner()

        val sharedPrefMa = getSharedPreferences("addition_score", MODE_PRIVATE)
        wins =sharedPrefMa.getInt("add_wins", 0)
        loses = sharedPrefMa.getInt("add_loses",0)

//        winLoseTxtViewMa.text = getString(R.string.wins_loses, wins, loses)

    }

    fun resetSharedPref () {
        val sharedPrefMa = getSharedPreferences("math_score", MODE_PRIVATE)

        wins = 0
        loses = 0
        sharedPrefMa.edit().apply{
            putInt("wins", 0)
            putInt("loses", 0)
            apply()
        }

        winLoseTxtViewMa.text = getString(R.string.wins_loses, wins, loses)

        Toast.makeText(this,"ScoreBoard is clear",Toast.LENGTH_SHORT).show()
    }

    private fun spinner() {
        val categories =
            arrayOf("Category","Addition (+)", "Subtration (-)", "Multiplikation (*)", "division (/)")
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
//        val firstNr = (1..20).random()
//        val secondNr = (1..20).random()

        when (position) {
            0 ->{

            }
            1 -> {
                Toast.makeText(this, "Addition (+)", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AdditionActivity::class.java)
                startActivity(intent)

//                correctAnswer = firstNr + secondNr
//                questionTxtViewMa.text = getString(R.string.addition_question, firstNr, secondNr)
            }

            2 -> {
                Toast.makeText(this, "Addition (+)", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AdditionActivity::class.java)
                startActivity(intent)
//                Toast.makeText(this, "Subtration (-)", Toast.LENGTH_SHORT).show()
//                correctAnswer = firstNr - secondNr
//                questionTxtViewMa.text = getString(R.string.subtrack_question, firstNr, secondNr)
            }

            3 -> {
                Toast.makeText(this, "Addition (+)", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AdditionActivity::class.java)
                startActivity(intent)
//                Toast.makeText(this, "Multiplikatin (*)", Toast.LENGTH_SHORT).show()
//                correctAnswer = firstNr * secondNr
//                questionTxtViewMa.text =
//                    getString(R.string.multiplication_question, firstNr, secondNr)
            }
            4 -> {
                Toast.makeText(this, "Addition (+)", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AdditionActivity::class.java)
                startActivity(intent)
//                Toast.makeText(this, "division (/)", Toast.LENGTH_SHORT).show()
//                correctAnswer = firstNr / secondNr
//                questionTxtViewMa.text = getString(R.string.division_question, firstNr, secondNr)
            }

        }

    }

    //---use in activity----//--//--//--//--//--/7
    fun handleAnswer() {
        val answeredCorrect = checkWin()

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