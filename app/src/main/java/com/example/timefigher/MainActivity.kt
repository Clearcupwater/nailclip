package com.example.timefigher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var gameScoreTextview: TextView
    private lateinit var timeLeftTextView: TextView
    private lateinit var tapMeButton: Button
    private var score = 0

    private var gameStarted = false
    private lateinit var countDownTimer: CountDownTimer
    private var initialCountDOwn: Long = 6000
    private var countDownInterval : Long = 1000
    private var timeLeft = 6


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameScoreTextview = findViewById(R.id.game_score_text_view)
        timeLeftTextView = findViewById(R.id.time_left_text_view)
        tapMeButton = findViewById(R.id.tap_me_button)
        tapMeButton.setOnClickListener({
            incrementScore()
        })

        resetGame()

    }

    private fun incrementScore() {
        if (!gameStarted){
            startGame()
        }


        score++
        val newScore = getString(R.string.your_score,score)
        gameScoreTextview.text = newScore

    }

    private fun resetGame(){
        score = 0
        val initialScore = getString(R.string.your_score, score)
        gameScoreTextview.text = initialScore

        val initialTimeLeft = getString(R.string.time_left,timeLeft)
        timeLeftTextView.text = initialTimeLeft

        countDownTimer = object : CountDownTimer(initialCountDOwn,countDownInterval){

            //overrides functionality so we see it tick down then passes it to the view
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished.toInt()/1000
                val timeLeftString = getString(R.string.time_left, timeLeft)
                timeLeftTextView.text = timeLeftString
            }

            override fun onFinish() {
                endGame()
            }

        }

        gameStarted = false

    }

    private fun startGame() {
        countDownTimer.start()
        gameStarted = true

    }

    private fun endGame(){
        Toast.makeText(this, getString(R.string.game_over_message, score), Toast.LENGTH_LONG).show()
        resetGame()

    }

}
