package com.emirli.eatup.ui.restaurantdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.emirli.eatup.R
import com.emirli.eatup.databinding.FragmentRestaurantBinding
import com.emirli.eatup.model.entity.Meal
import com.emirli.eatup.model.entity.Restaurant
import com.emirli.eatup.utils.Resource
import com.emirli.eatup.utils.adapter.MealItemAdapter
import com.emirli.eatup.utils.listener.IMealOnClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantDetailFragment : Fragment(){
    private lateinit var _binding : FragmentRestaurantBinding
    private val viewModel: RestaurantViewModel by viewModels()
    private val args: RestaurantDetailFragmentArgs by navArgs()

    private var mealAdapter = MealItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addObserver()
        addListener()
    }

    private fun initView() {
        _binding.mealRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun addObserver() {
        viewModel.getRestaurantById(args.restaurantId).observe(viewLifecycleOwner, { response ->
            if (response.status == Resource.Status.SUCCESS) {
                println("ESRAA ${response.data?.restaurantList?.get(0)}")
                response.data?.restaurantList?.get(0)?.let { setFields(it) }
            }
        })
    }

    private fun addListener() {
        mealAdapter.addListener(object : IMealOnClick{
            override fun onClick(meal: Meal) {
//                Log.v("Meal Click", meal.toString())
//                val action = RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToMealDetailFragment(meal)
//                findNavController().navigate(action)
            }
            override fun onClickBasket(meal: Meal) {
                Log.v("Meal Filter", meal.toString())
            }
        })
        _binding.previousButton.setOnClickListener{
            findNavController().popBackStack()
        }
        _binding.favoriteButton.setOnClickListener {
            Log.v("Restaurant Fav", "restaurant.toString()")
        }
    }

    private fun setFields(restaurant: Restaurant) {
        _binding.titleTextView.text = restaurant.name

        val options = RequestOptions().placeholder(R.drawable.no_data_yellow)
        Glide.with(_binding.imageView.context)
            .applyDefaultRequestOptions(options)
            .load(restaurant.imageUrl).into(_binding.imageView)

        _binding.priceLayout.textView.text = "$${restaurant.minimumPrice}"
        _binding.priceLayout.imageView.setBackgroundResource(R.mipmap.ic_price)

        _binding.deliveryTimeLayout.textView.text = restaurant.deliveryTime
        _binding.deliveryTimeLayout.imageView.setBackgroundResource(R.mipmap.ic_alarm)

        _binding.voteLayout.textView.text = restaurant.vote.toString()
        _binding.voteLayout.imageView.setBackgroundResource(R.mipmap.ic_vote)

        _binding.descriptionTextView.text = restaurant.detail

        mealAdapter.setData(restaurant.meals)
        _binding.mealRecyclerView.adapter = mealAdapter
    }


}