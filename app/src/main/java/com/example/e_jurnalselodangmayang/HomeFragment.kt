package com.example.e_jurnalselodangmayang

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.e_jurnalselodangmayang.adapter.JurnalAdapter
import com.example.e_jurnalselodangmayang.adapter.SliderAdapter
import com.example.e_jurnalselodangmayang.databinding.FragmentHomeBinding
import com.example.e_jurnalselodangmayang.model.ModelJurnal
import kotlin.math.abs


class HomeFragment : Fragment() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList:ArrayList<Int>
    private lateinit var adapter: SliderAdapter
    private var binding : FragmentHomeBinding? = null
    lateinit var rvListJurnal : RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        //rv list jurnal
        val lmListJurnal = LinearLayoutManager(activity)
        lmListJurnal.orientation = LinearLayoutManager.VERTICAL
        rvListJurnal = view.findViewById(R.id.rv_listjurnal)

        val adapterLisJurnal = JurnalAdapter(ArrayJurnal, activity)
        rvListJurnal.setHasFixedSize(true)
        rvListJurnal.layoutManager = lmListJurnal
        rvListJurnal.adapter = adapterLisJurnal

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        setUpTransformer()

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable,2000)
            }
        })
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable,2000)
    }

    private val runnable =Runnable {
        viewPager2.currentItem =viewPager2.currentItem+1
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(0))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleX = 0.85f + r * 0.14f
        }

        viewPager2.setPageTransformer(transformer)
    }

    private fun init(view: View) {
        viewPager2 = view.findViewById(R.id.vp_slider)
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.image_slider1)
        imageList.add(R.drawable.image_slider2)
        imageList.add(R.drawable.image_slider3)
        imageList.add(R.drawable.image_slider4)

        adapter = SliderAdapter(imageList, viewPager2)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 1
        viewPager2.clipToPadding = true
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    val ArrayJurnal :ArrayList<ModelJurnal>get() {
        val arrayjurnal = ArrayList<ModelJurnal>()

        val jurnal1 = ModelJurnal()
        jurnal1.judulJurnal = "Pemanfaatan Buah sawit tandan seger"
        jurnal1.tahunterbit = "2019"
        arrayjurnal.add(jurnal1)

        val jurnal2 = ModelJurnal()
        jurnal2.judulJurnal = "Perkembangan psikologi pada anak"
        jurnal2.tahunterbit = "2018"
        arrayjurnal.add(jurnal2)

        val jurnal3 = ModelJurnal()
        jurnal3.judulJurnal = "Penggunaan unsur Hcl secara tepat"
        jurnal3.tahunterbit = "2020"
        arrayjurnal.add(jurnal3)

        val jurnal4 = ModelJurnal()
        jurnal4.judulJurnal = "Masa efektif pekembangan remaja"
        jurnal4.tahunterbit = "2017"
        arrayjurnal.add(jurnal4)

        val jurnal5 = ModelJurnal()
        jurnal5.judulJurnal = "Pengembangan sistem informasi pada SMA 1"
        jurnal5.tahunterbit = "2019"
        arrayjurnal.add(jurnal5)

        val jurnal6 = ModelJurnal()
        jurnal6.judulJurnal = "Sifat unsur tanah gambut provinsi Riau"
        jurnal6.tahunterbit = "2021"
        arrayjurnal.add(jurnal6)

        val jurnal7 = ModelJurnal()
        jurnal7.judulJurnal = "Penangkapan satwa terlindungi UU"
        jurnal7.tahunterbit = "2018"
        arrayjurnal.add(jurnal7)

        return arrayjurnal
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}