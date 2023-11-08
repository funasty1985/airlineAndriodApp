package com.example.fuliang_comp304sec002_lab04

import android.app.Application
import com.example.fuliang_comp304sec002_lab04.database.AppDatabase

//you need to make a small change to the manifest. In AndroidMainifest.xml, set the android:name property
class FlightScheduleApplication: Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}