package com.quocdat.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.quocdat.workoutapp.databinding.ActivityExcerciseBinding
import com.quocdat.workoutapp.models.ExerciseModel
import com.quocdat.workoutapp.utils.Constants

class ExerciseActivity : AppCompatActivity() {

    private var binding: ActivityExcerciseBinding? = null

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var restTimerExercise: CountDownTimer? = null
    private var restProgressExercise = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentPositionExercise = -1

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

        exerciseList = Constants.defaultExerciseList()

        setRestView()
    }

    private fun setExerciseView(){
        binding?.flProgressBar?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE

        binding?.tvExercise?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE

        if (restTimerExercise != null){
            restTimerExercise!!.cancel()
            restProgressExercise = 0
        }

        binding?.tvExercise?.text = exerciseList!![currentPositionExercise].getName()
        binding?.ivImage?.setImageResource(exerciseList!![currentPositionExercise].getImage())

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
                if (currentPositionExercise < exerciseList!!.size - 1){
                    setRestView()
                }else{
                    Toast.makeText(this@ExerciseActivity,
                        "Congratulation! You have completed the 7 minutes workout",
                        Toast.LENGTH_LONG).show()
                }
            }

        }.start()
    }

    private fun setRestView(){
        binding?.flProgressBar?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE

        binding?.tvExercise?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
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
                currentPositionExercise++
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