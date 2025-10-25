package com.example.bemyguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var answer = 0
    var isGameOver = false
    var numOfAttempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startOver()
    }



    fun generateAnswer(){
        answer = Random.nextInt(1, 25)

    }

    fun startOver(){
        isGameOver = false
        generateAnswer()
        numOfAttempts = 0

        val answerTextView = findViewById<TextView>(R.id.answer)
        answerTextView.text = "??"

        val submitButton = findViewById<Button>(R.id.buttonSubmit)
        submitButton.isEnabled = true

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Guess 1 to 25"

        val editTextGuess = findViewById<EditText>(R.id.editTextGuess)
        editTextGuess.text.clear()
    }

    fun btnStartOverTapped(view: View){
        startOver()
    }

    fun btnSubmitTapped(view: View){

        val guess = getUserGuess() ?: -999

        val textView = findViewById<TextView>(R.id.textView)
        if(guess !in 1..25) // Same as guess<1 || guess>25
        {
            textView.text = "Guess Not in 1 to 25"
            return
        }

        var message = ""
        numOfAttempts++

        if(guess == answer) {
            if (guess == 1) {
                message = "Correct! Guess: $numOfAttempts"
            }
            else{
                message = "Correct! Guesses: $numOfAttempts"
            }
            isGameOver = true

            val answerTextView = findViewById<TextView>(R.id.answer)
            answerTextView.text = answer.toString()
            val submitButton = findViewById<Button>(R.id.buttonSubmit)
            submitButton.isEnabled = false
        }
        else{
            message = if(guess<answer) "Guess Higher!" else "Guess Lower!"

        }

        textView.text = message
    }

    fun getUserGuess() : Int? {
        val editTextGuess = findViewById<EditText>(R.id.editTextGuess)
        val userGuess = editTextGuess.text.toString()

        var guessAsInt = 0
        try{
            guessAsInt = userGuess.toInt()
        }
        catch(e:Exception){
            return null
        }
        return guessAsInt

    }
}