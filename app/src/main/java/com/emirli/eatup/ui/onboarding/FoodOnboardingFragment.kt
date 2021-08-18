package com.emirli.eatup.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emirli.eatup.R
import com.emirli.eatup.databinding.FragmentOnboardingBinding

class FoodOnboardingFragment : Fragment() {
    private lateinit var _binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.contentImageView.setImageResource(R.drawable.onboarding_food)
        _binding.contentTextView.text = getString(R.string.onboarding_food_text)
    }
}