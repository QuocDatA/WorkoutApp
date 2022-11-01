package com.quocdat.workoutapp.utils

import com.quocdat.workoutapp.R
import com.quocdat.workoutapp.models.ExerciseModel

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()

        val abdominalCrunch = ExerciseModel(
            1, R.drawable.ic_abdominal_crunch,
            "Abdominal Crunch", false, false
        )
        exerciseList.add(abdominalCrunch)

        val highKneesRunningInPlace = ExerciseModel(
            2, R.drawable.ic_high_knees_running_in_place,
            "High knees running in place", false, false
        )
        exerciseList.add(highKneesRunningInPlace)

        val jumpingJacks = ExerciseModel(
            3, R.drawable.ic_jumping_jacks,
            "Jumping Jacks", false, false
        )
        exerciseList.add(jumpingJacks)

        val lunge = ExerciseModel(
            4, R.drawable.ic_lunge,
            "Lunge", false, false
        )
        exerciseList.add(lunge)

        val plank = ExerciseModel(
            5, R.drawable.ic_plank,
            "Plank", false, false
        )
        exerciseList.add(plank)

        val pushUp = ExerciseModel(
            6, R.drawable.ic_push_up,
            "Push Up", false, false
        )
        exerciseList.add(pushUp)

        val pushUpAndRotation = ExerciseModel(
            7, R.drawable.ic_push_up_and_rotation,
            "Push Up And Rotation", false, false
        )
        exerciseList.add(pushUpAndRotation)

        val sidePlank = ExerciseModel(
            8, R.drawable.ic_side_plank,
            "Side Plank", false, false
        )
        exerciseList.add(sidePlank)

        val squat = ExerciseModel(
            9, R.drawable.ic_squat,
            "Squat", false, false
        )
        exerciseList.add(squat)

        val stepUpOntoChair = ExerciseModel(
            10, R.drawable.ic_step_up_onto_chair,
            "Step Up Onto Chair", false, false
        )
        exerciseList.add(stepUpOntoChair)

        val tricepsDipOnChair = ExerciseModel(
            11, R.drawable.ic_triceps_dip_on_chair,
            "Triceps Dip On Chair", false, false
        )
        exerciseList.add(tricepsDipOnChair)

        return exerciseList
    }
}