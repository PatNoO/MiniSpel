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

private lateinit var winLoseTxtViewMa: TextView

private var wins : Int = 0
private var loses : Int = 0


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        spinnerMa = findViewById(R.id.spinnerAm)




    }

    //----End of onCreate---//


    override fun onResume(){
        super.onResume()
        spinner()

        val sharedPrefMa = getSharedPreferences("addition_score", MODE_PRIVATE)
        wins =sharedPrefMa.getInt("add_wins", 0)
        loses = sharedPrefMa.getInt("add_loses",0)

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
        spinnerMa.dropDownVerticalOffset = spinnerMa.height
        
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

        when (position) {
            0 ->{

            }
            1 -> {
//                Toast.makeText(this, "Addition (+)", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AddActivity::class.java)
                startActivity(intent)
            }

            2 -> {
//                Toast.makeText(this, "Addition (+)", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SubActivity::class.java)
                startActivity(intent)
            }

            3 -> {
//                Toast.makeText(this, "Addition (+)", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MultiActivity::class.java)
                startActivity(intent)
            }
            4 -> {
//                Toast.makeText(this, "Addition (+)", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DivActivity::class.java)
                startActivity(intent)
//
            }

        }

    }


}