package com.deniz.term_project.model

import androidx.lifecycle.MutableLiveData

data class user(
    val age: Int?,
    val building_id : Int?,
    val created_at: String?,
    val info : String?,
    var lat : String?,
    var lng : String?,
    val name : String?,
    val password : String?,
    val team_id : Int?,
    val updated_at: String?,
    val user_id : Int?,
    val user_type : Int?,
){

}
data class LoginRequest(
    val name: String,
    val password: String
)

object UserDataHolder {
    private val userData = MutableLiveData<user>()

    fun setUser(user: user) {
        userData.value = user
    }
    fun setLatLng (lat: String?, lng: String?){
        userData.value!!.lat = lat
        userData.value!!.lng = lng
    }

    fun getUser(): user? {
        val userNullable: user? = userData.value
        return userNullable
    }
}
