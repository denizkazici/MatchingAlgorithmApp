package com.deniz.term_project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deniz.term_project.R
import com.deniz.term_project.adapters.roadListAdapter
import com.deniz.term_project.viewmodel.RoadViewModel
import com.deniz.term_project.viewmodel.TeamViewModel

class RoadFragment : Fragment() {
    private lateinit var viewModel: RoadViewModel
    private val recyclerRoadListAdapter = roadListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_road, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(RoadViewModel::class.java)
        viewModel.refreshData()
        view.findViewById<Button>(R.id.backBtn).setOnClickListener{
            findNavController().navigate(R.id.action_roadFragment_to_adminFragment)
        }

        val teamListRecycler = view.findViewById<RecyclerView>(R.id.roadList)
        teamListRecycler.layoutManager = LinearLayoutManager(context)
        teamListRecycler.adapter = recyclerRoadListAdapter

        observeRoadList()



    }
    private fun observeRoadList() {
        viewModel.roadList.observe(viewLifecycleOwner, Observer { roadList ->
            if (roadList != null) {

                recyclerRoadListAdapter.listeUpdate(roadList)
                requireView().findViewById<RecyclerView>(R.id.roadList).visibility = View.VISIBLE
            }
        })

    }


}