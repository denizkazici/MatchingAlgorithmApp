package com.deniz.term_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deniz.term_project.model.UserDataHolder
import com.deniz.term_project.model.user

class ProfileViewModel : ViewModel() {
    val userLiveData = MutableLiveData<user?>()

    fun userVerisi(){
        val User = UserDataHolder.getUser()
        userLiveData.value = User
    }

}