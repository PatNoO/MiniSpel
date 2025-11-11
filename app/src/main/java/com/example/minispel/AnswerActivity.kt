package com.example.minispel

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess


class AnswerActivity : AppCompatActivity() {

//    lateinit var resultView : TextView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_answer)
//
//        resultView = findViewById(R.id.resultViewAa)
//        val button = findViewById<Button>(R.id.buttonAa)
//
//        val answer = intent.getBooleanExtra("answeredCorrect", false)
//
//        val sharedPref =getSharedPreferences("math_score", Context.MODE_PRIVATE)
//
//        var wins = sharedPref.getInt("wins", 0)
//        var loses = sharedPref.getInt("loses", 0)
//
//        if (answer) {
//            wins++
//            resultView.text = getString(R.string.correct_answer )
//
//
//        } else {
//            loses++
//            resultView.text = getString(R.string.wrong_answer)
//
//
//        }
//
//        sharedPref.edit().apply {
//            putInt("wins",wins)
//            putInt("loses",loses)
//            apply()
//        }
//
////        button.setOnClickListener {
////            finish()
////        }
//
//    }

}