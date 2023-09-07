package com.deniz.term_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deniz.term_project.model.user
import com.deniz.term_project.service.services
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class UserDetailViewModel : ViewModel() {

    val user = MutableLiveData<user>()

    private val userService = services()
    private val disposable = CompositeDisposable()

    fun getUser(id: String) {
        disposable.add(
            userService.getUser(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<user>() {
                    override fun onSuccess(user: user) {
                        //UserDataHolder.setUser(user)
                        this@UserDetailViewModel.user.value = user
                    }

                    override fun onError(e: Throwable) {

                        val errorMessage = e.message

                        // val context = contextProvider.getContext()
                        //Toast.makeText(context, "Hatalı şifre", Toast.LENGTH_LONG).show()

                    }
                })
        )
    }
}