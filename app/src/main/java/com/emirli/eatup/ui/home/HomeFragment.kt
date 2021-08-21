package com.emirli.eatup.ui.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emirli.eatup.databinding.FragmentHomeBinding
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.emirli.eatup.R
import com.emirli.eatup.model.entity.Cuisine
import com.emirli.eatup.model.entity.Restaurant
import com.emirli.eatup.utils.Resource
import com.emirli.eatup.utils.adapter.CuisineItemAdapter
import com.emirli.eatup.utils.adapter.RestaurantItemAdapter
import com.emirli.eatup.utils.gone
import com.emirli.eatup.utils.listener.ICuisineOnClick
import com.emirli.eatup.utils.listener.IRestaurantOnClick
import com.emirli.eatup.utils.show
import dagger.hilt.android.AndroidEntryPoint

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
//            .load(viewModel.imageUrl).into(_binding.profileImageButton)

        _binding.restaurantRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        _binding.cuisineRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun addObserver() {
        viewModel.getRestaurantList().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> _binding.restaurantProgressBar.show()
                Resource.Status.SUCCESS -> setRestaurant(response.data?.restaurantList)
                Resource.Status.ERROR -> {
                    _binding.restaurantProgressBar.gone()
                    _binding.restaurantErrorLinearLayout.show()
                }
            }
        })
        viewModel.getCuisineList().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> _binding.cuisineProgressBar.show()
                Resource.Status.SUCCESS -> setCuisineList(response.data?.cuisineList)
                Resource.Status.ERROR -> {
                    _binding.cuisineProgressBar.gone()
                    _binding.cuisineErrorLinearLayout.show()
                }
            }
            if (response.status == Resource.Status.SUCCESS)
                setCuisineList(response.data?.cuisineList)
        })
    }

    private fun addListener() {
        restaurantAdapter.addListener(object : IRestaurantOnClick {
            override fun onClick(restaurant: Restaurant) {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToRestaurantDetailFragment(restaurant.id)
                findNavController().navigate(action)
            }
        })
        cuisineAdapter.addListener(object : ICuisineOnClick {
            override fun onClick(cuisine: Cuisine) {
                val action = HomeFragmentDirections.actionHomeFragmentToRestaurantListingFragment(
                    cuisine.id,
                    cuisine.name
                )
                findNavController().navigate(action)
            }
        })
    }

    private fun setCuisineList(cuisineList: List<Cuisine>?) {
        _binding.cuisineProgressBar.gone()
        _binding.cuisineErrorLinearLayout.isVisible = cuisineList.isNullOrEmpty()
        _binding.cuisineRecyclerView.show()

        cuisineAdapter.setData(cuisineList)
        _binding.cuisineRecyclerView.adapter = cuisineAdapter
    }

    private fun setRestaurant(restaurantList: List<Restaurant>?) {
        _binding.restaurantProgressBar.gone()
        _binding.restaurantErrorLinearLayout.isVisible = restaurantList.isNullOrEmpty()
        _binding.restaurantRecyclerView.show()

        restaurantAdapter.setData(restaurantList)
        _binding.restaurantRecyclerView.adapter = restaurantAdapter
    }

}