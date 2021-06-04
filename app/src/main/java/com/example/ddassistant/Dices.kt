package com.example.ddassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class Dices : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dices)
    }

    override fun onStart() {
        super.onStart()
        val rollDices =findViewById<Button>(R.id.btn_activity_dices_roll)
        val d4Text = findViewById<TextView>(R.id.txt_activity_dices_d4)
        val d6Text = findViewById<TextView>(R.id.txt_activity_dices_d6)
        val d8Text = findViewById<TextView>(R.id.txt_activity_dices_d8)
        val d10Text = findViewById<TextView>(R.id.txt_activity_dices_d10)
        val d12Text = findViewById<TextView>(R.id.txt_activity_dices_d12)
        val d20Text = findViewById<TextView>(R.id.txt_activity_dices_d20)
        val d100Text = findViewById<TextView>(R.id.txt_activity_dices_d100)

        rollDices.setOnClickListener{
            d4Text.text="${rolld(5)}"
            d6Text.text="${rolld(7)}"
            d8Text.text="${rolld(8)}"
            d10Text.text="${rolld(11)}"
            d12Text.text="${rolld(13)}"
            d20Text.text="${rolld(21)}"
            if(rolld100()==0){
                d100Text.text="00"
            } else d100Text.text="${rolld100()}"
        }

    }


    private fun rolld(d:Int): Int {
        return Random.nextInt(1, d)
    }
    private fun rolld100(): Int {
        return Random.nextInt(0, 10)*10

    }
}