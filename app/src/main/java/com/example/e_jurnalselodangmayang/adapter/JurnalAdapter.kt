package com.example.e_jurnalselodangmayang.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_jurnalselodangmayang.R
import com.example.e_jurnalselodangmayang.model.ModelJurnal
import kotlinx.android.synthetic.main.fragment_home.view.*

class JurnalAdapter (var data : ArrayList<ModelJurnal>, var context: Activity?) : RecyclerView.Adapter<JurnalAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val judulJurnal =view.findViewById<TextView>(R.id.tv_juduljurnal)
        val tahunTerbit =view.findViewById<TextView>(R.id.tv_tahunterbit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_jurnal, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.judulJurnal.text = data[position].judulJurnal
        holder.tahunTerbit.text = data[position].tahunterbit
    }

}