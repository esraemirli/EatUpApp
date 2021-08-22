package com.emirli.eatup.utils.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.emirli.eatup.R
import com.emirli.eatup.model.entity.basket.CartData
import com.emirli.eatup.utils.listener.IOrderRatingOnClick

class OrderItemAdapter : RecyclerView.Adapter<OrderItemAdapter.ViewHolder>() {
    private lateinit var cartList: MutableList<CartData>
    private var listener: IOrderRatingOnClick? = null
    private var defaultRating = 0F

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mealImageView: AppCompatImageView = view.findViewById(R.id.mealImageView)
        private val nameTextView: AppCompatTextView = view.findViewById(R.id.nameTextView)
        private val restaurantTextView: AppCompatTextView =
            view.findViewById(R.id.restaurantTextView)
        private val priceTextView: AppCompatTextView = view.findViewById(R.id.priceTextView)
        private val ratingBar: RatingBar = view.findViewById(R.id.ratingBar)

        fun bind(
            cart: CartData,
            listener: IOrderRatingOnClick?,
            defaultRating: Float,
            context: Context
        ) {
            nameTextView.text = cart.mealName
            priceTextView.text = context.getString(R.string.price_string, "$", cart.price)
            restaurantTextView.text = cart.restaurantName
            ratingBar.rating = defaultRating

            if (cart.rate != null) {
                ratingBar.setIsIndicator(true)
                ratingBar.rating = cart.rate
            }

            val options = RequestOptions().placeholder(R.drawable.no_data_yellow)
            Glide.with(mealImageView.context)
                .applyDefaultRequestOptions(options)
                .load(cart.imageUrl).into(mealImageView)

            ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
                listener?.onClick(rating, cart.mealId, cart.cartId)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_item_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = cartList[position]
        val context = holder.itemView.context
        holder.bind(item, listener, defaultRating, context)
    }

    override fun getItemCount(): Int = cartList.size

    fun setData(cartList: List<CartData>?) {
        defaultRating = 0F
        cartList?.let {
            this.cartList = cartList as MutableList<CartData>
            notifyDataSetChanged()
        }
    }

    fun addListener(listener: IOrderRatingOnClick?) {
        this.listener = listener
    }

    fun updateItem(cartId: Int, mealId: Int, vote: Float) {
        val order = cartList.first { it.cartId == cartId && it.mealId == mealId }
        val position = cartList.indexOf(order)
        defaultRating = vote
        notifyItemChanged(position)
    }

}