package com.atilsamancioglu.sehirleruygulamasi.service

import com.atilsamancioglu.sehirleruygulamasi.model.Sehir
import io.reactivex.Single
import retrofit2.http.GET

interface SehirAPI {

    //GET, POST

    //https://raw.githubusercontent.com/atilsamancioglu/MEB22-KotlinVeriSeti/master/sehirler.json

    // BASE_URL -> https://raw.githubusercontent.com/
    // -> atilsamancioglu/MEB22-KotlinVeriSeti/master/sehirler.json

    @GET("atilsamancioglu/MEB22-KotlinVeriSeti/master/sehirler.json")
    fun sehirleriAl(): Single<List<Sehir>>


}