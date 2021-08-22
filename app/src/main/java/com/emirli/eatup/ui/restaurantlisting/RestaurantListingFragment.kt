package com.emirli.eatup.ui.restaurantlisting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.emirli.eatup.R
import com.emirli.eatup.databinding.FragmentRestaurantListingBinding
import com.emirli.eatup.model.entity.restaurant.Restaurant
import com.emirli.eatup.utils.Resource.Status
import com.emirli.eatup.utils.adapter.RestaurantListingItemAdapter
import com.emirli.eatup.utils.gone
import com.emirli.eatup.utils.listener.IRestaurantOnClick
import com.emirli.eatup.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantListingFragment : Fragment() {
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
        _binding.restaurantRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        _binding.pageTitleTextView.text =
            getString(R.string.cuisine_string, args.cuisineName)
    }

    private fun addListener() {
        _binding.previousButton.setOnClickListener {
            findNavController().popBackStack()
        }
        restaurantAdapter.addListener(object : IRestaurantOnClick {
            override fun onClick(restaurant: Restaurant) {
                val action =
                    RestaurantListingFragmentDirections.actionRestaurantListingFragmentToRestaurantDetailFragment(
                        restaurant.id
                    )
                findNavController().navigate(action)
            }
        })
    }

    private fun addObserver() {
        viewModel.getRestaurantsByCuisine(args.cuisineId).observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Status.LOADING -> _binding.progressBar.show()
                Status.SUCCESS -> setRestaurant(response.data?.restaurantList)
                Status.ERROR -> isRestaurantListVisible(false)
            }
        })
    }

    private fun setRestaurant(restaurantList: List<Restaurant>?) {
        val isVisible = !restaurantList.isNullOrEmpty()
        isRestaurantListVisible(isVisible)

        restaurantAdapter.setData(restaurantList)
        _binding.restaurantRecyclerView.adapter = restaurantAdapter
    }

    private fun isRestaurantListVisible(isVisible: Boolean) {
        _binding.progressBar.gone()
        _binding.restaurantRecyclerView.isVisible = isVisible
        _binding.responseErrorLinearLayout.isVisible = isVisible.not()
    }

}