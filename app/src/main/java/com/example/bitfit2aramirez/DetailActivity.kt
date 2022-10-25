package com.example.bitfit2aramirez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

const val EXTRA_ENTRY = "EXTRA_ENTRY"

class DetailActivity : AppCompatActivity() {

    lateinit var dayInput: EditText
    lateinit var sleepInput: EditText
    lateinit var enterBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        dayInput = findViewById(R.id.eTDay)
        sleepInput = findViewById(R.id.eTHoursSlept)
        enterBtn = findViewById(R.id.enterButton)

        enterBtn.setOnClickListener {
            if(dayInput.text.isNotEmpty() && sleepInput.text.isNotEmpty()){
                val intent = Intent(this, MainActivity::class.java)
                Log.d("DTAILACT", dayInput.text.toString());
                val day = BitFit(dayInput.text.toString(), sleepInput.text.toString())
                intent.putExtra(EXTRA_ENTRY, day)
                this.startActivity(intent)
            }
            else {
                Toast.makeText(this, "Please enter your favorite color!", Toast.LENGTH_SHORT).show()
            }

        }
    }
}