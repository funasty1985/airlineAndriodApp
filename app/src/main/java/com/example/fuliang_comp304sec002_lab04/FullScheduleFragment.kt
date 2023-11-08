package com.example.fuliang_comp304sec002_lab04

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fuliang_comp304sec002_lab04.databinding.FullScheduleFragmentBinding
import com.example.fuliang_comp304sec002_lab04.viewmodels.FlightScheduleViewModelFactory
import com.example.fuliang_comp304sec002_lab04.viewmodels.FlightScheduleViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FullScheduleFragment : Fragment() {

    // data binding
    private var _binding: FullScheduleFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    // getting viewModel using the view model factory class
    private val viewModel: FlightScheduleViewModel by activityViewModels{
        FlightScheduleViewModelFactory(
            (activity?.application as FlightScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FullScheduleFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        // set up the recycler view and assign its layout manage
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val flightScheduleAdapter = FlightScheduleAdapter({
            val action = FullScheduleFragmentDirections
                .actionFullScheduleFragmentToStopScheduleFragment(
                    airlineName = it.airline
                )
            view.findNavController().navigate(action)
        })
        recyclerView.adapter = flightScheduleAdapter

        lifecycle.coroutineScope.launch {
            viewModel.fullSchedule().collect() {
                flightScheduleAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}