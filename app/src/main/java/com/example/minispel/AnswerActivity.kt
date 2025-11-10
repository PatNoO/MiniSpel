package com.example.minispel

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess


class AnswerActivity : AppCompatActivity() {

    lateinit var resultView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        resultView = findViewById(R.id.resultViewAa)
        val button = findViewById<Button>(R.id.buttonAa)

        val answer = intent.getBooleanExtra("answeredCorrect", false)

        if (answer) {
            resultView.text = getString(R.string.correct_answer)
        } else {
            resultView.text = getString(R.string.wrong_answer)

        }

        button.setOnClickListener {
            finish()
        }

    }
}