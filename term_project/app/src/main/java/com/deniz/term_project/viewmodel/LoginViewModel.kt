package com.deniz.term_project.viewmodel

import androidx.lifecycle.*
import com.deniz.term_project.model.user
import com.deniz.term_project.service.services
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class LoginViewModel() : ViewModel() {
    val user = MutableLiveData<user>()

    private val LoginService = services()
    private val disposable = CompositeDisposable()

    fun getVeri(name: String, password: String) {
        disposable.add(
            LoginService.getUser(name, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<user>() {
                    override fun onSuccess(user: user) {
                        //UserDataHolder.setUser(user)
                        this@LoginViewModel.user.value = user
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


