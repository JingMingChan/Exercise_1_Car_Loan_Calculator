package com.example.exercise_1_car_loan_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.text.DecimalFormat
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    lateinit var carPrice: EditText
    lateinit var downPayAmt: EditText
    lateinit var loanPeriod: EditText
    lateinit var interestRate: EditText
    lateinit var carloan: TextView
    lateinit var interest: TextView
    lateinit var monRepay: TextView

    val formater = DecimalFormat("0.00")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carPrice = findViewById(R.id.carPrice)
        downPayAmt = findViewById(R.id.downPayAmt)
        loanPeriod = findViewById(R.id.loanPeriod)
        interestRate = findViewById(R.id.interestRate)

        carloan = findViewById(R.id.carLoanVal)
        interest = findViewById(R.id.interestVal)
        monRepay = findViewById(R.id.monRepayVal)


        findViewById<Button>(R.id.btn_cal).setOnClickListener {
            calculate(it)
        }

        findViewById<Button>(R.id.btn_Clr).setOnClickListener {
            clear()
        }
    }

    private fun calculate(view: View){

        try {

            carloan.setText(formater.format(carPrice.text.toString().toDouble() - downPayAmt.text.toString().toDouble()))
            interest.setText(formater.format(carloan.text.toString().toDouble() * interestRate.text.toString().toDouble() * loanPeriod.text.toString().toDouble()))

            if((carloan.text.toString().toDouble() + interestRate.text.toString().toDouble()) == 0.toDouble()){
                monRepay.setText(formater.format(0))
            }else {
                monRepay.setText(formater.format((carloan.text.toString().toDouble() + interestRate.text.toString().toDouble()) / loanPeriod.text.toString().toDouble() / 12.toDouble()))
            }

        }catch (e:Exception){
            carloan.setText(formater.format(0))
            interest.setText(formater.format(0))
            monRepay.setText(formater.format(0))
        }

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private fun clear(){

        carPrice.setText("")
        downPayAmt.setText("")
        loanPeriod.setText("")
        interestRate.setText("")
        carloan.setText("")
        interest.setText("")
        monRepay.setText("")
    }
}
