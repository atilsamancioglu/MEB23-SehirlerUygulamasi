package com.atilsamancioglu.sehirleruygulamasi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Sehir(
    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    val sehirIsmi : String?,
    @ColumnInfo(name = "bolge")
    @SerializedName("bolge")
    val sehirBolgesi: String?,
    @ColumnInfo(name = "nufus")
    @SerializedName("nufus")
    val sehirNufusu: String?,
    @ColumnInfo(name = "meshur")
    @SerializedName("meshur")
    val sehirMeshur: String?,
    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val sehirGorseli: String?) {

    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0

}