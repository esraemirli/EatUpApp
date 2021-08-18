package com.emirli.eatup.ui.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emirli.eatup.databinding.FragmentHomeBinding
import com.emirli.eatup.model.entity.Cuisine
import dagger.hilt.android.AndroidEntryPoint
import android.view.LayoutInflater
import android.app.ActionBar.LayoutParams
import android.widget.Button
import com.emirli.eatup.R

@AndroidEntryPoint
class HomeFragment : Fragment(){
    private lateinit var _binding : FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

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
        addListener()
    }

    private fun addListener() {

    }

    private fun initView() {


    }



}