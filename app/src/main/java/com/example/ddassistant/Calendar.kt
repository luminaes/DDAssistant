package com.example.ddassistant

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.Calendar


class Calendar : AppCompatActivity() {
    var yearInt :Int = 0
    var monthInt :Int =0
    var dayInt :Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
    }


    override fun onStart() {
        super.onStart()
        val addEventButton = findViewById<Button>(R.id.btn_activity_calendar_add_event)
        val dateSelect = findViewById<TextView>(R.id.txt_activity_calendar_select_date)
        //Cal val
        val cal =Calendar.getInstance()
        val myyear = cal.get(Calendar.YEAR)
        val mymonth = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)



        //var yearInt :Int



        dateSelect.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this,DatePickerDialog.OnDateSetListener {view, year, month, dayOfMonth ->
                dateSelect.text= "Fecha: "+ dayOfMonth + "/ " +(month+1) + "/ " + year
                yearInt = year
                monthInt=month
                dayInt= dayOfMonth
            },myyear,mymonth,day)
            datePickerDialog.show()
        }




        addEventButton.setOnClickListener {
            val titleText = findViewById<EditText>(R.id.txt_activity_calendar_title).text.toString()
            val placeText = findViewById<EditText>(R.id.txt_activity_calendar_place).text.toString()
            val descripText = findViewById<EditText>(R.id.txt_activity_calendar_description).text.toString()
            val calIntent = Intent(Intent.ACTION_INSERT)
            val switchPrivate = findViewById<Switch>(R.id.switch_activity_calendar_private)

            calIntent.type = "vnd.android.cursor.item/event"
            calIntent.putExtra(Events.TITLE, titleText)
            calIntent.putExtra(Events.EVENT_LOCATION, placeText)
            calIntent.putExtra(Events.DESCRIPTION, descripText)
            if (switchPrivate.isChecked){ calIntent.putExtra(Events.ACCESS_LEVEL, Events.ACCESS_PRIVATE)
            } else calIntent.putExtra(Events.ACCESS_LEVEL, Events.ACCESS_PUBLIC)

            calIntent.putExtra(Events.RRULE,"FREQ=WEEKLY")


            val calDate = GregorianCalendar(yearInt, monthInt, dayInt)
            calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
            calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calDate.timeInMillis)
            calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calDate.timeInMillis)

            startActivity(calIntent)
        }
    }

    private fun getDate(){


    }



}