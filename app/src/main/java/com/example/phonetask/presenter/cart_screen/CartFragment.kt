package com.example.phonetask.presenter.cart_screen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.phonetask.R
import com.example.phonetask.presenter.main_screen.MainScreenAdapter
import com.example.phonetask.presenter.main_screen.MainScreenViewModel

class CartFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var deliveryTextView: TextView
    lateinit var totalTextView: TextView
    lateinit var adapter: CartScreenAdapter
    private lateinit var viewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        recyclerView = view.findViewById(R.id.r_v_cart)
        deliveryTextView = view.findViewById(R.id.t_v_free)
        totalTextView = view.findViewById(R.id.t_v_total_price)
        adapter = CartScreenAdapter()
        recyclerView.adapter = adapter

        viewModel.makeApiCartObservable()

        viewModel.getPhoneCartObserver().observe(viewLifecycleOwner) { list ->
            adapter.setList(list.basket)
            deliveryTextView.text = viewModel.getPhoneCartObserver().value?.delivery
            totalTextView.text = viewModel.getPhoneCartObserver().value?.total.toString()
        }

    }
}