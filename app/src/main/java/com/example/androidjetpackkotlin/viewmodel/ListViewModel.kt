package com.example.androidjetpackkotlin.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.androidjetpackkotlin.model.DealsApiService
import com.example.androidjetpackkotlin.model.Model
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel(application: Application) : BaseViewModel(application) {

    private val dealsService = DealsApiService()
    private val disposable = CompositeDisposable()

    val deals = MutableLiveData<List<Model.Products>>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
            fetchFromRemote()
    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            dealsService.getProductListing()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Model>() {
                    override fun onSuccess(list: Model?) {
                        deals.value = list?.getProducts()?.toMutableList()
                        loadError.value = false
                        loading.value = false
                        Toast.makeText(getApplication(), "Here are the latest deals for you.", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable?) {
                        loadError.value = true
                        loading.value = false
                        e?.printStackTrace()
                    }

                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}