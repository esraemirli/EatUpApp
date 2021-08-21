package com.emirli.eatup.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.emirli.eatup.R
import com.emirli.eatup.model.entity.order.CartData
import com.emirli.eatup.utils.listener.ICartOnClick
import okhttp3.internal.notifyAll

class BasketItemAdapter : RecyclerView.Adapter<BasketItemAdapter.ViewHolder>(){
    private lateinit var cartList: MutableList<CartData>
    private var listener: ICartOnClick? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mealImageView: AppCompatImageView = view.findViewById(R.id.mealImageView)
        private val numberTextView: AppCompatTextView = view.findViewById(R.id.numberTextView)
        private val nameTextView: AppCompatTextView = view.findViewById(R.id.nameTextView)
        private val detailTextView: AppCompatTextView = view.findViewById(R.id.detailTextView)
        private val priceTextView: AppCompatTextView = view.findViewById(R.id.priceTextView)
        private val removeImageButton: AppCompatImageButton = view.findViewById(R.id.removeImageButton)

        fun bind(cart: CartData, listener: ICartOnClick?) {
            nameTextView.text = cart.mealName
            numberTextView.text = "${cart.quantity}x"
            priceTextView.text = "$${cart.price}"
            detailTextView.text = cart.ingredients.joinToString(separator = ","){it}

            val options = RequestOptions().placeholder(R.drawable.no_data_yellow)
            Glide.with(mealImageView.context)
                .applyDefaultRequestOptions(options)
                .load(cart.imageUrl).into(mealImageView)

            removeImageButton.setOnClickListener {
                listener?.onClick(cart)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.basket_item_layout, parent, false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = cartList[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int = cartList.size

    fun setData(cartList: List<CartData>?) {
        cartList?.let {
            this.cartList = cartList as MutableList<CartData>
            notifyDataSetChanged()
        }
    }

    fun addListener(listener: ICartOnClick?) {
        this.listener = listener
    }

    fun removeItem(cart: CartData) {
        cartList.remove(cart)
        notifyDataSetChanged()
    }

}