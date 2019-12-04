package com.example.exercise_1_car_loan_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
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

        carloan = findViewById(R.id.carLoan)
        interest = findViewById(R.id.interest)
        monRepay = findViewById(R.id.monRepay)


        findViewById<Button>(R.id.btn_cal).setOnClickListener {
            calculate(it)
        }

        findViewById<Button>(R.id.btn_Clr).setOnClickListener {
            clear()
        }
    }

    private fun calculate(view: View){

        carloan.setText(R.string.car_loan)
        interest.setText(R.string.interest)
        monRepay.setText(R.string.monthly_Repayment)
        try {

            //if(TextUtils.isEmpty(carPrice.text) && TextUtils.isEmpty(downPayAmt.text))
            carloan.append(formater.format(carPrice.text.toString().toDouble() - downPayAmt.text.toString().toDouble()))
            //carloan.setText(formater.format(carPrice.text.toString().toDouble() - downPayAmt.text.toString().toDouble()))
            interest.append(formater.format((carPrice.text.toString().toDouble() - downPayAmt.text.toString().toDouble()) * interestRate.text.toString().toDouble() * loanPeriod.text.toString().toDouble()))

            monRepay.append(formater.format(((carPrice.text.toString().toDouble() - downPayAmt.text.toString().toDouble()) + ((carPrice.text.toString().toDouble() - downPayAmt.text.toString().toDouble()) * interestRate.text.toString().toDouble() * loanPeriod.text.toString().toDouble())) / loanPeriod.text.toString().toDouble() / 12.toDouble()))


        }catch (e:Exception){
            carloan.setText(R.string.car_loan)
            interest.setText(R.string.interest)
            monRepay.setText(R.string.monthly_Repayment)
            Toast.makeText(this, "Please don't leave any text box empty",
                Toast.LENGTH_SHORT).show()
        }

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private fun clear(){

        carPrice.setText("")
        downPayAmt.setText("")
        loanPeriod.setText("")
        interestRate.setText("")
        carloan.setText(R.string.car_loan)
        interest.setText(R.string.interest)
        monRepay.setText(R.string.monthly_Repayment)
    }
}
