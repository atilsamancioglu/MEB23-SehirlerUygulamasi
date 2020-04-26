package com.atilsamancioglu.sehirleruygulamasi.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.atilsamancioglu.sehirleruygulamasi.model.Sehir

@Database(entities = arrayOf(Sehir::class),version = 1)
abstract class SehirDatabase : RoomDatabase() {

    abstract fun sehirDao() : SehirDAO

    //Singleton

    companion object {

        @Volatile private var instance : SehirDatabase? = null

        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: veritabaniOlustur(context).also {
                instance = it
            }
        }

        private fun veritabaniOlustur(context: Context) = Room.databaseBuilder(context,
        SehirDatabase::class.java,"sehirDatabase").build()

    }

}