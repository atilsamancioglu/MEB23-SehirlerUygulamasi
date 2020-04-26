package com.atilsamancioglu.sehirleruygulamasi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.atilsamancioglu.sehirleruygulamasi.R
import com.atilsamancioglu.sehirleruygulamasi.adapter.SehirListAdapter
import com.atilsamancioglu.sehirleruygulamasi.viewmodel.ListeViewModel
import kotlinx.android.synthetic.main.fragment_liste.*


class ListeFragment : Fragment() {

    private lateinit var viewModel : ListeViewModel
    private val listeAdapter = SehirListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liste, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel = ViewModelProviders.of(this).get(ListeViewModel::class.java)
        viewModel = ViewModelProvider(this).get(ListeViewModel::class.java)
        viewModel.refreshData()

        sehirList.layoutManager = LinearLayoutManager(context)
        sehirList.adapter = listeAdapter

        refreshLayout.setOnRefreshListener {
            sehirList.visibility = View.GONE
            sehirHataMesaji.visibility = View.GONE
            sehirlerYukleniyor.visibility = View.VISIBLE
            viewModel.apiRefresh()
            refreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.sehirler.observe(viewLifecycleOwner, Observer { sehirler ->

            sehirler?.let {
                sehirList.visibility = View.VISIBLE
                listeAdapter.verileriGuncelle(sehirler)
            }

        })

        viewModel.sehirHata.observe(viewLifecycleOwner, Observer { hata ->
            hata?.let{
                if (hata) {
                    sehirHataMesaji.visibility = View.VISIBLE
                    sehirList.visibility = View.GONE
                } else {
                    sehirHataMesaji.visibility = View.GONE
                }
            }
        })

        viewModel.sehirYukleniyor.observe(viewLifecycleOwner, Observer { yukleniyor ->
            yukleniyor?.let{

                if (yukleniyor) {
                    sehirlerYukleniyor.visibility = View.VISIBLE
                    sehirList.visibility = View.GONE
                    sehirHataMesaji.visibility =View.GONE
                } else {
                    sehirlerYukleniyor.visibility = View.GONE
                }

            }
        })

    }

}