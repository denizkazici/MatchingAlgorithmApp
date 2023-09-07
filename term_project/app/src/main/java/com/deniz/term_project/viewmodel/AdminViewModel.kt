package com.deniz.term_project.viewmodel

import android.os.Message
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deniz.term_project.model.Algorithm

import com.deniz.term_project.model.ResponseMessage
import com.deniz.term_project.model.building
import com.deniz.term_project.service.services
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.coroutines.launch



class AdminViewModel : ViewModel() {
    val Response = MutableLiveData<ResponseMessage>()
    val building = MutableLiveData<building>()
    val algorithm = MutableLiveData<Algorithm>()
    private val adminService = services()

    fun test(id :String){
        adminService.test(id)
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
    fun getEarthquake(){
        adminService.getEarthquake()
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
    fun complete (building_id : String, team_id : String){
        adminService.complete(building_id, team_id)
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
    fun createBuildingList( merkezlat: String, merkezlng: String, distance: String, count: String) {
        adminService.createBuildingList(merkezlat, merkezlng, distance, count)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Message ->
                    // userList Gson tarafından otomatik olarak dönüştürülmüş User listesidir
                    Response.value = Message

                },
                { error ->
                    Response.value = ResponseMessage("Başarısız")
                    println("hata")
                    Log.e("refreshData", "Hata: ${error.message}")
                    // Hata durumunda yapılacak işlemler
                }
            )



    }
    fun createUserList(){
        adminService.createUserList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Message ->
                    // userList Gson tarafından otomatik olarak dönüştürülmüş User listesidir
                    Response.value = Message

                },
                { error ->
                    Response.value = ResponseMessage("Başarısız")
                    println("hata")
                    Log.e("refreshData", "Hata: ${error.message}")
                    // Hata durumunda yapılacak işlemler
                }
            )

    }
    fun createTeamList( merkezlat: String, merkezlng: String, distance: String, count: String){
        adminService.createTeamList(merkezlat,merkezlng, distance, count)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Message ->
                    // userList Gson tarafından otomatik olarak dönüştürülmüş User listesidir
                    Response.value = Message

                },
                { error ->
                    Response.value = ResponseMessage("Başarısız")
                    println("hata")
                    Log.e("refreshData", "Hata: ${error.message}")
                    // Hata durumunda yapılacak işlemler
                }
            )
    }
    fun createRoadList(id : String){
        adminService.createRoadList(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Message ->
                    // userList Gson tarafından otomatik olarak dönüştürülmüş User listesidir
                    Response.value = Message

                },
                { error ->
                    Response.value = ResponseMessage("Başarısız")
                    println("hata")
                    Log.e("refreshData", "Hata: ${error.message}")
                    // Hata durumunda yapılacak işlemler
                }
            )
    }
    fun createGroupList (){
        adminService.createGroupList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Message ->
                    // userList Gson tarafından otomatik olarak dönüştürülmüş User listesidir
                    Response.value = Message

                },
                { error ->
                    Response.value = ResponseMessage("Başarısız")
                    println("hata")
                    Log.e("refreshData", "Hata: ${error.message}")
                    // Hata durumunda yapılacak işlemler
                }
            )
    }
    fun change (id : String, name : String, password: String) {
        adminService.change(id, name, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Message ->
                    // userList Gson tarafından otomatik olarak dönüştürülmüş User listesidir
                    Response.value = Message

                },
                { error ->
                    Response.value = ResponseMessage("Başarısız")
                    println("hata")
                    Log.e("refreshData", "Hata: ${error.message}")
                    // Hata durumunda yapılacak işlemler
                }
            )
    }

    fun delete () {
        adminService.delete()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Message ->
                    // userList Gson tarafından otomatik olarak dönüştürülmüş User listesidir
                    Response.value = Message

                },
                { error ->
                    Response.value = ResponseMessage("Başarısız")
                    println("hata")
                    Log.e("refreshData", "Hata: ${error.message}")
                    // Hata durumunda yapılacak işlemler
                }
            )
    }
}