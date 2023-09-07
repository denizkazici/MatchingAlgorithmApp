package com.deniz.term_project.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deniz.term_project.R
import com.deniz.term_project.adapters.buildingListAdapter
import com.deniz.term_project.adapters.userListAdapter
import com.deniz.term_project.model.BuildingDataHolder
import com.deniz.term_project.model.UserDataHolder
import com.deniz.term_project.model.user
import com.deniz.term_project.viewmodel.BuildingViewModel
import com.deniz.term_project.viewmodel.userViewModel

class HomeFragment : Fragment() {
    private lateinit var viewModel: userViewModel
    private val recyclerUserListAdapter = userListAdapter()
    private val recyclerBuildingListAdapter = buildingListAdapter()
    private lateinit var viewModel2: BuildingViewModel
    val User = UserDataHolder.getUser()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("LocationChanged", "building_id: ${BuildingDataHolder.getBuilding()?.building_id}")

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        if (User != null) {
            if (User.user_type == 1){
                progressBar.visibility = View.GONE
                viewModel2 = ViewModelProvider(this).get(BuildingViewModel::class.java)
                viewModel2.refreshData()
                val buildingListRecycler = view.findViewById<RecyclerView>(R.id.buildingList)
                buildingListRecycler.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                buildingListRecycler.layoutManager = LinearLayoutManager(context)
                buildingListRecycler.adapter = recyclerBuildingListAdapter
                observeBuildingList()

            }
            else if(User.user_type == 0){
                viewModel = ViewModelProvider(this).get(userViewModel::class.java)
                viewModel.refreshData()
                val userListRecycler = view.findViewById<RecyclerView>(R.id.userList)
                userListRecycler.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                userListRecycler.layoutManager = LinearLayoutManager(context)
                userListRecycler.adapter = recyclerUserListAdapter
                observeUserList()
            }
        }

    }
    private fun observeUserList() {
        viewModel.userList.observe(viewLifecycleOwner, Observer { userList ->
            userList?.let {

                val newUserList: MutableList<user> = mutableListOf()
                for (item in userList) {
                    item.building_id?.let {
                        if ( it == User!!.building_id){
                            newUserList.add(item)
                        }
                    }
                }
                requireView().findViewById<RecyclerView>(R.id.userList).visibility = View.VISIBLE
                recyclerUserListAdapter.listeUpdate(newUserList)
            }
        })


    }
    private fun observeBuildingList() {
        viewModel2.buildingList.observe(viewLifecycleOwner, Observer { buildingList ->
            if (buildingList != null) {
                recyclerBuildingListAdapter.listeUpdate(buildingList)
                requireView().findViewById<RecyclerView>(R.id.buildingList).visibility = View.VISIBLE
            }
        })

    }


}