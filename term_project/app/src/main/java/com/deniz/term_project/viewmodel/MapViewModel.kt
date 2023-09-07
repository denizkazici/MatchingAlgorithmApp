package com.deniz.term_project.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deniz.term_project.model.Algorithm
import com.deniz.term_project.model.ResponseMessage
import com.deniz.term_project.model.building
import com.deniz.term_project.service.services
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MapViewModel : ViewModel(){
    val building = MutableLiveData<building>()
    val Response = MutableLiveData<ResponseMessage>()
    val algorithm = MutableLiveData<Algorithm>()
    private val buildingService = services()

    fun refreshData(lat: String, lng: String, id: String? = null) {
        buildingService.getMatchingBuilding(lat, lng, id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Building ->
                    // userList Gson tarafından otomatik olarak dönüştürülmüş User listesidir
                    building.value = Building

                },
                { error ->
                    println("hata")
                    Log.e("refreshData", "Hata: ${error.message}")
                    // Hata durumunda yapılacak işlemler
                }
            )

    }
    fun getBuilding(lat: String, lng: String, team_id: String, group_id:String){
        buildingService.getBuilding(lat, lng, team_id, group_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Building ->
                    // userList Gson tarafından otomatik olarak dönüştürülmüş User listesidir
                    building.value = Building

                },
                { error ->
                    println("hata")
                    Log.e("refreshData", "Hata: ${error.message}")
                    // Hata durumunda yapılacak işlemler
                }
            )
    }
    fun distance(lat: String, lng: String, merkezlat: String, merkezlng: String, distance: String){
        buildingService.distance(lat, lng, merkezlat, merkezlng, distance)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Message ->
                    // userList Gson tarafından otomatik olarak dönüştürülmüş User listesidir
                    Response.value = Message

                },
                { error ->
                    println("hata")
                    val hataResponse = ResponseMessage("hata")
                    Response.value = hataResponse
                    Log.e("refreshData", "Hata: ${error.message}")
                    // Hata durumunda yapılacak işlemler
                }
            )
    }

    fun complete (building_id : String, team_id : String){
        buildingService.complete(building_id, team_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Message ->
                    // userList Gson tarafından otomatik olarak dönüştürülmüş User listesidir
                    Response.value = Message

                },
                { error ->
                    println("hata")
                    Log.e("refreshData", "Hata: ${error.message}")
                    // Hata durumunda yapılacak işlemler
                }
            )

    }
    fun getEarthquake(){
        buildingService.getEarthquake()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Algorithm ->
                    // userList Gson tarafından otomatik olarak dönüştürülmüş User listesidir
                    algorithm.value = Algorithm

                },
                { error ->
                    println("hata")
                    Log.e("refreshData", "Hata: ${error.message}")
                    // Hata durumunda yapılacak işlemler
                }
            )
    }

    fun updateUser(lat: String, lng: String, id : String){
        buildingService.updateUser(lat, lng, id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Message ->
                    // userList Gson tarafından otomatik olarak dönüştürülmüş User listesidir
                    Response.value = Message

                },
                { error ->
                    println("hata")
                    Log.e("refreshData", "Hata: ${error.message}")
                    // Hata durumunda yapılacak işlemler
                }
            )
    }
}