package com.example.phonetask.presenter.cart_screen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.phonetask.Listener
import com.example.phonetask.R
import com.example.phonetask.data.model.cart_screen.Basket
import com.example.phonetask.data.model.cart_screen.CartResult
import com.example.phonetask.data.model.main_screen.BestSeller
import com.example.phonetask.presenter.main_screen.MainScreenAdapter

class CartScreenAdapter : RecyclerView.Adapter<CartScreenAdapter.CartScreenHolder>() {

    var phoneCartList = emptyList<Basket>()
    lateinit var mlistener: Listener

    fun setOnItemClickListener(listener: Listener) {
        mlistener = listener
    }

    class CartScreenHolder(view: View) : RecyclerView.ViewHolder(view) {

        var imageCartItem = view.findViewById<ImageView>(R.id.i_v_cart_image)
        var titleCartItem = view.findViewById<TextView>(R.id.t_v_title_item_cart)
        var priceCartItem = view.findViewById<TextView>(R.id.t_v_price_item_cart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartScreenHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_cart,
            parent, false
        )
        return CartScreenHolder(view)
    }

    override fun onBindViewHolder(holder: CartScreenHolder, position: Int) {

        //image
        Glide.with(holder.imageCartItem)
            .load(phoneCartList[position].images)
            .placeholder(androidx.appcompat.R.drawable.abc_btn_check_material_anim)
            .into(holder.imageCartItem)

        holder.titleCartItem.text = phoneCartList[position].title
        holder.priceCartItem.text = phoneCartList[position].price.toString()
    }

    override fun getItemCount(): Int {
       return phoneCartList.size
    }

    //function for set list
    @SuppressLint("NotifyDataSetChanged")
    fun setList(listResult: List<Basket>) {
        phoneCartList = listResult
        //if something change, Android notify us
        notifyDataSetChanged()
    }
}