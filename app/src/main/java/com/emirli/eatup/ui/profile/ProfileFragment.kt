package com.emirli.eatup.ui.profile

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.emirli.eatup.R
import com.emirli.eatup.databinding.FragmentProfileBinding
import com.emirli.eatup.model.entity.User
import com.emirli.eatup.model.entity.profile.UserRequest
import com.emirli.eatup.utils.Resource
import com.emirli.eatup.utils.gone
import com.emirli.eatup.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var _binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addListener()
    }

    private fun addListener() {
        _binding.saveButton.setOnClickListener { updateUserInformation() }
        _binding.closeImageButton.setOnClickListener { findNavController().popBackStack() }
    }

    private fun updateUserInformation() {
        val request = UserRequest(
            name = _binding.nameLayout.fieldEditText.text.toString(),
            phoneNumber = _binding.phoneLayout.fieldEditText.text.toString(),
            address = _binding.addressLayout.fieldEditText.text.toString(),
            password = _binding.passwordLayout.fieldEditText.text.toString(),
        )
        viewModel.updateUser(request).observe(viewLifecycleOwner, {

        })
    }

    private fun initView() {
        viewModel.getUserDetail().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> _binding.progressBar.show()
                Resource.Status.SUCCESS -> response.data?.let { setFields(it) }
                Resource.Status.ERROR -> _binding.progressBar.gone()
            }
        })
    }

    private fun setFields(user: User) {
        _binding.progressBar.gone()
        _binding.containerLinearLayout.show()

        val options = RequestOptions().placeholder(R.drawable.ic_profile)
        Glide.with(_binding.profileImageView.context)
            .applyDefaultRequestOptions(options)
            .load(user.imageUrl).into(_binding.profileImageView)

        _binding.mailTextView.text = user.mail

        _binding.phoneLayout.fieldImageButton.setBackgroundResource(R.drawable.ic_phone)
        _binding.phoneLayout.fieldEditText.inputType = InputType.TYPE_CLASS_PHONE
        _binding.phoneLayout.titleTextView.text = getString(R.string.phone_number_title)
        _binding.phoneLayout.fieldEditText.hint = getString(R.string.hint_phone)
        _binding.phoneLayout.fieldEditText.setText(user.phoneNumber)

        _binding.nameLayout.fieldImageButton.setBackgroundResource(R.drawable.ic_name)
        _binding.nameLayout.titleTextView.text = getString(R.string.name_title)
        _binding.nameLayout.fieldEditText.hint = getString(R.string.hint_name)
        _binding.nameLayout.fieldEditText.setText(user.name)

        _binding.addressLayout.fieldImageButton.setBackgroundResource(R.drawable.ic_location)
        _binding.addressLayout.fieldEditText.setText(user.address)
        _binding.addressLayout.titleTextView.text = getString(R.string.address_title)
        _binding.addressLayout.fieldEditText.hint = getString(R.string.hint_address)

        _binding.passwordLayout.fieldImageButton.setBackgroundResource(R.drawable.ic_password)
        _binding.passwordLayout.titleTextView.text = getString(R.string.password_title)
        _binding.passwordLayout.fieldEditText.hint = getString(R.string.hint_password)
    }
}