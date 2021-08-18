package com.emirli.eatup.utils.adapter

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
import com.emirli.eatup.model.entity.Restaurant
import com.emirli.eatup.utils.listener.IRestaurantOnClick

class RestaurantItemAdapter : RecyclerView.Adapter<RestaurantItemAdapter.ViewHolder>() {
    private lateinit var restaurantList: List<Restaurant>
    private var listener: IRestaurantOnClick? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView: AppCompatTextView = view.findViewById(R.id.nameTextView)
        private val imageView: AppCompatImageView = view.findViewById(R.id.iconImageButton)
        private val containerLinearLayout: LinearLayout = view.findViewById(R.id.containerLinearLayout)

        fun bind(restaurant: Restaurant, listener: IRestaurantOnClick?) {
            nameTextView.text = restaurant.name

            val options = RequestOptions().placeholder(R.mipmap.ic_launcher)    //TODO no data..
            Glide.with(imageView.context)
                .applyDefaultRequestOptions(options)
                .load(restaurant.image).into(imageView)

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
            .inflate(R.layout.restaurant_item_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = restaurantList[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int = restaurantList.size

    fun setData(restaurantList: List<Restaurant>?) {
        restaurantList?.let {
            this.restaurantList = restaurantList
            notifyDataSetChanged()
        }
    }

    fun addListener(listener: IRestaurantOnClick?) {
        this.listener = listener
    }

}