package com.example.exercise_1_car_loan_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.app.Activity
import android.content.Context
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.app.ComponentActivity.ExtraData
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.view.inputmethod.InputMethodManager


class MainActivity : AppCompatActivity() {

    lateinit var carPrice: EditText
    lateinit var downPayAmt: EditText
    lateinit var LoanPeriod: EditText
    lateinit var InterestRate: EditText
    lateinit var carloan: TextView
    lateinit var interest: TextView
    lateinit var monRepay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carPrice = findViewById(R.id.carPrice)
        downPayAmt = findViewById(R.id.downPayAmt)
        LoanPeriod = findViewById(R.id.loanPeriod)
        InterestRate = findViewById(R.id.interestRate)

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


        carloan.text = ("%.2f".format(carPrice.text.toString().toDouble() - downPayAmt.text.toString().toDouble()).toDouble()).toString()


        interest.text = (carloan.text.toString().toDouble() * InterestRate.text.toString().toDouble() * LoanPeriod.text.toString().toDouble()).toString()

        monRepay.text = ((carloan.text.toString().toDouble() + InterestRate.text.toString().toDouble()) / LoanPeriod.text.toString().toDouble() / 12.toDouble()).toString()

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private fun clear(){

        carPrice.setText("")
        downPayAmt.setText("")
        LoanPeriod.setText("")
        InterestRate.setText("")

        carloan.setText("")
        interest.setText("")
        monRepay.setText("")
    }
}
