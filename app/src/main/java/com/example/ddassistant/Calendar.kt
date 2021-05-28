package com.example.ddassistant

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.Calendar


class Calendar : AppCompatActivity() {
    var yearStart :Int = 0
    var monthStart :Int =0
    var dayStart :Int =0
    var hourStart :Int =12
    var minuteStart :Int =0

    var yearEnd :Int = 0
    var monthEnd :Int =0
    var dayEnd:Int =0
    var hourEnd :Int=12
    var minuteEnd: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
    }


    override fun onStart() {
        super.onStart()
        val addEventButton = findViewById<Button>(R.id.btn_activity_calendar_add_event)
        var dateSelectStart = findViewById<TextView>(R.id.txt_activity_calendar_select_start_date)
        var dateSelectEnd = findViewById<TextView>(R.id.txt_activity_calendar_select_end_date)
        val timeSelect =findViewById<TextView>(R.id.txt_activity_calendar_select_start_time)
        val allDaySwitch =findViewById<Switch>(R.id.switch_activity_calendar_all_day)
        //Cal val


        //val myhour = cal.get(Calendar.HOUR)
        //val myminute = cal.get(Calendar.MINUTE)


        dateSelectStart.setOnClickListener {
            pickDate(true)
            /*val datePickerDialog = DatePickerDialog(this,DatePickerDialog.OnDateSetListener {view, year, month, dayOfMonth ->
                dateSelect.text= "Fecha: "+ dayOfMonth + "/ " +(month+1) + "/ " + year
                yearInt = year
                monthInt=month
                dayInt= dayOfMonth
            },myyear,mymonth,day)
            datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
            datePickerDialog.show()*/
        }
        dateSelectEnd.setOnClickListener {
            pickDate(false)
        }


        timeSelect.setOnClickListener {
           /* val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                if(minute <10 ){timeSelect.text = "Hora " + hourOfDay +":0"+ minute
                }else timeSelect.text = "Hora " + hourOfDay +":"+ minute
                hourStart = hourOfDay
                minuteStart =minute
            }, myhour, myminute, false)
            timePickerDialog.show()*/
        }

        allDaySwitch.setOnClickListener {
            if (allDaySwitch.isChecked){ timeSelect.text = ""
                timeSelect.isClickable = false
            }else{ timeSelect.text ="Seleccionar Hora"
                timeSelect.isClickable = true
            }
        }


        addEventButton.setOnClickListener {
            val titleText = findViewById<EditText>(R.id.txt_activity_calendar_title).text.toString()
            val placeText = findViewById<EditText>(R.id.txt_activity_calendar_place).text.toString()
            val descripText = findViewById<EditText>(R.id.txt_activity_calendar_description).text.toString()
            val calIntent = Intent(Intent.ACTION_INSERT)
            val switchPrivate = findViewById<Switch>(R.id.switch_activity_calendar_private)
            val switchWeekly = findViewById<Switch>(R.id.switch_activity_calendar_weekly)
            val switchAllDay = findViewById<Switch>(R.id.switch_activity_calendar_all_day)
            calIntent.type = "vnd.android.cursor.item/event"
            calIntent.putExtra(Events.TITLE, titleText)
            calIntent.putExtra(Events.EVENT_LOCATION, placeText)
            calIntent.putExtra(Events.DESCRIPTION, descripText)
            //privado o publico
            if (switchPrivate.isChecked){ calIntent.putExtra(Events.ACCESS_LEVEL, Events.ACCESS_PRIVATE)
            } else calIntent.putExtra(Events.ACCESS_LEVEL, Events.ACCESS_PUBLIC)
            //repeticion
            // semanalmente 3 meses
           if(switchWeekly.isChecked)calIntent.putExtra(Events.RRULE,"FREQ=WEEKLY;COUNT=12")

            val calDate = GregorianCalendar(yearStart, monthStart, dayStart,hourStart,minuteStart)
            val calDateEnd =GregorianCalendar(yearEnd,monthEnd,dayEnd,hourEnd,minuteEnd)
            //todo el dia
            if (switchAllDay.isChecked){ calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
            }else calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)

            calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calDate.timeInMillis)
            calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calDateEnd.timeInMillis)

            startActivity(calIntent)
        }
    }

    private fun pickDate(startEnd: Boolean){
        val cal =Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        var dateSelectStart = findViewById<TextView>(R.id.txt_activity_calendar_select_start_date)
        var dateSelectEnd = findViewById<TextView>(R.id.txt_activity_calendar_select_end_date)
        val datePickerDialog = DatePickerDialog(this,DatePickerDialog.OnDateSetListener {view, year, month, dayOfMonth ->
            if(startEnd){
                dateSelectStart.text= "Fecha: "+ dayOfMonth + "/ " +(month+1) + "/ " + year
                yearStart = year
                monthStart=month
                dayStart= dayOfMonth
            }else{
                dateSelectEnd.text= "Fecha: "+ dayOfMonth + "/ " +(month+1) + "/ " + year
                yearEnd = year
                monthEnd=month
                dayEnd= dayOfMonth
            }
        },year,month,day)

        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
        datePickerDialog.show()


    }



}