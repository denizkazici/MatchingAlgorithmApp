package com.deniz.term_project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.deniz.term_project.R
import com.deniz.term_project.model.UserDataHolder
import com.deniz.term_project.model.user
import com.deniz.term_project.viewmodel.UserDetailViewModel
import kotlinx.coroutines.launch


class UserDetailFragment : Fragment() {
    private lateinit var viewModel: UserDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserDetailViewModel::class.java)
        view.findViewById<Button>(R.id.backBtn).setOnClickListener{
            findNavController().navigate(R.id.action_userDetailFragment_to_adminFragment)
        }
        view.findViewById<Button>(R.id.getBtn).setOnClickListener {
            val id = view.findViewById<EditText>(R.id.etId).text.toString()
            performUser(id)
            observeUserData(view)
        }




    }
    private fun performUser(id : String) {
        // ViewModel üzerinden giriş işlemini yap
        viewModel.getUser(id)
    }
    private fun observeUserData(view : View) {
        viewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                // Kullanıcı verilerini görüntüleyin veya işleyin
                view.findViewById<EditText>(R.id.etName).setText(user.name)
                view.findViewById<EditText>(R.id.etPassword).setText(user.password)
                user.team_id?.let { view.findViewById<EditText>(R.id.etTeamId).setText(it.toString()) }
                user.age?.let { view.findViewById<EditText>(R.id.etAge).setText(it.toString()) }
                view.findViewById<EditText>(R.id.etInfo).setText(user.info)
                view.findViewById<EditText>(R.id.etLat).setText(user.lat)
                view.findViewById<EditText>(R.id.etLng).setText(user.lng)
                user.user_type?.let { view.findViewById<EditText>(R.id.etUsertype).setText(it.toString()) }
                user.building_id?.let { view.findViewById<EditText>(R.id.etBuildingid).setText(it.toString()) }
                // vb.
            } else {
                Toast.makeText(requireContext(), "Kullanıcı bulunamadı", Toast.LENGTH_SHORT).show()

            }
        }
    }


}