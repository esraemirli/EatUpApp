package com.emirli.eatup.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emirli.eatup.databinding.FragmentHomeBinding
import com.emirli.eatup.model.entity.Cuisine
import dagger.hilt.android.AndroidEntryPoint
import android.view.LayoutInflater
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.emirli.eatup.R
import com.emirli.eatup.model.entity.Restaurant
import com.emirli.eatup.utils.adapter.CuisineItemAdapter
import com.emirli.eatup.utils.adapter.RestaurantItemAdapter
import com.emirli.eatup.utils.listener.ICuisineOnClick
import com.emirli.eatup.utils.listener.IRestaurantOnClick

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var _binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private var restaurantAdapter = RestaurantItemAdapter()
    private var cuisineAdapter = CuisineItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addObserver()
        addListener()
    }

    private fun initView() {
        val options = RequestOptions().placeholder(R.drawable.ic_profile)
        Glide.with(_binding.profileImageButton.context)
            .applyDefaultRequestOptions(options)
            .load(viewModel.imageUrl).into(_binding.profileImageButton)

        _binding.restaurantRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        _binding.cuisineRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun addObserver() {
        viewModel.restaurantList.observe(viewLifecycleOwner, { restaurantList ->
            setRestaurant(restaurantList)
        })
        viewModel.cuisineList.observe(viewLifecycleOwner, { cuisineList ->
            setCuisineList(cuisineList)
        })
    }

    private fun setCuisineList(cuisineList: List<Cuisine>) {
        cuisineAdapter.setData(cuisineList)
        _binding.cuisineRecyclerView.adapter = cuisineAdapter
    }

    private fun setRestaurant(restaurantList: List<Restaurant>?) {
        restaurantAdapter.setData(restaurantList)
        _binding.restaurantRecyclerView.adapter = restaurantAdapter
    }

    private fun addListener() {
        restaurantAdapter.addListener( object : IRestaurantOnClick{
            override fun onClick(restaurant: Restaurant) {
                val action = HomeFragmentDirections.actionHomeFragmentToRestaurantDetailFragment(restaurant)
                findNavController().navigate(action)
                Log.v("Click Restaurant" , restaurant.toString())
            }
        })
        cuisineAdapter.addListener( object : ICuisineOnClick{
            override fun onClick(cuisine: Cuisine) {
                val action = HomeFragmentDirections.actionHomeFragmentToRestaurantListingFragment(cuisine)
                findNavController().navigate(action)
                Log.v("Click Cuisine" , cuisine.toString())
            }
        })
    }


}