package com.emirli.eatup.ui.restaurantlisting

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
import com.emirli.eatup.databinding.FragmentRestaurantListingBinding
import com.emirli.eatup.model.entity.Restaurant
import com.emirli.eatup.utils.Resource
import com.emirli.eatup.utils.adapter.RestaurantListingItemAdapter
import com.emirli.eatup.utils.listener.IRestaurantOnClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantListingFragment : Fragment(){
    private lateinit var _binding: FragmentRestaurantListingBinding
    private val viewModel: RestaurantListingViewModel by viewModels()
    private val args: RestaurantListingFragmentArgs by navArgs()

    private var restaurantAdapter = RestaurantListingItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantListingBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addObserver()
        addListener()
    }

    private fun initView() {
        Log.v("Fragment" , "RestaurantListing")
        _binding.restaurantRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        _binding.pageTitleTextView.text = "${args.cuisineName} Restaurants"
    }

    private fun addListener() {
        _binding.previousButton.setOnClickListener {
            findNavController().popBackStack()
        }
        restaurantAdapter.addListener(object : IRestaurantOnClick {
            override fun onClick(restaurant: Restaurant) {
                val action = RestaurantListingFragmentDirections.actionRestaurantListingFragmentToRestaurantDetailFragment(restaurant.id)
                findNavController().navigate(action)
                Log.v("Click Restaurant" , restaurant.toString())
            }
        })
    }

    private fun addObserver() {
        viewModel.getRestaurantsByCuisine(args.cuisineId).observe(viewLifecycleOwner, { response ->
            if(response.status == Resource.Status.SUCCESS)
                setRestaurant(response.data?.restaurantList)
        })
    }

    private fun setRestaurant(restaurantList: List<Restaurant>?) {
        restaurantAdapter.setData(restaurantList)
        _binding.restaurantRecyclerView.adapter = restaurantAdapter
    }

}