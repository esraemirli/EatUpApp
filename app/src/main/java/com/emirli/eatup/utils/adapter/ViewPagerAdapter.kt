package com.emirli.eatup.utils.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.emirli.eatup.ui.onboarding.DeliveryOnBoardingFragment
import com.emirli.eatup.ui.onboarding.FoodOnboardingFragment
import com.emirli.eatup.ui.onboarding.MenuOnboardingFragment

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> MenuOnboardingFragment()
            1 -> DeliveryOnBoardingFragment()
            else -> FoodOnboardingFragment()
        }
    }
}