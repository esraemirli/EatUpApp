package com.emirli.eatup.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.emirli.eatup.R
import com.emirli.eatup.databinding.FragmentRestaurantListingBinding
import com.emirli.eatup.utils.adapter.FavoriteRestaurantAdapter
import com.emirli.eatup.utils.listener.IRestaurantOnClick
import androidx.fragment.app.viewModels
import com.emirli.eatup.model.entity.Restaurant
import com.emirli.eatup.ui.restaurantlisting.RestaurantListingFragmentDirections
import com.emirli.eatup.utils.Resource.Status.SUCCESS
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(){
    private lateinit var _binding: FragmentRestaurantListingBinding
    private val viewModel: FavoriteViewModel by viewModels()

    private var restaurantAdapter = FavoriteRestaurantAdapter()

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
        _binding.restaurantRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        _binding.pageTitleTextView.text = getString(R.string.favorite_page_title)
    }

    private fun addListener() {
        _binding.previousButton.setOnClickListener {
            findNavController().popBackStack()
        }
        restaurantAdapter.addListener(object : IRestaurantOnClick {
            override fun onClick(restaurant: Restaurant) {
                val action = FavoriteFragmentDirections.actionFavoriteFragmentToRestaurantDetailFragment(restaurant.id)
                findNavController().navigate(action)
            }
        })
    }

    private fun addObserver() {
        viewModel.getFavoriteRestaurantList().observe(viewLifecycleOwner, { response ->
            if(response.status == SUCCESS)
                setRestaurant(response.data?.restaurantList)
        })
    }

    private fun setRestaurant(restaurantList: List<Restaurant>?) {
        restaurantAdapter.setData(restaurantList)
        _binding.restaurantRecyclerView.adapter = restaurantAdapter
    }

}