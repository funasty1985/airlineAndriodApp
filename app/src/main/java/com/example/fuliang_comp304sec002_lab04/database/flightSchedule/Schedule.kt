package com.example.fuliang_comp304sec002_lab04.database.flightSchedule
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Schedule(
    @PrimaryKey val id:Int,
    @NonNull @ColumnInfo(name="airline") val airline: String,
    @NonNull @ColumnInfo(name="arrival_time") val arrivalTime: Int,
    @NonNull @ColumnInfo(name="terminal") val terminal: String,
    @NonNull @ColumnInfo(name="status") val status: String
)
