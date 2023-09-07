package com.deniz.term_project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deniz.term_project.R
import com.deniz.term_project.adapters.buildingListAdapter
import com.deniz.term_project.adapters.groupListAdapter
import com.deniz.term_project.adapters.groupedBuildingListAdapter
import com.deniz.term_project.viewmodel.BuildingViewModel
import com.deniz.term_project.viewmodel.GroupViewModel


class GroupFragment : Fragment() {

    private lateinit var viewModel: GroupViewModel
    private val recyclerGroupListAdapter = groupListAdapter()
    private val recyclergroupBuildingAdapter = groupedBuildingListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(GroupViewModel::class.java)
        viewModel.refreshData()
        val groupListRecycler = view.findViewById<RecyclerView>(R.id.groupList)
        val buildingListRecycler = view.findViewById<RecyclerView>(R.id.groupedBuildingList)
        view.findViewById<Button>(R.id.backBtn).setOnClickListener{
            findNavController().navigate(R.id.action_groupFragment_to_adminFragment)
        }
        view.findViewById<Button>(R.id.getGroupBtn).setOnClickListener {
            groupListRecycler.visibility = View.GONE
            buildingListRecycler.visibility = View.VISIBLE
            buildingListRecycler.layoutManager = LinearLayoutManager(context)
            buildingListRecycler.adapter = recyclergroupBuildingAdapter
            val id = view.findViewById<EditText>(R.id.etGroupId).text.toString()
            if (id == null){
                Toast.makeText(requireContext(), "please enter group id ", Toast.LENGTH_LONG).show()
            }
            else{
                viewModel.groupedList(id)
                observeGroupedBuildingList()
            }

        }

        groupListRecycler.layoutManager = LinearLayoutManager(context)
        groupListRecycler.adapter = recyclerGroupListAdapter

        observeGroupList()



    }

    private fun observeGroupList() {
        viewModel.groupList.observe(viewLifecycleOwner, Observer { groupList ->
            if (groupList != null) {
                recyclerGroupListAdapter.listeUpdate(groupList)
                requireView().findViewById<RecyclerView>(R.id.groupList).visibility = View.VISIBLE
            }
        })
    }
    private fun observeGroupedBuildingList() {
        viewModel.groupedList.observe(viewLifecycleOwner, Observer { groupedList ->
            if (groupedList != null) {
                recyclergroupBuildingAdapter.listeUpdate(groupedList)
                requireView().findViewById<RecyclerView>(R.id.groupedBuildingList).visibility = View.VISIBLE
            }
        })
    }

}