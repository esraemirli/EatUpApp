package com.emirli.eatup.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.auth0.android.jwt.JWT
import com.emirli.eatup.R
import com.emirli.eatup.databinding.FragmentSplashBinding
import com.emirli.eatup.model.local.SharedPrefManager
import com.emirli.eatup.ui.MainActivity

class SplashFragment : Fragment() {
    private lateinit var _binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        _binding.splashAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                val token = getToken()
                if (!isOnboardingSeen()) {
                    findNavController().navigate(R.id.action_splashFragment_to_onboardingBaseFragment)
                } else {
                    if (token.isNullOrEmpty()) {
                        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                    } else {
                        val jwt = JWT(token.split(" ")[1])
                        if (jwt.isExpired(0)) {
                            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                        } else {
                            val intent = Intent(context, MainActivity::class.java)
                            startActivity(intent)
                            requireActivity().finish()
                        }
                    }
                }
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })
    }

    private fun getToken(): String? {
        return SharedPrefManager(requireContext()).getToken()
    }

    private fun isOnboardingSeen(): Boolean {
        return SharedPrefManager(requireContext()).isOnboardingSeen()
    }
}