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
import com.deniz.term_project.adapters.teamListAdapter
import androidx.lifecycle.Observer
import com.deniz.term_project.viewmodel.TeamViewModel


class TeamFragment : Fragment() {
    private lateinit var viewModel: TeamViewModel
    private val recyclerTeamListAdapter = teamListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(TeamViewModel::class.java)
        viewModel.refreshData()
        view.findViewById<Button>(R.id.backBtn).setOnClickListener{
            findNavController().navigate(R.id.action_teamFragment_to_adminFragment)
        }

        val teamListRecycler = view.findViewById<RecyclerView>(R.id.teamList)
        teamListRecycler.layoutManager = LinearLayoutManager(context)
        teamListRecycler.adapter = recyclerTeamListAdapter

        observeTeamList()



    }
    private fun observeTeamList() {
        viewModel.teamList.observe(viewLifecycleOwner, Observer { teamList ->
            if (teamList != null) {

                recyclerTeamListAdapter.listeUpdate(teamList)
                requireView().findViewById<RecyclerView>(R.id.teamList).visibility = View.VISIBLE
            }
        })

    }

}