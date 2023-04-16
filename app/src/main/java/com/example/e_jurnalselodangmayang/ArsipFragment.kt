package com.example.e_jurnalselodangmayang

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_jurnalselodangmayang.adapter.JurnalAdapter
import com.example.e_jurnalselodangmayang.databinding.FragmentHomeBinding
import com.example.e_jurnalselodangmayang.model.ModelJurnal

class ArsipFragment : Fragment() {

    private var binding : FragmentHomeBinding? = null
    lateinit var rvArsipJurnal : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_arsip, container, false)

        //rv list jurnal
        val lmListJurnal = LinearLayoutManager(activity)
        lmListJurnal.orientation = LinearLayoutManager.VERTICAL
        rvArsipJurnal = view.findViewById(R.id.rv_arsip)

        val adapterLisJurnal = JurnalAdapter(ArrayArsip, activity)
        rvArsipJurnal.setHasFixedSize(true)
        rvArsipJurnal.layoutManager = lmListJurnal
        rvArsipJurnal.adapter = adapterLisJurnal

        return view
    }

    val ArrayArsip : ArrayList<ModelJurnal>get() {
        val arrayarsip = ArrayList<ModelJurnal>()

        val jurnal1 = ModelJurnal()
        jurnal1.judulJurnal = "Pemanfaatan Buah sawit tandan seger"
        jurnal1.tahunterbit = "2019"
        arrayarsip.add(jurnal1)

        val jurnal2 = ModelJurnal()
        jurnal2.judulJurnal = "Perkembangan psikologi pada anak"
        jurnal2.tahunterbit = "2018"
        arrayarsip.add(jurnal2)

        val jurnal3 = ModelJurnal()
        jurnal3.judulJurnal = "Penggunaan unsur Hcl secara tepat"
        jurnal3.tahunterbit = "2020"
        arrayarsip.add(jurnal3)

        val jurnal4 = ModelJurnal()
        jurnal4.judulJurnal = "Masa efektif pekembangan remaja"
        jurnal4.tahunterbit = "2017"
        arrayarsip.add(jurnal4)

        val jurnal5 = ModelJurnal()
        jurnal5.judulJurnal = "Pengembangan sistem informasi pada SMA 1"
        jurnal5.tahunterbit = "2019"
        arrayarsip.add(jurnal5)

        val jurnal6 = ModelJurnal()
        jurnal6.judulJurnal = "Sifat unsur tanah gambut provinsi Riau"
        jurnal6.tahunterbit = "2021"
        arrayarsip.add(jurnal6)

        val jurnal7 = ModelJurnal()
        jurnal7.judulJurnal = "Penangkapan satwa terlindungi UU"
        jurnal7.tahunterbit = "2018"
        arrayarsip.add(jurnal7)

        return arrayarsip
    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ArsipFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}