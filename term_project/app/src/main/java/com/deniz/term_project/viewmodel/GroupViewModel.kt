package com.deniz.term_project.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deniz.term_project.model.building
import com.deniz.term_project.model.group
import com.deniz.term_project.service.services
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GroupViewModel : ViewModel() {

    val groupList = MutableLiveData<List<group>>()
    val groupedList = MutableLiveData<List<building>>()
    private val groupListService = services()


    fun refreshData() {
        groupListService.getGroupList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { gList ->
                    // userList Gson tarafından otomatik olarak dönüştürülmüş User listesidir
                    groupList.value = gList
                    print(gList)
                },
                { error ->
                    println("hata")
                    Log.e("refreshData", "Hata: ${error.message}")
                    // Hata durumunda yapılacak işlemler
                }
            )
    }
    fun groupedList(id : String){
        groupListService.getGrupedList(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { List ->
                    // userList Gson tarafından otomatik olarak dönüştürülmüş User listesidir
                    groupedList.value = List
                    print(List)
                },
                { error ->
                    println("hata")
                    Log.e("refreshData", "Hata: ${error.message}")
                    // Hata durumunda yapılacak işlemler
                }
            )

    }
}