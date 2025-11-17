package com.example.minispel

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.minispel.math.activitys.AddActivity
import com.example.minispel.math.activitys.DivActivity
import com.example.minispel.math.activitys.MultiActivity
import com.example.minispel.math.activitys.SubActivity

/**
 * MainActivity is the central hub of the math game.
 * It displays the menu, greets the player, and allows navigation
 * to different math categories and the scoreboard.
 *
 * @property player The current player object containing name, wins, and losses.
 * @property spinnerMa Dropdown menu for selecting math categories.
 * @property winLoseInfoTextMa TextView showing total wins and losses.
 * @property headTextMa Greeting text showing the player name.
 */
class MainActivity : AppCompatActivity() {
    private var player: Player? = Player("Default", 0, 0)
    private lateinit var spinnerMa: Spinner
    private lateinit var winLoseInfoTextMa: TextView
    private lateinit var headTextMa: TextView

    /**
     * Handles results returned from launched activities such as AddActivity,
     * SubActivity, MultiActivity, DivActivity, ScoreboardActivity, and StartActivity.
     *
     * @param result The activity result containing updated Intent data.
     */
    private val startLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val playerUpdated = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    result.data?.getSerializableExtra("player_updated", Player::class.java)
                } else {
                    result.data?.getSerializableExtra("player_updated") as Player
                }
                player = playerUpdated
                showGreeting()
                showWinLose()
            }
        }

    /**
     * Called when the activity is created.
     * Launches StartActivity to let the user enter their name,
     * and initializes views.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Open StartActivity for player name input
        val intent = Intent(this, StartActivity::class.java)
        intent.putExtra("player", player)
        startLauncher.launch(intent)
        onPause()

        spinnerMa = findViewById(R.id.spinnerAm)
        headTextMa = findViewById(R.id.headTextAm)
        winLoseInfoTextMa = findViewById(R.id.win_lose_info_text_am)

        showGreeting()
    }

    //----End of onCreate---//
    /**
     * Updates the TextView showing the player's total wins and losses.
     */
    fun showWinLose() {
        winLoseInfoTextMa.text = getString(R.string.total_wins_loses, player?.wins, player?.loses)
    }

    /**
     * Updates the greeting text to show the player's name.
     */
    fun showGreeting() {
        headTextMa.text = getString(R.string.greeting, player?.name)
    }

    /**
     * Called when returning to this activity.
     * Reinitializes the spinner so the menu always works.
     */
    override fun onResume() {
        super.onResume()
        spinner()
    }

    /**
     * Sets up and populates the category selection spinner.
     * Adds an event listener to react when the user selects a category.
     */
    private fun spinner() {
        val categories =
            arrayOf(
                "Category",
                "Addition (+)",
                "Subtration (-)",
                "Multiplikation (*)",
                "division (/)",
                "ScoreBoard"
            )
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

    /**
     * Handles navigation based on the spinner selection.
     * Launches the correct activity depending on chosen category.
     *
     * @param position The selected category index from the spinner.
     */
    private fun setNewQuestion(position: Int) {

        when (position) {
            0 -> {}
            1 -> {
                val intent = Intent(this, AddActivity::class.java)
                intent.putExtra("player", player)
                startLauncher.launch(intent)
            }

            2 -> {
                val intent = Intent(this, SubActivity::class.java)
                intent.putExtra("player", player)
                startLauncher.launch(intent)
            }

            3 -> {
                val intent = Intent(this, MultiActivity::class.java)
                intent.putExtra("player", player)
                startLauncher.launch(intent)
            }

            4 -> {
                val intent = Intent(this, DivActivity::class.java)
                intent.putExtra("player", player)
                startLauncher.launch(intent)
            }

            5 -> {
                val intent = Intent(this, ScoreboardActivity::class.java)
                intent.putExtra("player", player)
                startLauncher.launch(intent)
            }
        }

    }

}