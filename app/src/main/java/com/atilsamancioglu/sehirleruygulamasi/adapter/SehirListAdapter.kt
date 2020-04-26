package com.atilsamancioglu.sehirleruygulamasi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.atilsamancioglu.sehirleruygulamasi.R
import com.atilsamancioglu.sehirleruygulamasi.databinding.ItemSehirBinding
import com.atilsamancioglu.sehirleruygulamasi.model.Sehir
import com.atilsamancioglu.sehirleruygulamasi.util.downloadGorsel
import com.atilsamancioglu.sehirleruygulamasi.util.placeholderProgressBar
import com.atilsamancioglu.sehirleruygulamasi.view.ListeFragmentDirections
import kotlinx.android.synthetic.main.item_sehir.view.*

class SehirListAdapter(val sehirListesi : ArrayList<Sehir>) :RecyclerView.Adapter<SehirListAdapter.SehirViewHolder>(),SehirClickListener {

    class SehirViewHolder(var view: ItemSehirBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SehirViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.item_sehir,parent,false)
        val view = DataBindingUtil.inflate<ItemSehirBinding>(inflater,R.layout.item_sehir,parent,false)
        return SehirViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sehirListesi.size
    }

    override fun onBindViewHolder(holder: SehirViewHolder, position: Int) {

        holder.view.sehir = sehirListesi[position]
        holder.view.listener = this
        /*
        holder.view.itemSehirIsmi.text = sehirListesi[position].sehirIsmi
        holder.view.itemSehirBolgesi.text = sehirListesi[position].sehirBolgesi

        holder.view.setOnClickListener {
            val action = ListeFragmentDirections.actionListeFragmentToSehirFragment(sehirListesi[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.itemImageView.downloadGorsel(sehirListesi[position].sehirGorseli,placeholderProgressBar(holder.view.context))

         */
    }

    fun verileriGuncelle(yeniSehirListesi: List<Sehir>) {
        sehirListesi.clear()
        sehirListesi.addAll(yeniSehirListesi)
        notifyDataSetChanged()
    }

    override fun onSehirClicked(v: View) {
        val action = ListeFragmentDirections.actionListeFragmentToSehirFragment(v.itemSehirUuid.text.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }

}