package com.deniz.term_project.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deniz.term_project.model.building
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.deniz.term_project.service.services


class BuildingViewModel : ViewModel()  {
    val buildingList = MutableLiveData<List<building>>()

    private val buildingListService = services()


    fun refreshData() {
        buildingListService.getBuildingList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { bList ->
                    // userList Gson tarafından otomatik olarak dönüştürülmüş User listesidir
                    buildingList.value = bList
                    print(bList)
                },
                { error ->
                    println("hata")
                    Log.e("refreshData", "Hata: ${error.message}")
                    // Hata durumunda yapılacak işlemler
                }
            )
    }
}