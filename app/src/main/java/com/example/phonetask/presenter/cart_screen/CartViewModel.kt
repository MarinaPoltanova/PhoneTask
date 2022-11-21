package com.example.phonetask.presenter.cart_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.phonetask.data.model.cart_screen.CartResult
import com.example.phonetask.data.model.main_screen.SearchResult
import com.example.phonetask.data.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CartViewModel : ViewModel() {
    var repository = Repository()

    val liveDataPhoneCart: MutableLiveData<CartResult> = MutableLiveData()

    fun getPhoneCartObserver(): MutableLiveData<CartResult> {
        return liveDataPhoneCart
    }

    fun makeApiCartObservable() {

        val apiInterfaceCart = repository.getPhoneCart()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getPhoneCartObserverRx())
    }

    private fun getPhoneCartObserverRx(): SingleObserver<CartResult> {
        return object : SingleObserver<CartResult> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onSuccess(t: CartResult) {
                liveDataPhoneCart.postValue(t)
            }

            override fun onError(e: Throwable) {
                liveDataPhoneCart.postValue(null)
            }
        }
    }
}