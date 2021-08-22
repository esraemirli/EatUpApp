package com.emirli.eatup.ui.onboarding

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.emirli.eatup.R
import com.emirli.eatup.databinding.FragmentBaseOnboardingBinding
import com.emirli.eatup.model.local.SharedPrefManager
import com.emirli.eatup.utils.DepthTransformation
import com.emirli.eatup.utils.adapter.ViewPagerAdapter

class OnboardingBaseFragment : Fragment() {
    private lateinit var _binding: FragmentBaseOnboardingBinding
    private var viewPagerCurrentItem = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBaseOnboardingBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        addListener()
    }

    private fun initViews() {
        val adapter = ViewPagerAdapter(requireActivity())
        _binding.viewPager.adapter = adapter
        _binding.viewPager.setPageTransformer(DepthTransformation())
    }

    private fun addListener() {
        _binding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                _binding.previousButton.isVisible = position != 0
                viewPagerCurrentItem = position
            }
        })
        _binding.nextButton.setOnClickListener {
            if(viewPagerCurrentItem != 2)
                _binding.viewPager.currentItem = viewPagerCurrentItem + 1
            else
                actionToLoginPage()
        }
        _binding.previousButton.setOnClickListener {
            _binding.viewPager.currentItem = viewPagerCurrentItem - 1
        }
        _binding.skipTextView.setOnClickListener {
            actionToLoginPage()
        }

    }

    private fun actionToLoginPage() {
        SharedPrefManager(requireContext()).setOnboardingSeen()
        findNavController().navigate(R.id.action_onboardingBaseFragment_to_loginFragment)
    }

}