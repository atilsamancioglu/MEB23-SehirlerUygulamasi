package com.atilsamancioglu.sehirleruygulamasi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.atilsamancioglu.sehirleruygulamasi.R
import com.atilsamancioglu.sehirleruygulamasi.databinding.FragmentSehirBinding
import com.atilsamancioglu.sehirleruygulamasi.model.Sehir
import com.atilsamancioglu.sehirleruygulamasi.util.downloadGorsel
import com.atilsamancioglu.sehirleruygulamasi.util.placeholderProgressBar
import com.atilsamancioglu.sehirleruygulamasi.viewmodel.SehirViewModel
import kotlinx.android.synthetic.main.fragment_sehir.*

class SehirFragment : Fragment() {

    private lateinit var viewModel : SehirViewModel
    private var uuid = 0
    private lateinit var dataBinding : FragmentSehirBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_sehir,container,false)
        return dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            uuid = SehirFragmentArgs.fromBundle(it).sehirId
        }

        viewModel = ViewModelProvider(this).get(SehirViewModel::class.java)
        viewModel.verileriRoomdanAl(uuid)

        observeLiveData()

    }

     fun observeLiveData() {

        viewModel.sehirLiveData.observe(viewLifecycleOwner, Observer { sehir ->
            sehir?.let {
                dataBinding.secilenSehir = sehir
                /*
                sehirIsmi.text = it.sehirIsmi
                sehirBolgesi.text = it.sehirBolgesi
                sehirNufusu.text = it.sehirNufusu
                sehirMeshur.text = it.sehirMeshur
                context?.let {
                    sehirImage.downloadGorsel(sehir.sehirGorseli, placeholderProgressBar(it))
                }

                 */
            }
        })

    }


}