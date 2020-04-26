package com.atilsamancioglu.sehirleruygulamasi.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.atilsamancioglu.sehirleruygulamasi.model.Sehir
import com.atilsamancioglu.sehirleruygulamasi.service.SehirDatabase
import kotlinx.coroutines.launch

class SehirViewModel(application: Application) : BaseViewModel(application) {

    val sehirLiveData = MutableLiveData<Sehir>()

    fun verileriRoomdanAl(uuid : Int) {

        launch {

            val dao = SehirDatabase(getApplication()).sehirDao()
            val sehir = dao.getSehir(uuid)
            sehirLiveData.value = sehir

        }

    }
}