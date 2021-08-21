package com.emirli.eatup.ui.restaurantdetail

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
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.emirli.eatup.R
import com.emirli.eatup.databinding.FragmentRestaurantBinding
import com.emirli.eatup.model.entity.Meal
import com.emirli.eatup.model.entity.Restaurant
import com.emirli.eatup.model.entity.basket.BasketRequest
import com.emirli.eatup.utils.Resource.Status
import com.emirli.eatup.utils.adapter.MealItemAdapter
import com.emirli.eatup.utils.gone
import com.emirli.eatup.utils.listener.IMealOnClick
import com.emirli.eatup.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantDetailFragment : Fragment() {
    private lateinit var _binding: FragmentRestaurantBinding
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
            when (response.status) {
                Status.LOADING -> _binding.progressBar.show()
                Status.SUCCESS -> response.data?.restaurantList?.get(0)?.let { setFields(it) }
                Status.ERROR -> isRestaurantListVisible(false)
            }
        })
    }

    private fun isRestaurantListVisible(isVisible: Boolean) {
        _binding.progressBar.gone()
        _binding.containerLinearLayout.isVisible = isVisible
        _binding.responseErrorLinearLayout.isVisible = isVisible.not()
    }

    private fun addListener() {
        mealAdapter.addListener(object : IMealOnClick {
            override fun onClick(meal: Meal) {
                val action =
                    RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToMealDetailFragment(
                        meal.id
                    )
                findNavController().navigate(action)
            }

            override fun onClickBasket(meal: Meal) {
                addBasket(meal)
            }
        })
        _binding.previousButton.setOnClickListener {
            findNavController().popBackStack()
        }
        _binding.addFavoriteButton.setOnClickListener {
            viewModel.addFavoriteRestaurant(args.restaurantId)
                .observe(viewLifecycleOwner, { response ->
                    if (response.status == Status.SUCCESS)
                        setFavoriteButtonVisibility(isFavorite = true)
                })
        }
        _binding.removeFavoriteButton.setOnClickListener {
            viewModel.removeFavoriteRestaurant(args.restaurantId)
                .observe(viewLifecycleOwner, { response ->
                    if (response.status == Status.SUCCESS)
                        setFavoriteButtonVisibility(isFavorite = false)
                })
        }
    }

    private fun addBasket(meal: Meal) {
        val request = BasketRequest(mealId = meal.id, number = 1)
        viewModel.addBasket(request).observe(viewLifecycleOwner, { response ->
            if (response.status == Status.SUCCESS) {
                mealAdapter.isBasketAnimationVisible(meal, true)
            }
        })
    }

    private fun setFavoriteButtonVisibility(isFavorite: Boolean?) {
        _binding.addFavoriteButton.isVisible = isFavorite?.not() ?: false
        _binding.removeFavoriteButton.isVisible = isFavorite ?: false
    }

    private fun setFields(restaurant: Restaurant) {
        isRestaurantListVisible(true)

        _binding.titleTextView.text = restaurant.name
        setFavoriteButtonVisibility(restaurant.isFavorite)

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