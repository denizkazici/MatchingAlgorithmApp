package com.deniz.term_project.model

import androidx.lifecycle.MutableLiveData

data class building(
    val address: String?,
    val building_id: Int?,
    val completed: Int?,
    val count: Int?,
    val created_at: String?,
    val lat: String?,
    val lng: String?,
    val matches: Int?, // Eğer uygun bir tür bilgisi varsa onu burada belirtebilirsiniz
    val name: String?,
    val person_count: Int?,
    val updated_at: String?,
    val group_id: Int?,
    val time: Int?,
    val group_count: Int?,
    val road : String?
){

}
object BuildingDataHolder {
    private val buildingData = MutableLiveData<building>()

    fun setBuilding(Building: building) {
        buildingData.value = Building
    }

    fun getBuilding(): building? {
        val buildingNullable: building? = buildingData.value
        return buildingNullable
    }
}

data class ResponseMessage(
    val message: String
)
