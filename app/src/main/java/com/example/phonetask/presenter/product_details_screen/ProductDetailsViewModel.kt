package com.example.phonetask.presenter.product_details_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.phonetask.data.model.main_screen.SearchResult
import com.example.phonetask.data.model.product_detail_screen.DetailResult
import com.example.phonetask.data.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ProductDetailsViewModel: ViewModel() {

    var repository = Repository()

    val liveDataCurrent : MutableLiveData<DetailResult> = MutableLiveData()


    fun getDetailPhoneObserver(): MutableLiveData<DetailResult> {
        return liveDataCurrent
    }

    fun makeApiDetailObservable() {

        val apiInterfaceDetail = repository.getPhoneDetail()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getPhoneDetailObserverRx())
    }

    private fun getPhoneDetailObserverRx(): SingleObserver<DetailResult> {
        return object : SingleObserver<DetailResult> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onSuccess(t: DetailResult) {
                liveDataCurrent.postValue(t)
            }

            override fun onError(e: Throwable) {
                liveDataCurrent.postValue(null)
            }
        }
    }
}