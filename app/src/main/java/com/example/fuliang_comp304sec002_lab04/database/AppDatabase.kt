package com.example.fuliang_comp304sec002_lab04.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fuliang_comp304sec002_lab04.database.flightSchedule.Schedule
import com.example.fuliang_comp304sec002_lab04.database.flightSchedule.ScheduleDao

@Database(entities = arrayOf(Schedule::class), version=2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun scheduleDao(): ScheduleDao

    companion object {

        private var INSTANCE: AppDatabase?= null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
//                    .fallbackToDestructiveMigration()
                    .createFromAsset("database/app_database.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }

}