package com.example.ddassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Switch
import android.widget.TextView
import kotlin.random.Random
private lateinit var d4Text: TextView
private lateinit var d6Text: TextView
private lateinit var d8Text: TextView
private lateinit var d10Text: TextView
private lateinit var d12Text: TextView
private lateinit var d20Text: TextView
private lateinit var d100Text: TextView
private lateinit var allSwitch: Switch
class Dices : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dices)
    }

    override fun onStart() {
        super.onStart()
        val rollDices = findViewById<Button>(R.id.btn_activity_dices_roll)
        allSwitch = findViewById<Switch>(R.id.switch_activity_dices_all)
        allSwitch.isChecked=true
        switchIsCheked()

        var ch4= findViewById<CheckBox>(R.id.chec_img_activity_dices_d4)
        val ch6= findViewById<CheckBox>(R.id.chec_img_activity_dices_d6)
        val ch8= findViewById<CheckBox>(R.id.chec_img_activity_dices_d8)
        val ch10= findViewById<CheckBox>(R.id.chec_img_activity_dices_d10)
        val ch12= findViewById<CheckBox>(R.id.chec_img_activity_dices_d12)
        val ch20= findViewById<CheckBox>(R.id.chec_img_activity_dices_d20)
        val ch100= findViewById<CheckBox>(R.id.chec_img_activity_dices_d100)
        var d100 :Int

        allSwitch.setOnClickListener {
            if (allSwitch.isChecked){
                switchIsCheked()
            }else{
                switchIsNoCheked()
                cleartext()
            }

        }

        rollDices.setOnClickListener{
            cleartext()
            if (allSwitch.isChecked){
                rollAll()
            }else{
                d4Text = findViewById(R.id.txt_activity_dices_d4)
                d6Text = findViewById(R.id.txt_activity_dices_d6)
                d8Text = findViewById(R.id.txt_activity_dices_d8)
                d10Text = findViewById(R.id.txt_activity_dices_d10)
                d12Text = findViewById(R.id.txt_activity_dices_d12)
                d20Text = findViewById(R.id.txt_activity_dices_d20)
                d100Text = findViewById(R.id.txt_activity_dices_d100)
                if (ch4.isChecked) d4Text.text="${rolld(5)}"
                if (ch6.isChecked) d6Text.text="${rolld(7)}"
                if (ch8.isChecked) d8Text.text="${rolld(8)}"
                if (ch10.isChecked) d10Text.text="${rolld(11)}"
                if (ch12.isChecked) d12Text.text="${rolld(13)}"
                if (ch20.isChecked) d20Text.text="${rolld(21)}"
                if (ch100.isChecked){
                    d100 =rolld100()
                    if(d100==0){
                        d100Text.text="00"
                    } else d100Text.text="$d100"
                }

            }

        }

    }

    private fun rollAll(){
        d4Text = findViewById(R.id.txt_activity_dices_d4)
        d6Text = findViewById(R.id.txt_activity_dices_d6)
        d8Text = findViewById(R.id.txt_activity_dices_d8)
        d10Text = findViewById(R.id.txt_activity_dices_d10)
        d12Text = findViewById(R.id.txt_activity_dices_d12)
        d20Text = findViewById(R.id.txt_activity_dices_d20)
        d100Text = findViewById(R.id.txt_activity_dices_d100)
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

    private fun rolld(d:Int): Int {
        return Random.nextInt(1, d)
    }
    private fun rolld100(): Int {
        return Random.nextInt(0, 10)*10
    }

    private fun cleartext(){
        d4Text = findViewById(R.id.txt_activity_dices_d4)
        d6Text = findViewById(R.id.txt_activity_dices_d6)
        d8Text = findViewById(R.id.txt_activity_dices_d8)
        d10Text = findViewById(R.id.txt_activity_dices_d10)
        d12Text = findViewById(R.id.txt_activity_dices_d12)
        d20Text = findViewById(R.id.txt_activity_dices_d20)
        d100Text = findViewById(R.id.txt_activity_dices_d100)
        d4Text.text=""
        d6Text.text=""
        d8Text.text=""
        d10Text.text=""
        d12Text.text=""
        d20Text.text=""
        d100Text.text=""
    }

    private fun switchIsCheked(){
        var ch4= findViewById<CheckBox>(R.id.chec_img_activity_dices_d4)
        val ch6= findViewById<CheckBox>(R.id.chec_img_activity_dices_d6)
        val ch8= findViewById<CheckBox>(R.id.chec_img_activity_dices_d8)
        val ch10= findViewById<CheckBox>(R.id.chec_img_activity_dices_d10)
        val ch12= findViewById<CheckBox>(R.id.chec_img_activity_dices_d12)
        val ch20= findViewById<CheckBox>(R.id.chec_img_activity_dices_d20)
        val ch100= findViewById<CheckBox>(R.id.chec_img_activity_dices_d100)
        ch4.isEnabled=false
        ch6.isEnabled=false
        ch8.isEnabled=false
        ch10.isEnabled=false
        ch12.isEnabled=false
        ch20.isEnabled=false
        ch100.isEnabled=false
        ch4.isChecked=true
        ch6.isChecked=true
        ch8.isChecked=true
        ch10.isChecked=true
        ch12.isChecked=true
        ch20.isChecked=true
        ch100.isChecked=true
    }

    private fun  switchIsNoCheked(){
        var ch4= findViewById<CheckBox>(R.id.chec_img_activity_dices_d4)
        val ch6= findViewById<CheckBox>(R.id.chec_img_activity_dices_d6)
        val ch8= findViewById<CheckBox>(R.id.chec_img_activity_dices_d8)
        val ch10= findViewById<CheckBox>(R.id.chec_img_activity_dices_d10)
        val ch12= findViewById<CheckBox>(R.id.chec_img_activity_dices_d12)
        val ch20= findViewById<CheckBox>(R.id.chec_img_activity_dices_d20)
        val ch100= findViewById<CheckBox>(R.id.chec_img_activity_dices_d100)
        ch4.isEnabled=true
        ch6.isEnabled=true
        ch8.isEnabled=true
        ch12.isEnabled=true
        ch10.isEnabled=true
        ch20.isEnabled=true
        ch100.isEnabled=true
        ch4.isChecked=false
        ch6.isChecked=false
        ch8.isChecked=false
        ch10.isChecked=false
        ch12.isChecked=false
        ch20.isChecked=false
        ch100.isChecked=false
    }
}