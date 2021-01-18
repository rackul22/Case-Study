package com.example.androidjetpackkotlin.viewmodel

import android.app.Application
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidjetpackkotlin.R
import com.example.androidjetpackkotlin.model.DealsApiService
import com.example.androidjetpackkotlin.model.Model
import com.example.androidjetpackkotlin.model.Product
import com.example.androidjetpackkotlin.model.ProductDetail
import com.example.androidjetpackkotlin.utils.getProgressDrawable
import com.example.androidjetpackkotlin.utils.loadImage
import com.example.androidjetpackkotlin.utils.showStrikeThrough
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DetailsViewModel(application: Application):BaseViewModel(application) {

    val productLiveData = MutableLiveData<ProductDetail>()

    val loading = MutableLiveData<Boolean>()
    private val dealsService = DealsApiService()
    private val disposable = CompositeDisposable()

     fun fetchProductDetails(id: Int) {
        //loading.value = true
        disposable.add(
            dealsService.getProductDetails(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ProductDetail>() {
                    override fun onSuccess(value: ProductDetail?) {
                        loading.value = false
                        Log.d("product details API",value.toString())
                        productLiveData.value = value
                        Toast.makeText(getApplication(), "Hurry, Limited time Offer!", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable?) {
                        loading.value = false


                    }


                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

