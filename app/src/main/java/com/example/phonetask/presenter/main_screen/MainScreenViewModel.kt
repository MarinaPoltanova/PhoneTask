package com.example.phonetask.presenter.main_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.phonetask.data.model.main_screen.SearchResult
import com.example.phonetask.data.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainScreenViewModel : ViewModel() {
    var repository = Repository()

    //    val liveDataHotSales: MutableLiveDatatableLiveData<String> = MutableLiveData()
    val liveDataPhoneList: MutableLiveData<SearchResult> = MutableLiveData()

    fun getPhoneListObserver(): MutableLiveData<SearchResult> {
        return liveDataPhoneList
    }

    fun makeApiPhoneObservable() {

        val apiInterfacePhone = repository.getPhoneSearch()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getPhoneListObserverRx())
    }

    private fun getPhoneListObserverRx(): SingleObserver<SearchResult> {
        return object : SingleObserver<SearchResult> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onSuccess(t: SearchResult) {
                liveDataPhoneList.postValue(t)
            }

            override fun onError(e: Throwable) {
                liveDataPhoneList.postValue(null)
            }
        }
    }
}
