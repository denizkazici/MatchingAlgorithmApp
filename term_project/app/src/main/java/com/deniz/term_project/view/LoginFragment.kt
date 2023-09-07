package com.deniz.term_project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.deniz.term_project.R
import com.deniz.term_project.model.UserDataHolder
import com.deniz.term_project.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_login, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        view.findViewById<Button>(R.id.btnLogin).setOnClickListener {
            val name = view.findViewById<EditText>(R.id.etName).text.toString()
            val password = view.findViewById<EditText>(R.id.etPassword).text.toString()
            performLogin(name, password)
            /*val myUser = UserDataHolder.getUser()
            if(myUser.value!!.user_type == 0){
                //normal kullanıcı
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
            else{
                //admin
            }*/
            observeUserData()
        }


    }
    private fun performLogin(name: String, password: String) {
        // ViewModel üzerinden giriş işlemini yap
        viewModel.getVeri(name, password)
    }

    private fun observeUserData() {
        viewModel.user.observe(viewLifecycleOwner) { user ->
            UserDataHolder.setUser(user)
            if(user.user_type == 1 || user.user_type == 0){
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
            else if(user.user_type ==2 ){
                findNavController().navigate(R.id.action_loginFragment_to_adminFragment)
            }
        }
        /*viewModel.user.observe(viewLifecycleOwner, { user ->
            user?.let {
                val myUser = UserDataHolder.getUser().value
                if (myUser != null && myUser.user_type == 0) {
                    // Normal kullanıcı
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    // Admin
                }
            }
        })*/
    }
}