package com.deniz.term_project.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.deniz.term_project.R
import com.deniz.term_project.model.Algorithm
import com.deniz.term_project.model.AlgorithmDataHolder
import com.deniz.term_project.model.BuildingDataHolder
import com.deniz.term_project.model.team
import com.deniz.term_project.viewmodel.AdminViewModel
import com.deniz.term_project.viewmodel.LoginViewModel


class AdminFragment : Fragment() {

    private lateinit var viewModel: AdminViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdminViewModel::class.java)
        val progressBar = view.findViewById<ProgressBar>(R.id.progress)
        view.findViewById<Button>(R.id.userBtn).setOnClickListener {
            findNavController().navigate(R.id.action_adminFragment_to_userFragment)
        }
        view.findViewById<Button>(R.id.buildingBtn).setOnClickListener {
            findNavController().navigate(R.id.action_adminFragment_to_buildingFragment)
        }
        view.findViewById<Button>(R.id.teamBtn).setOnClickListener {
            findNavController().navigate(R.id.action_adminFragment_to_teamFragment)
        }
        view.findViewById<Button>(R.id.roadBtn).setOnClickListener {
            findNavController().navigate(R.id.action_adminFragment_to_roadFragment)
        }
        view.findViewById<Button>(R.id.GroupBtn).setOnClickListener {
            findNavController().navigate(R.id.action_adminFragment_to_groupFragment)
        }
        view.findViewById<Button>(R.id.deleteBtn).setOnClickListener {
            //AlgorithmDataHolder.deleteData()
            progressBar.visibility = View.VISIBLE
            viewModel.delete()
            observeBuildingData(view)
        }
        view.findViewById<Button>(R.id.createBuildingBtn).setOnClickListener {
            val lat = view.findViewById<EditText>(R.id.etLat).getText().toString()
            val lng = view.findViewById<EditText>(R.id.etLong).getText().toString()
            val distance = view.findViewById<EditText>(R.id.etDistance).getText().toString()
            val count = view.findViewById<EditText>(R.id.etCount).getText().toString()
            //AlgorithmDataHolder.setData(Algorithm(lat, lng, distance))
            viewModel.createBuildingList(lat, lng, distance, count)
            observeBuildingData(view)
        }
        view.findViewById<Button>(R.id.createUserBtn).setOnClickListener {
            progressBar.visibility = View.VISIBLE
            viewModel.createUserList()
            observeBuildingData(view)
        }
        view.findViewById<Button>(R.id.createTeamBtn).setOnClickListener {
            //val lat = view.findViewById<EditText>(R.id.etLat).getText().toString()
            //val lng = view.findViewById<EditText>(R.id.etLong).getText().toString()
            //val distance = view.findViewById<EditText>(R.id.etDistance).getText().toString()
            viewModel.getEarthquake()
            observeAlgorithmData(view)
            if(AlgorithmDataHolder.getData() == null){
                Toast.makeText(requireContext(), "first create building list", Toast.LENGTH_LONG).show()
            }
            else{
                val lat = AlgorithmDataHolder.getData()!!.merkez_lat.toString()
                val lng = AlgorithmDataHolder.getData()!!.merkez_lng.toString()
                val distance = AlgorithmDataHolder.getData()!!.distance.toString()
                val count = view.findViewById<EditText>(R.id.etCount).getText().toString()
                viewModel.createTeamList(lat, lng, distance, count)
                observeBuildingData(view)
            }


        }
        view.findViewById<Button>(R.id.createGroupBtn).setOnClickListener {
            viewModel.createGroupList()
            observeBuildingData(view)
        }
        view.findViewById<Button>(R.id.createRoadBtn).setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val teamId = view.findViewById<EditText>(R.id.etTeamId).getText().toString()
            viewModel.createRoadList(teamId)
            observeBuildingData(view)
        }
        view.findViewById<Button>(R.id.UpdateUserBtn).setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val UserId = view.findViewById<EditText>(R.id.etUserId).getText().toString()
            val UserName = view.findViewById<EditText>(R.id.etUserName).getText().toString()
            val UserPassword = view.findViewById<EditText>(R.id.etUserPassword).getText().toString()
            if (UserId == null && UserName == null && UserPassword == null){
                progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "please enter id, name, password ", Toast.LENGTH_LONG).show()
            }
            else{
                viewModel.change(UserId,UserName,UserPassword)
                observeBuildingData(view)
            }
        }
        view.findViewById<Button>(R.id.testBtn).setOnClickListener {
            val teamId = view.findViewById<EditText>(R.id.etTeam_id).getText().toString()
            viewModel.test(teamId)
            observeData(view)
        }
        view.findViewById<Button>(R.id.completeBtn).setOnClickListener {
            val team_id = view.findViewById<EditText>(R.id.etcompleteTeam).getText().toString()
            val building_id = view.findViewById<EditText>(R.id.etCompleteBuilding).getText().toString()
            viewModel.complete(building_id, team_id)
            observeBuildingData(view)
        }
    }
    private fun observeBuildingData(view : View) {
        viewModel.Response.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                view.findViewById<ProgressBar>(R.id.progress).visibility = View.GONE
                Toast.makeText(requireContext(), response.message, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "liste did not create", Toast.LENGTH_SHORT).show()

            }
        }
    }
    private fun observeAlgorithmData(view: View){
        viewModel.algorithm.observe(viewLifecycleOwner){ algorithm ->
            AlgorithmDataHolder.setData(algorithm)
            Toast.makeText(requireContext(), algorithm.merkez_lat, Toast.LENGTH_LONG).show()
        }
    }
    private fun observeData(view : View) {
        viewModel.building.observe(viewLifecycleOwner) { building ->
            view.findViewById<LinearLayout>(R.id.layout).visibility = View.VISIBLE
            if (building != null) {
                view.findViewById<TextView>(R.id.tvBuildingId).text = building.building_id.toString()
                view.findViewById<TextView>(R.id.tvAdress).text = building.address
                view.findViewById<TextView>(R.id.tvTime).text = building.time.toString()
                view.findViewById<TextView>(R.id.tvPersoncount).text = building.person_count.toString()
                view.findViewById<TextView>(R.id.tvCreated).text = building.created_at
                view.findViewById<TextView>(R.id.tvCount).text = building.count.toString()
                view.findViewById<TextView>(R.id.tvDestroyedRoad).text = building.road
            } else {
                Toast.makeText(requireContext(), "building is null", Toast.LENGTH_LONG).show()
            }
        }
    }

}