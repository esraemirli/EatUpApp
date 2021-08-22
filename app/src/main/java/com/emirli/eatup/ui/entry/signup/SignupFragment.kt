package com.emirli.eatup.ui.entry.signup

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emirli.eatup.databinding.FragmentSignupBinding
import com.emirli.eatup.model.entity.login.RegisterRequest
import com.emirli.eatup.ui.MainActivity
import com.emirli.eatup.utils.Resource
import com.emirli.eatup.utils.gone
import com.emirli.eatup.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : Fragment() {
    private lateinit var _binding: FragmentSignupBinding
    private val viewModel: SignupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListener()
    }

    private fun addListener() {
        _binding.signupButton.setOnClickListener { signUp() }
        _binding.signupAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })
    }

    private fun signUp() {
        val name = _binding.nameTextView.editText?.text.toString()
        val email = _binding.emailTextView.editText?.text.toString()
        val password = _binding.passwordTextView.editText?.text.toString()

        val request = RegisterRequest(email, name, password)
        viewModel.signup(request).observe(viewLifecycleOwner, { response ->
            if (response.status == Resource.Status.SUCCESS) {
                _binding.containerLinearLayout.gone()
                _binding.signupAnimation.show()
            }
        })
    }


}