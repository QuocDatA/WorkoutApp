package com.quocdat.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quocdat.workoutapp.databinding.ActivityExcerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private var binding: ActivityExcerciseBinding? = null

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
    }
}