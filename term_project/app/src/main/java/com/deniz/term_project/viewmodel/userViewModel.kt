package com.deniz.term_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deniz.term_project.model.user
import com.deniz.term_project.service.services
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class userViewModel: ViewModel()  {
    val userList = MutableLiveData<List<user>>()

    private val userListService = services()
   // private val disposable = CompositeDisposable()
   // val compositeDisposable = CompositeDisposable()
    fun refreshData(){
        userListService.getUserList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { uList ->
                    // userList Gson tarafından otomatik olarak dönüştürülmüş User listesidir
                    userList.value = uList
                },
                { error ->
                    println("hata")
                    // Hata durumunda yapılacak işlemler
                }
            )
        /*userListService.getUserList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { uList ->
                    //userListAdapter.setData(uList)
                    userList.value = uList
                },
                { error ->
                    println("hata")
                }
            )
            .let { compositeDisposable.add(it) }*/
        /*disposable.add(
            userListService.getUserList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<user>>() {
                    override fun onSuccess(t: List<user>) {
                        this@userViewModel.userList.value = t
                    }
                    override fun onError(e: Throwable) {

                        val errorMessage = e.message

                        // val context = contextProvider.getContext()
                        //Toast.makeText(context, "Hatalı şifre", Toast.LENGTH_LONG).show()

                    }

                })
        )*/
    }

}