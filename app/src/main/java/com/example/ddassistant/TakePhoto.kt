package com.example.ddassistant

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class TakePhoto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_photo)
        val calIntent = Intent(Intent.ACTION_INSERT)
        calIntent.type = "vnd.android.cursor.item/event"
        calIntent.putExtra(Events.TITLE, "My House Party")
        calIntent.putExtra(Events.EVENT_LOCATION, "My Beach House")
        calIntent.putExtra(Events.DESCRIPTION, "A Pig Roast on the Beach")

        val calDate = GregorianCalendar(2021, 5, 23)
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                calDate.timeInMillis)
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                calDate.timeInMillis)

        startActivity(calIntent)
    }
}