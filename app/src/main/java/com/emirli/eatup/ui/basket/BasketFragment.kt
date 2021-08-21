package com.emirli.eatup.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.emirli.eatup.databinding.FragmentBasketBinding
import com.emirli.eatup.model.entity.order.CartData
import com.emirli.eatup.utils.Resource
import com.emirli.eatup.utils.adapter.BasketItemAdapter
import com.emirli.eatup.utils.gone
import com.emirli.eatup.utils.listener.ICartOnClick
import com.emirli.eatup.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment() {
    private lateinit var _binding: FragmentBasketBinding
    private val viewModel: BasketViewModel by viewModels()

    private var basketAdapter = BasketItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addListener()
        getBasketItemData()
    }

    private fun initView() {
        _binding.basketRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        _binding.pageTitleTextView.text = "My Basket"

    }

    private fun getBasketItemData() {
        viewModel.getBasketItemList().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> _binding.progressBar.show()
                Resource.Status.SUCCESS -> {
                    _binding.totalPriceTextView.text =
                        "$${response.data?.basketData?.totalPrice}"
                    setBasketItem(response.data?.basketData?.cartDataList)
                }
                Resource.Status.ERROR -> isPageVisible(false)
            }
        })
    }

    private fun isPageVisible(isVisible: Boolean) {
        _binding.progressBar.gone()
        _binding.basketRecyclerView.isVisible = isVisible
        _binding.orderLinearLayout.isVisible = isVisible
        _binding.responseErrorLinearLayout.isVisible = isVisible.not()
    }

    private fun setBasketItem(cartList: ArrayList<CartData>?) {
        val isVisible = !cartList.isNullOrEmpty()
        isPageVisible(isVisible)

        basketAdapter.setData(cartList)
        _binding.basketRecyclerView.adapter = basketAdapter
    }

    private fun addListener() {
        _binding.previousButton.setOnClickListener {
            findNavController().popBackStack()
        }
        _binding.buyBasketButton.setOnClickListener {
            viewModel.buyBasket().observe(viewLifecycleOwner, { response ->
                if (response.status == Resource.Status.SUCCESS) {
                    _binding.basketAnimation.show()
                    _binding.buyBasketButton.gone()
                }
            })
        }
        basketAdapter.addListener(object : ICartOnClick {
            override fun onClick(cart: CartData) {
                viewModel.removeItemFromBasket(cart.mealId)
                    .observe(viewLifecycleOwner, { response ->
                        if (response.status == Resource.Status.SUCCESS)
                            getBasketItemData()
                    })
            }
        })
    }

}