package com.atilsamancioglu.sehirleruygulamasi.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class OzelSharedPreferences {

    companion object {

        private val PREFERENCES_ZAMAN = "zaman"
        private var sharedPreferences : SharedPreferences? = null

        @Volatile private var instance : OzelSharedPreferences? = null

        private var lock = Any()

        operator fun invoke (context : Context) : OzelSharedPreferences = instance ?: synchronized(lock) {
            instance ?: ozelSharedPreferencesOlustur(context).also {
                instance = it
            }
        }

        private fun ozelSharedPreferencesOlustur(context: Context) : OzelSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return OzelSharedPreferences()
        }


    }

    fun zamaniKaydet(zaman : Long) {
        sharedPreferences?.edit(commit = true) {
            putLong(PREFERENCES_ZAMAN,zaman)
        }
    }

    fun zamaniAl() = sharedPreferences?.getLong(PREFERENCES_ZAMAN,0)


}