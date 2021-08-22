package com.emirli.eatup.ui.lastorder

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emirli.eatup.R
import com.emirli.eatup.databinding.FragmentLastOrderBinding
import com.emirli.eatup.model.entity.Basket
import com.emirli.eatup.model.entity.CartData
import com.emirli.eatup.utils.Resource
import com.emirli.eatup.utils.adapter.OrderItemAdapter
import com.emirli.eatup.utils.gone
import com.emirli.eatup.utils.listener.IOrderRatingOnClick
import com.emirli.eatup.utils.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LastOrderFragment : Fragment(){
    private lateinit var _binding: FragmentLastOrderBinding
    private val viewModel: LastOrderViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLastOrderBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addListener()
    }

    private fun addListener() {
        _binding.previousButton.setOnClickListener {
            findNavController().popBackStack()
        }
        _binding.starAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                _binding.starAnimation.gone()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }
        })
    }


    private fun initView() {
        _binding.pageTitleTextView.text = getString(R.string.last_orders_title)
        getLastOrder()
    }

    private fun getLastOrder() {
        viewModel.getLastOrders().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> _binding.progressBar.show()
                Resource.Status.SUCCESS -> setOrders(response.data?.orderDetail)
                Resource.Status.ERROR -> isOrdersVisible(false)
            }
        })
    }


    private fun isOrdersVisible(isVisible: Boolean) {
        _binding.progressBar.gone()
        _binding.orderLinearLayout.isVisible = isVisible
        _binding.responseErrorLinearLayout.isVisible = isVisible.not()
    }

    private fun setOrders(orderList: List<Basket>?) {
        val isVisible = !orderList.isNullOrEmpty()
        isOrdersVisible(isVisible)

        orderList?.forEach { item ->
            val textView = createTextView(item.orderTime)
            val recyclerView = createRecyclerView(item.cartDataList)

            _binding.orderLinearLayout.addView(textView)
            _binding.orderLinearLayout.addView(recyclerView)
        }
    }

    private fun createTextView(orderTime: String): AppCompatTextView{
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0, 8, 0, 8)

        val textView = AppCompatTextView(requireContext())
        textView.text = orderTime
        textView.textSize = 18F
        textView.layoutParams = layoutParams
        textView.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
        return textView
    }

    private fun createRecyclerView(cartDataList: ArrayList<CartData>): RecyclerView {
        val recyclerView = RecyclerView(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val orderAdapter = OrderItemAdapter()
        orderAdapter.setData(cartDataList)
        recyclerView.adapter = orderAdapter

        orderAdapter.addListener(object : IOrderRatingOnClick{
            override fun onClick(vote: Float, mealId: Int, cartId : Int) {
                sendRating(vote,mealId,cartId, orderAdapter)
            }
        })
        return recyclerView
    }

    private fun sendRating(vote: Float, mealId: Int, cartId: Int, orderAdapter: OrderItemAdapter) {
        viewModel.rateOrder(vote,mealId,cartId).observe(viewLifecycleOwner, { response ->
            if(response.status == Resource.Status.SUCCESS) {
                _binding.starAnimation.show()
                orderAdapter.updateItem(cartId, mealId,vote)
            }
        })
    }

}