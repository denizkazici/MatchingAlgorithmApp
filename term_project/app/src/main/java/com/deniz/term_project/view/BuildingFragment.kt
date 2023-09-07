package com.deniz.term_project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deniz.term_project.R
import com.deniz.term_project.adapters.buildingListAdapter
import androidx.lifecycle.Observer
import com.deniz.term_project.viewmodel.BuildingViewModel

class BuildingFragment : Fragment() {

    private lateinit var viewModel: BuildingViewModel
    private val recyclerBuildingListAdapter = buildingListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_building, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BuildingViewModel::class.java)
        viewModel.refreshData()
        view.findViewById<Button>(R.id.backBtn).setOnClickListener{
            findNavController().navigate(R.id.action_buildingFragment_to_adminFragment)
        }

        val buildingListRecycler = view.findViewById<RecyclerView>(R.id.buildingList)
        buildingListRecycler.layoutManager = LinearLayoutManager(context)
        buildingListRecycler.adapter = recyclerBuildingListAdapter

        observeBuildingList()



    }
    private fun observeBuildingList() {
        viewModel.buildingList.observe(viewLifecycleOwner, Observer { buildingList ->
            if (buildingList != null) {
                recyclerBuildingListAdapter.listeUpdate(buildingList)
                requireView().findViewById<RecyclerView>(R.id.buildingList).visibility = View.VISIBLE
            }
        })

    }

}