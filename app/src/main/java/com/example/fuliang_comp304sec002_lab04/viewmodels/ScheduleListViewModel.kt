package com.example.fuliang_comp304sec002_lab04.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fuliang_comp304sec002_lab04.database.flightSchedule.Schedule
import com.example.fuliang_comp304sec002_lab04.database.flightSchedule.ScheduleDao
import kotlinx.coroutines.flow.Flow

class FlightScheduleViewModel(private val scheduleDao: ScheduleDao): ViewModel(){

    fun fullSchedule(): Flow<List<Schedule>> = scheduleDao.getAll()

    fun scheduleForAirlineName(name: String): Flow<List<Schedule>> = scheduleDao.getByAirlineName(name)

}

// view model provider for fragment/view
class FlightScheduleViewModelFactory(
    private val scheduleDao: ScheduleDao
) : ViewModelProvider.Factory{
    override fun<T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FlightScheduleViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return FlightScheduleViewModel(scheduleDao) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel Class")
    }

}