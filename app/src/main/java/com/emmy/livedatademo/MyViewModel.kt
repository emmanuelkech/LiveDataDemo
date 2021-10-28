package com.emmy.livedatademo

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private lateinit var timer: CountDownTimer
    val timerValue = MutableLiveData<Long>()

    private val _seconds = MutableLiveData<Int>()

    val stop = 0

    //this is what will be exposed to the activity
    val seconds : LiveData<Int> get() = _seconds

    var finished = MutableLiveData<Boolean>()

    fun startTimer() {
        timer = object : CountDownTimer(timerValue.value?.toLong()!!,1000) {
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished/1000
                _seconds.value = timeLeft.toInt()
            }
            override fun onFinish() {
                finished.value = true
            }
            //this is to start the timer
        }.start()
    }

    fun stopTimer() {
        timer.cancel()
    }
}