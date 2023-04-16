package com.example.e_jurnalselodangmayang.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.e_jurnalselodangmayang.R

class SliderAdapter (private val imageList : ArrayList<Int>, private val viewPager2: ViewPager2)
    : RecyclerView.Adapter<SliderAdapter.imageViewHolder>() {

    class imageViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val imageview : ImageView = itemView.findViewById(R.id.iv_imageslider)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): imageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_container, parent, false)
        return imageViewHolder(view)
    }

    override fun onBindViewHolder(holder: imageViewHolder, position: Int) {
        holder.imageview.setImageResource(imageList[position])
        if (position == imageList.size-1) {
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    private val runnable = Runnable {
        imageList.addAll(imageList)
        notifyDataSetChanged()
    }
}