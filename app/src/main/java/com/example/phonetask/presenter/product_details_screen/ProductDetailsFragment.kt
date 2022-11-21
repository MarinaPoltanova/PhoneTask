package com.example.phonetask.presenter.product_details_screen

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.phonetask.R
import com.example.phonetask.presenter.cart_screen.CartFragment
import com.example.phonetask.presenter.main_screen.MainScreenAdapter
import com.example.phonetask.presenter.main_screen.MainScreenViewModel

class ProductDetailsFragment : Fragment() {
    lateinit var imageViewDetail: ImageView
    lateinit var textViewTitleDetail: TextView
    lateinit var textViewProcessorDetail: TextView
    lateinit var textViewCameraDetail: TextView
    lateinit var textViewSsdDetail: TextView
    lateinit var textViewSdrDetail: TextView
    lateinit var favoriteImageButton: ImageView
    lateinit var addToCartImageButton: Button
    lateinit var imageButtonColorFirst: ImageButton
    lateinit var imageButtonColorSecond: ImageButton
    lateinit var capacityFirstButton: Button
    lateinit var capacitySecondButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[ProductDetailsViewModel::class.java]
        imageViewDetail = view.findViewById(R.id.i_v_image_detail)
        textViewTitleDetail = view.findViewById(R.id.t_v_title_details)
        textViewProcessorDetail = view.findViewById(R.id.t_v_processor)
        textViewCameraDetail = view.findViewById(R.id.t_v_photo)
        textViewSsdDetail = view.findViewById(R.id.t_v_flash)
        textViewSdrDetail = view.findViewById(R.id.t_v_sim)
        favoriteImageButton = view.findViewById(R.id.appCompatImageViewLike)
        addToCartImageButton = view.findViewById(R.id.b_add_to_cart)
        imageButtonColorFirst = view.findViewById(R.id.i_v_first)
        imageButtonColorSecond = view.findViewById(R.id.i_v_second)
        capacityFirstButton = view.findViewById(R.id.i_v_capacity_first)
        capacitySecondButton = view.findViewById(R.id.i_v_capacity_second)

        viewModel.makeApiDetailObservable()

        viewModel.getDetailPhoneObserver().observe(viewLifecycleOwner) { list ->

            textViewTitleDetail.text = viewModel.getDetailPhoneObserver().value?.title
            textViewProcessorDetail.text = viewModel.getDetailPhoneObserver().value?.CPU
            textViewCameraDetail.text = viewModel.getDetailPhoneObserver().value?.camera
            textViewSsdDetail.text = viewModel.getDetailPhoneObserver().value?.ssd
            textViewSdrDetail.text = viewModel.getDetailPhoneObserver().value?.sd
            addToCartImageButton.text = "Add to Cart      $  " + viewModel.getDetailPhoneObserver().value?.price
            var firstColor = viewModel.getDetailPhoneObserver().value?.color?.get(0)
            var secondColor = viewModel.getDetailPhoneObserver().value?.color?.get(1)
            imageButtonColorFirst.setBackgroundColor(Color.parseColor(firstColor))
            imageButtonColorSecond.setBackgroundColor(Color.parseColor(secondColor))
            capacityFirstButton.text = viewModel.getDetailPhoneObserver().value?.capacity?.get(0) + "  GB"
            capacitySecondButton.text = viewModel.getDetailPhoneObserver().value?.capacity?.get(1) + "  gb"

            Glide.with(imageViewDetail)
                .load(viewModel.getDetailPhoneObserver().value?.images?.get(0))
                .placeholder(androidx.appcompat.R.drawable.abc_btn_check_material_anim)
                .into(imageViewDetail)
        }

        favoriteImageButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                favoriteImageButton.setBackgroundResource(R.drawable.ic_favorite_clickes)

            }
        })

        capacityFirstButton.setOnClickListener(object : View.OnClickListener {
            @SuppressLint("ResourceAsColor")
            override fun onClick(view: View?) {
                capacityFirstButton.setBackgroundColor(R.color.orange)
                capacityFirstButton.setTextColor(R.color.white)
            }
        })

        capacitySecondButton.setOnClickListener(object : View.OnClickListener {
            @SuppressLint("ResourceAsColor")
            override fun onClick(view: View?) {
                capacitySecondButton.setBackgroundColor(R.color.orange)
                capacitySecondButton.setTextColor(R.color.white)
            }
        })

        addToCartImageButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.place_holder, CartFragment())
                ?.addToBackStack("Main") //положила в стек
                ?.commit()
        }
    }

}