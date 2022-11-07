package com.quocdat.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.quocdat.workoutapp.databinding.ActivityBmiactivityBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object{
        private const val METRIC_UNIT_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNIT_VIEW = "US_UNIT_VIEW"
    }

    private var currentVisibleView: String = "METRIC_UNIT_VIEW"

    private var binding: ActivityBmiactivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATOR BMI"
        }

        binding?.toolbarBmiActivity?.setNavigationOnClickListener { onBackPressed() }

        makeVisibleMetricView()

        binding?.rgUnit?.setOnCheckedChangeListener{ _, checkedId: Int ->
            if (checkedId == R.id.rbMetricUnit){
                makeVisibleMetricView()
            }else{
                makeVisibleUSView()
            }
        }

        binding?.llDisplayBMIResult?.visibility = View.GONE

        binding?.btnCalculateUnits?.setOnClickListener {
            binding?.llDisplayBMIResult?.visibility = View.VISIBLE
            calculateUnits()
        }
    }

    private fun calculateUnits(){
        if (currentVisibleView == METRIC_UNIT_VIEW){
            if (validateMetricUnits()){
                val heightValue = binding?.etMetricHeight?.text.toString().toFloat() / 100
                val weightValue = binding?.etMetricWeight?.text.toString().toFloat()

                val bmi = weightValue / (heightValue * heightValue)
                displayBMIResult(bmi)
            }else{
                Toast.makeText(this@BMIActivity, "Please enter valid values!", Toast.LENGTH_LONG).show()
            }
        }else{
            if (validateUSUnits()){
                val weightValue = binding?.etUSWeight?.text.toString().toFloat()
                val heightValueInch = binding?.etUSHeightInch?.text.toString().toFloat()
                val heightValueFeet = binding?.etUSHeightFeet?.text.toString().toFloat()

                val heightValue = heightValueInch + heightValueFeet * 12

                val bmi = 703 * (weightValue / (heightValue * heightValue))

                displayBMIResult(bmi)
            }else{
                Toast.makeText(this@BMIActivity, "Please enter valid values!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun makeVisibleMetricView(){
        currentVisibleView = METRIC_UNIT_VIEW
        binding?.tilMetricHeight?.visibility = View.VISIBLE
        binding?.tilMetricWeight?.visibility = View.VISIBLE

        binding?.tilUSWeight?.visibility = View.GONE
        binding?.tilUSHeightFeet?.visibility = View.GONE
        binding?.tilUSHeightInch?.visibility = View.GONE

        binding?.etMetricHeight?.text!!.clear()
        binding?.etMetricWeight?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun makeVisibleUSView(){
        currentVisibleView = US_UNIT_VIEW
        binding?.tilMetricHeight?.visibility = View.GONE
        binding?.tilMetricWeight?.visibility = View.GONE

        binding?.tilUSWeight?.visibility = View.VISIBLE
        binding?.tilUSHeightFeet?.visibility = View.VISIBLE
        binding?.tilUSHeightInch?.visibility = View.VISIBLE

        binding?.etUSHeightFeet?.text!!.clear()
        binding?.etUSHeightInch?.text!!.clear()
        binding?.etUSWeight?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun displayBMIResult(bmi: Float){
        var bmiLabel: String
        var bmiDescription: String

        if (bmi.compareTo(15f) < 0){
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        }else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) < 0){
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        }else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) < 0){
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        }else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) < 0){
            bmiLabel = "Normal"
            bmiDescription = "Congratulation! You are in a good shape!"
        }else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) < 0){
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Workout maybe! "
        }else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) < 0){
            bmiLabel = "Obese class || (Moderately obese)"
            bmiDescription = "Oops! You really need to take better care of yourself! Workout maybe! "
        }else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) < 0){
            bmiLabel = "Obese class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now! "
        }else{
            bmiLabel = "Obese class || (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now! "
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription
    }

    private fun validateUSUnits(): Boolean{
        var isValid = true
        if (binding?.etUSHeightFeet?.text.toString().isEmpty()){
            isValid = false
        }else if (binding?.etUSHeightInch?.text.toString().isEmpty()){
            isValid = false
        }else if (binding?.etUSWeight?.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }

    private fun validateMetricUnits(): Boolean{
        var isValid = true
        if (binding?.etMetricHeight?.text.toString().isEmpty()){
            isValid = false
        }else if (binding?.etMetricWeight?.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }
}