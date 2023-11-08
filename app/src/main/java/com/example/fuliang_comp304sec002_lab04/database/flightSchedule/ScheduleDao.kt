package com.example.fuliang_comp304sec002_lab04.database.flightSchedule

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Query("select * from schedule order by arrival_time ASC")
    fun getAll(): Flow<List<Schedule>>

    @Query("select * from schedule where airline = :airlineName order by arrival_time asc")
    fun getByAirlineName(airlineName: String): Flow<List<Schedule>>
}