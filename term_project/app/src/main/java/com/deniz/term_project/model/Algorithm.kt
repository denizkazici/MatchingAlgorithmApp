package com.deniz.term_project.model

import androidx.lifecycle.MutableLiveData

data class Algorithm(
    val id : Int?,
    val merkez_lat : String?,
    val merkez_lng : String?,
    val distance : String?
){

}
object AlgorithmDataHolder {
    private val algorithmData = MutableLiveData<Algorithm?>()

    fun setData(algorithm: Algorithm) {
        algorithmData.value = algorithm
    }

    fun getData(): Algorithm? {
        val algorithmNullable: Algorithm? = algorithmData.value
        return algorithmNullable
    }
    fun deleteData() {
        algorithmData.value = null
    }
}
