package com.atilsamancioglu.sehirleruygulamasi.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.atilsamancioglu.sehirleruygulamasi.model.Sehir

@Dao
interface SehirDAO {
    //Data Access Object
    @Insert
    suspend fun insertAll(vararg sehirler : Sehir) : List<Long>

    //insert -> INSERT INTO
    //suspend -> coroutine, duraklatılabilen & devam ettirilebilen
    //vararg -> birden fazla, şehir objesi alabilmemize olanak tanıyo
    // List<Long> -> primary key

    @Query("SELECT * FROM sehir")
    suspend fun getAllSehirler() : List<Sehir>

    @Query("SELECT * FROM sehir WHERE uuid = :sehirId")
    suspend fun getSehir(sehirId : Int) : Sehir

    @Query("DELETE FROM sehir")
    suspend fun deleteAllSehir()

}