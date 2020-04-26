package com.atilsamancioglu.sehirleruygulamasi.service

import com.atilsamancioglu.sehirleruygulamasi.model.Sehir
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SehirAPIService {

    //https://raw.githubusercontent.com/atilsamancioglu/MEB22-KotlinVeriSeti/master/sehirler.json

    // BASE_URL -> https://raw.githubusercontent.com/
    // -> atilsamancioglu/MEB22-KotlinVeriSeti/master/sehirler.json

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(SehirAPI::class.java)

    fun veriAl() : Single<List<Sehir>> {
        return api.sehirleriAl()
    }

}