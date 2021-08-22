package com.emirli.eatup.utils.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.emirli.eatup.R
import com.emirli.eatup.model.entity.restaurant.Restaurant
import com.emirli.eatup.utils.listener.IRestaurantOnClick


class RestaurantListingItemAdapter :
    RecyclerView.Adapter<RestaurantListingItemAdapter.ViewHolder>() {
    private lateinit var restaurantList: MutableList<Restaurant>
    private var listener: IRestaurantOnClick? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView: AppCompatTextView = view.findViewById(R.id.nameTextView)
        private val minPriceTextView: AppCompatTextView = view.findViewById(R.id.minPriceTextView)
        private val deliveryTimeTextView: AppCompatTextView =
            view.findViewById(R.id.deliveryTimeTextView)
        private val voteTextView: AppCompatTextView = view.findViewById(R.id.voteTextView)
        private val imageView: AppCompatImageView = view.findViewById(R.id.iconImageView)
        private val containerLinearLayout: LinearLayout =
            view.findViewById(R.id.containerLinearLayout)

        fun bind(restaurant: Restaurant, listener: IRestaurantOnClick?, context: Context) {
            nameTextView.text = restaurant.name
            deliveryTimeTextView.text = restaurant.deliveryTime
            minPriceTextView.text =
                context.getString(R.string.price_string, "$", restaurant.minimumPrice)
            voteTextView.text = restaurant.vote.toString()

            val options = RequestOptions().placeholder(R.drawable.no_data_yellow)
            Glide.with(imageView.context)
                .applyDefaultRequestOptions(options)
                .load(restaurant.imageUrl).into(imageView)

            containerLinearLayout.setOnClickListener {
                listener?.onClick(restaurant)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cuisine_restaurant_listing_item_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = restaurantList[position]
        val context = holder.itemView.context
        holder.bind(item, listener, context)
    }

    override fun getItemCount(): Int = restaurantList.size

    fun setData(restaurantList: List<Restaurant>?) {
        restaurantList?.let {
            this.restaurantList = restaurantList as MutableList<Restaurant>
            notifyDataSetChanged()
        }
    }

    fun addListener(listener: IRestaurantOnClick?) {
        this.listener = listener
    }

}