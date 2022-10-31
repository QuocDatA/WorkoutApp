package com.quocdat.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.quocdat.workoutapp.databinding.ActivityExcerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private var binding: ActivityExcerciseBinding? = null

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var restTimerExercise: CountDownTimer? = null
    private var restProgressExercise = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExcerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }

        setRestView()
    }

    private fun setExerciseView(){
        binding?.flProgressBar?.visibility = View.INVISIBLE
        binding?.tvTitle?.text = "Exercise Name"
        binding?.flExerciseView?.visibility = View.VISIBLE

        if (restTimerExercise != null){
            restTimerExercise!!.cancel()
            restProgressExercise = 0
        }

        setExerciseProgressBar()
    }

    private fun setExerciseProgressBar(){
        binding?.progressBarExercise?.progress = restProgressExercise

        restTimerExercise = object : CountDownTimer(30000, 1000){
            override fun onTick(p0: Long) {
                restProgressExercise++
                binding?.progressBarExercise?.progress = 30 - restProgressExercise
                binding?.tvTimerExercise?.text = (30 - restProgressExercise).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,
                    "30 seconds is over, reset again view please!",
                    Toast.LENGTH_LONG).show()
            }

        }.start()
    }

    private fun setRestView(){
        if (restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }

        setRestProgressBar()
    }

    private fun setRestProgressBar(){
        binding?.progressBar?.progress = restProgress

        restTimer = object : CountDownTimer(10000, 1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,
                    "Here now we will start the exercise",
                    Toast.LENGTH_LONG).show()
                setExerciseView()
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }

        if (restTimerExercise != null){
            restTimerExercise!!.cancel()
            restProgressExercise = 0
        }
        binding = null
    }
}