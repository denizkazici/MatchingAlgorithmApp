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
import com.deniz.term_project.adapters.userListAdapter
import com.deniz.term_project.viewmodel.userViewModel


class UserFragment : Fragment() {
    private lateinit var viewModel: userViewModel
    private val recyclerUserListAdapter = userListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(userViewModel::class.java)
        viewModel.refreshData()
        view.findViewById<Button>(R.id.backBtn).setOnClickListener{
            findNavController().navigate(R.id.action_userFragment_to_adminFragment)
        }
        view.findViewById<Button>(R.id.userBtn).setOnClickListener{
            findNavController().navigate(R.id.action_userFragment_to_userDetailFragment)
        }
        val userListRecycler = view.findViewById<RecyclerView>(R.id.userList)
        userListRecycler.layoutManager = LinearLayoutManager(context)
        userListRecycler.adapter = recyclerUserListAdapter

        observeUserList()



    }



    private fun observeUserList() {
        viewModel.userList.observe(viewLifecycleOwner, Observer { userList ->
            userList?.let {
                requireView().findViewById<RecyclerView>(R.id.userList).visibility = View.VISIBLE
                recyclerUserListAdapter.listeUpdate(userList)
            }
        })

    }

}