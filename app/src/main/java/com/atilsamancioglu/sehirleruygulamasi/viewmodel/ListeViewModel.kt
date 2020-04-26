package com.atilsamancioglu.sehirleruygulamasi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.atilsamancioglu.sehirleruygulamasi.model.Sehir
import com.atilsamancioglu.sehirleruygulamasi.service.SehirAPIService
import com.atilsamancioglu.sehirleruygulamasi.service.SehirDatabase
import com.atilsamancioglu.sehirleruygulamasi.util.OzelSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListeViewModel(application: Application) : BaseViewModel(application) {

    private val sehirApiService = SehirAPIService()
    private val disposable = CompositeDisposable()
    private var ozelSharedPreferences = OzelSharedPreferences(getApplication())
    private var yenilemeZamani = 10 * 60 * 1000 * 1000 * 1000L

    val sehirler = MutableLiveData<List<Sehir>>()
    val sehirHata = MutableLiveData<Boolean>()
    val sehirYukleniyor = MutableLiveData<Boolean>()


    fun apiRefresh() {
        apiVeriAl()
    }

    fun refreshData() {
        val kaydedilenZaman = ozelSharedPreferences.zamaniAl()
        if (kaydedilenZaman != null && kaydedilenZaman != 0L && System.nanoTime() - kaydedilenZaman < yenilemeZamani ) {
            //SQLite Al
            sqliteVeriAl()
        } else {
            apiVeriAl()
        }
    }

    private fun sqliteVeriAl() {
        sehirYukleniyor.value = true
        launch {
            val sehirler = SehirDatabase(getApplication()).sehirDao().getAllSehirler()
            sehirleriGoster(sehirler)
            Toast.makeText(getApplication(),"SQLite'dan Yüklendi",Toast.LENGTH_LONG).show()
        }
    }

    private fun apiVeriAl() {
        sehirYukleniyor.value = true

        disposable.add(
            sehirApiService.veriAl()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Sehir>>(){
                    override fun onSuccess(t: List<Sehir>) {
                        sqLiteKayitEt(t)
                        Toast.makeText(getApplication(),"API'dan Yüklendi",Toast.LENGTH_LONG).show()

                    }

                    override fun onError(e: Throwable) {
                        sehirYukleniyor.value = false
                        sehirHata.value = true
                        e.printStackTrace()
                    }
                }
        ))


    }

    private fun sehirleriGoster(sehirListesi : List<Sehir>) {
        sehirYukleniyor.value = false
        sehirHata.value = false
        sehirler.value = sehirListesi
    }

    private fun sqLiteKayitEt(sehirListesi: List<Sehir>) {
        launch {
            val dao = SehirDatabase(getApplication()).sehirDao()
            dao.deleteAllSehir()
            val listLong = dao.insertAll(*sehirListesi.toTypedArray())
            var i = 0
            while (i < sehirListesi.size) {
                sehirListesi[i].uuid = listLong[i].toInt()
                i = i + 1
            }

            sehirleriGoster(sehirListesi)
        }

        ozelSharedPreferences.zamaniKaydet(System.nanoTime())

    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }

}