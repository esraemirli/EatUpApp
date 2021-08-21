package com.emirli.eatup.ui.mealdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.emirli.eatup.R
import com.emirli.eatup.databinding.FragmentMeailDetailBinding
import com.emirli.eatup.model.entity.Meal
import com.emirli.eatup.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailFragment : Fragment() {
    private lateinit var _binding: FragmentMeailDetailBinding
    private val viewModel: MealDetailViewModel by viewModels()
    private val args: MealDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMeailDetailBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        addObserver()
        addListener()
    }

    private fun initView() {
        Log.v("Fragment", "MealDetailFragment")
    }

    private fun addObserver() {
        viewModel.getMealById(args.mealId).observe( viewLifecycleOwner, { response ->
            if (response.status == Resource.Status.SUCCESS)
                response.data?.mealList?.get(0)?.let { setFields(it) }
        })
    }

    private fun addListener() {
        _binding.previousButton.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun setFields(meal: Meal) {
        _binding.titleTextView.text = meal.name
        val options = RequestOptions().placeholder(R.drawable.no_data_yellow)
        Glide.with(_binding.imageView.context)
            .applyDefaultRequestOptions(options)
            .load(meal.imageUrl).into(_binding.imageView)

        _binding.calorieLayout.textView.text = "${meal.calorie} cal"
        _binding.calorieLayout.imageView.setBackgroundResource(R.mipmap.ic_calorie)

        _binding.voteLayout.textView.text = "${meal.vote} vote"
        _binding.voteLayout.imageView.setBackgroundResource(R.mipmap.ic_vote)

        _binding.quantityLayout.textView.text = "${meal.quantity}g"
        _binding.quantityLayout.imageView.setBackgroundResource(R.mipmap.ic_quantity)

        _binding.descriptionTextView.text = meal.ingredients.joinToString (separator = ",") { it }
        _binding.priceTextView.text = "$${meal.calorie}"
    }

}