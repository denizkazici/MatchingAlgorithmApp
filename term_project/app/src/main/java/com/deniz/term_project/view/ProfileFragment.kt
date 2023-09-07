package com.deniz.term_project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.deniz.term_project.R
import com.deniz.term_project.viewmodel.ProfileViewModel
import androidx.lifecycle.Observer

class ProfileFragment : Fragment() {

    private lateinit var viewModel : ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.userVerisi()

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.userLiveData.observe(viewLifecycleOwner, Observer {
            it?.let{
                if(it.user_type == 0 ){
                    requireView().findViewById<TextView>(R.id.tvUsertype).setText("user")
                }
                else if(it.user_type == 1){
                    requireView().findViewById<TextView>(R.id.tvUsertype).setText("team")
                }
                requireView().findViewById<TextView>(R.id.tvName).text = it.name
                requireView().findViewById<TextView>(R.id.tvPassword).text = it.password
                requireView().findViewById<TextView>(R.id.tvInfo).text = it.info
                requireView().findViewById<TextView>(R.id.tvAge).text = it.age.toString()
                requireView().findViewById<TextView>(R.id.tvBuilding).text = it.building_id.toString()

            }
        })
    }



}