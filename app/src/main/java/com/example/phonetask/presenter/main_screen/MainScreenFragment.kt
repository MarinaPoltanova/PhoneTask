package com.example.phonetask.presenter.main_screen

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.phonetask.Listener
import com.example.phonetask.R
import com.example.phonetask.data.model.favorite_screen.FavoriteFragment
import com.example.phonetask.presenter.cart_screen.CartFragment
import com.example.phonetask.presenter.product_details_screen.ProductDetailsFragment
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout

class MainScreenFragment : Fragment() {
    lateinit var hotSalesTitle: TextView
    lateinit var hotSalesDescription: TextView
    lateinit var hotSalesImageView: ImageView
    lateinit var bottomMenu: BottomNavigationView

    lateinit var phoneItemCategory: ImageButton

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MainScreenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_screen, container, false)

    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[MainScreenViewModel::class.java]
        recyclerView = view.findViewById(R.id.r_v_best_seller)

        hotSalesTitle = view.findViewById(R.id.t_v_hot_sales_phone_name)
        hotSalesDescription = view.findViewById(R.id.t_v_hot_sales_description)
        hotSalesImageView = view.findViewById(R.id.i_v_hot_sales)

        phoneItemCategory = view.findViewById(R.id.i_v_phones)

        bottomMenu = view.findViewById(R.id.n_v_bottom_menu)

        bottomMenu.setOnNavigationItemSelectedListener {
            var selectedFragment: Fragment = MainScreenFragment()
            when (it.itemId) {
                R.id.shopping -> selectedFragment = CartFragment()
                R.id.message -> selectedFragment = FavoriteFragment()
            }
            val transaction = fragmentManager?.beginTransaction()
            ?.replace(R.id.place_holder, selectedFragment)
            ?.addToBackStack("Main")
            ?.commit()
            return@setOnNavigationItemSelectedListener true
        }

        adapter = MainScreenAdapter()
        recyclerView.adapter = adapter

        viewModel.makeApiPhoneObservable()

        viewModel.getPhoneListObserver().observe(viewLifecycleOwner) { list ->
            adapter.setList(list.best_seller)
            hotSalesTitle.text = viewModel.getPhoneListObserver().value?.home_store?.get(0)?.title
            hotSalesDescription.text =
                viewModel.getPhoneListObserver().value?.home_store?.get(0)?.subtitle

            Glide.with(hotSalesImageView)
                .load(viewModel.getPhoneListObserver().value?.home_store?.get(0)?.picture)
                .placeholder(androidx.appcompat.R.drawable.abc_btn_check_material_anim)
                .into(hotSalesImageView)
        }

        adapter.setOnItemClickListener(object : Listener {
            override fun onClickListener(position: Int) {

                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.place_holder, ProductDetailsFragment())
                    ?.addToBackStack("Main") //положила в стек
                    ?.commit()
            }
        })
    }
}