package com.emmy.livedatademo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.emmy.livedatademo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countDownTimer : TextView = binding.countdownText
        val inputText : EditText = binding.editTextText
        val start : Button = binding.start
        val stop : Button = binding.stop

        //this is to link the view model to this activity
        val viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        viewModel.seconds.observe(this, {
            countDownTimer.text = it.toString()
        })

        viewModel.finished.observe(this, {
            if (it) {
                Toast.makeText(this, "Countdown Over", Toast.LENGTH_SHORT).show()
            }
        })

        start.setOnClickListener {

            if (inputText.text.isEmpty() || inputText.text.length < 4) {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
            }else {
                viewModel.timerValue.value = inputText.text.toString().toLong()
                viewModel.startTimer()
            }
        }

        stop.setOnClickListener {
            countDownTimer.text = viewModel.stop.toString()
            if (inputText.text.isEmpty() || inputText.text.length < 4) {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
            }else {
                viewModel.timerValue.value = inputText.text.toString().toLong()
                viewModel.stopTimer()
            }
        }
    }
}