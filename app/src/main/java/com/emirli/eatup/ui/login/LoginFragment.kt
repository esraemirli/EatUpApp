package com.emirli.eatup.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emirli.eatup.ui.MainActivity
import com.emirli.eatup.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(){
    private lateinit var _binding : FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addListener()
    }

    private fun initView() {
        println("İNİT")
        viewModel.login().observe(viewLifecycleOwner, {
            println("ESRA $it\n${it.data?.token}")
        })
    }

    private fun addListener() {
        _binding.loginButton.setOnClickListener{
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }
}