package com.deniz.term_project.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deniz.term_project.model.team
import com.deniz.term_project.service.services
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TeamViewModel : ViewModel(){
    val teamList = MutableLiveData<List<team>>()
    private val teamListService = services()

    fun refreshData() {
        teamListService.getTeamList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { tList ->
                    // userList Gson tarafından otomatik olarak dönüştürülmüş User listesidir
                    teamList.value = tList
                },
                { error ->
                    println("hata")
                    Log.e("refreshData", "Hata: ${error.message}")
                    // Hata durumunda yapılacak işlemler
                }
            )
    }
}