package com.emirli.eatup.ui.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emirli.eatup.databinding.FragmentEntryBinding
import com.emirli.eatup.ui.entry.login.LoginFragment
import com.emirli.eatup.ui.entry.signup.SignupFragment
import com.emirli.eatup.utils.EntryViewPager

class EntryFragment  : Fragment(){
    private lateinit var _binding: FragmentEntryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEntryBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val adapter = EntryViewPager(childFragmentManager)
        adapter.addFragment(LoginFragment(), "Login")
        adapter.addFragment(SignupFragment(), "Sign-up")
        _binding.viewPager.adapter = adapter
        _binding.tabLayout.setupWithViewPager(_binding.viewPager)
    }
}