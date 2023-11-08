package com.example.fuliang_comp304sec002_lab04

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fuliang_comp304sec002_lab04.databinding.FragmentSingleFlightInfoBinding
import com.example.fuliang_comp304sec002_lab04.viewmodels.FlightScheduleViewModel
import com.example.fuliang_comp304sec002_lab04.viewmodels.FlightScheduleViewModelFactory
import kotlinx.coroutines.launch

class SingleFlightInfoFragment : Fragment() {

    companion object {
        var AIRLINE_NAME = "airlineName"
    }

    private var _binding: FragmentSingleFlightInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var airlineName: String

    private val viewModel: FlightScheduleViewModel by activityViewModels {
        FlightScheduleViewModelFactory(
            (activity?.application as FlightScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        arguments?.let {
            airlineName = it.getString(AIRLINE_NAME).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSingleFlightInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val flightScheduleAdapter = FlightScheduleAdapter({})
        recyclerView.adapter = flightScheduleAdapter

        lifecycle.coroutineScope.launch {
            viewModel.scheduleForAirlineName(airlineName).collect() {
                flightScheduleAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}