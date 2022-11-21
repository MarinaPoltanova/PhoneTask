package com.example.phonetask.presenter.main_screen

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.phonetask.Listener
import com.example.phonetask.R
import com.example.phonetask.data.model.main_screen.BestSeller
import com.example.phonetask.data.model.main_screen.HomeStore

class MainScreenAdapter : RecyclerView.Adapter<MainScreenAdapter.MainScreenHolder>()  {

    var bestSellerList = emptyList<BestSeller>()
    lateinit var mlistener: Listener

    fun setOnItemClickListener(listener: Listener) {
        mlistener = listener
    }

    class MainScreenHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageBestSeller = view.findViewById<ImageView>(R.id.i_v_local_image_best_celler)
        var newPriceBestSeller = view.findViewById<TextView>(R.id.t_v_current_price_best_celler)
        var oldPriceBestSeller = view.findViewById<TextView>(R.id.t_v_old_price_best_celler)
        var titleBestSeller = view.findViewById<TextView>(R.id.t_v_local_title_best_celler)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_best_seller,
            parent, false
        )
        return MainScreenHolder(view)
    }

    override fun onBindViewHolder(holder: MainScreenHolder, position: Int) {
         //image
        Glide.with(holder.imageBestSeller)
            .load(bestSellerList[position].picture)
            .placeholder(androidx.appcompat.R.drawable.abc_btn_check_material_anim)
            .into(holder.imageBestSeller)

        holder.titleBestSeller.text = bestSellerList[position].title
        holder.newPriceBestSeller.text = bestSellerList[position].discount_price.toString()
        holder.oldPriceBestSeller.text = bestSellerList[position].price_without_discount.toString()
        holder.oldPriceBestSeller.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG
        holder.itemView.setOnClickListener {
            bestSellerList[position].id?.let { it1 -> mlistener.onClickListener(it1) }
        }
    }

    override fun getItemCount(): Int {
        return bestSellerList.size
    }

    //function for set list
    @SuppressLint("NotifyDataSetChanged")
    fun setList(listResult: List<BestSeller>) {
        bestSellerList = listResult
        //if something change, Android notify us
        notifyDataSetChanged()
    }
}