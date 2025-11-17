package com.example.minispel.math.activitys


import com.example.minispel.fragments.WinOutputFragment
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
import com.example.minispel.fragments.LoseOutputFragment
import com.example.minispel.Player
import com.example.minispel.R
import com.google.android.material.textfield.TextInputEditText


class AddActivity : AppCompatActivity() {

    private lateinit var player : Player
    private lateinit var questionTextAdA: TextView
    private lateinit var winLoseTextAdA: TextView
    private lateinit var spinnerDifficultyAdA: Spinner
    private lateinit var resultViewAdA: TextView
    private lateinit var answerInputAdA: TextInputEditText
    private var currentDifficulty = 0
    var firstNumber = 0
    var secondNumber = 0
    var correctAnswer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        spinnerDifficultyAdA = findViewById(R.id.spinnerDifficultyAcA)
        questionTextAdA = findViewById(R.id.questionTextAcA)
        winLoseTextAdA = findViewById(R.id.resultViewAcA)
        answerInputAdA = findViewById(R.id.answerInputAda)


        spinner()

        val enterButtonAdA = findViewById<Button>(R.id.enterButtonAcA)
        val backButtonAdA = findViewById<Button>(R.id.backButtonAcA)

        // Handles the user's answer when pressing Enter
        enterButtonAdA.setOnClickListener {

            if (answerInputAdA.text.isNullOrEmpty()) {
                Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            handleAnswer()
            answerInputAdA.text?.clear()
            setDifficulty(currentDifficulty)
        }

        // Go back to MainActivity
        backButtonAdA.setOnClickListener {
            removeWinOutputFragment()
            removeLoseOutputFragment()
            finish()
        }
    }

    fun addWinOutputFragment() {
        val addWinOutputFragment = WinOutputFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.cardView_aca, addWinOutputFragment, "win_output_fragment")
        transaction.commit()
    }

    fun removeWinOutputFragment() {
        val removeWinOutputFragment =
            supportFragmentManager.findFragmentByTag("win_output_fragment")

        if (removeWinOutputFragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(removeWinOutputFragment)
            transaction.commit()
        } else {
            Toast.makeText(this, "No Win Fragment found", Toast.LENGTH_SHORT).show()
        }
    }

    fun addLoseOutputFragment() {
        val addLoseOutputFragment = LoseOutputFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.cardView_aca, addLoseOutputFragment, "lose_output_fragment")
        transaction.commit()
    }

    fun removeLoseOutputFragment() {
        val removeLoseOutputFragment = LoseOutputFragment()

        if (removeLoseOutputFragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(removeLoseOutputFragment)
            transaction.commit()
        } else {
            Toast.makeText(this, "No Lose Fragment found", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Checks the user's answer, updates Player and SharedPreferences,
     * and displays the result.
     */
    private fun handleAnswer() {
        resultViewAdA = findViewById(R.id.resultViewAcA)


        val userAnswerText = answerInputAdA.text.toString()
        val userAnswer = userAnswerText.toIntOrNull()

        val sharedPref = getSharedPreferences("addition_score", MODE_PRIVATE)

        var wins = sharedPref.getInt("add_wins", 0)
        var loses = sharedPref.getInt("add_loses", 0)

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
            // Prints Win @String !
            addWinOutputFragment()
            winLoseTextAdA.text = getString(R.string.correct_answer)
        } else {
            loses++
            player.loses ++
            // Prints Lose @String !
            addLoseOutputFragment()
            winLoseTextAdA.text = getString(R.string.wrong_answer)
        }
        sharedPref.edit().apply {
            putInt("add_wins", wins)
            putInt("add_loses", loses)
            apply()
        }

    }
    /**
     * Sets up the difficulty spinner with levels:
     * Easy, Medium, Hard, SuperHard.
     */
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
    /**
     * Generates a new addition question based on difficulty.
     *
     * @param position The difficulty index:
     * 0 = Easy, 1 = Medium, 2 = Hard, 3 = SuperHard.
     */
    fun setDifficulty(position: Int) {

        currentDifficulty = position

        when (position) {
            0 -> {
                firstNumber = (1..10).random()
                secondNumber = (1..10).random()
                correctAnswer = firstNumber + secondNumber
                questionTextAdA.text = getString(R.string.add_question, firstNumber, secondNumber)
            }

            1 -> {
                firstNumber = (10..20).random()
                secondNumber = (10..30).random()
                correctAnswer = firstNumber + secondNumber
                questionTextAdA.text = getString(R.string.add_question, firstNumber, secondNumber)
            }

            2 -> {
                firstNumber = (10..30).random()
                secondNumber = (10..40).random()
                correctAnswer = firstNumber + secondNumber
                questionTextAdA.text = getString(R.string.add_question, firstNumber, secondNumber)
            }

            3 -> {
                firstNumber = (30..100).random()
                secondNumber = (20..350).random()
                correctAnswer = firstNumber + secondNumber
                questionTextAdA.text = getString(R.string.add_question, firstNumber, secondNumber)
            }
        }
    }

}