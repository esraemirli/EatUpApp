package com.emirli.eatup.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.emirli.eatup.R
import com.emirli.eatup.model.entity.Meal
import com.emirli.eatup.utils.listener.IMealOnClick

class MealItemAdapter : RecyclerView.Adapter<MealItemAdapter.ViewHolder>() {
    private lateinit var mealList: List<Meal>
    private var listener: IMealOnClick? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView: AppCompatTextView = view.findViewById(R.id.nameTextView)
        private val detailTextView: AppCompatTextView = view.findViewById(R.id.detailTextView)
        private val priceTextView: AppCompatTextView = view.findViewById(R.id.priceTextView)
        private val imageView: AppCompatImageView = view.findViewById(R.id.iconImageView)
        private val filterImageButton: AppCompatImageButton = view.findViewById(R.id.filterImageButton)
        private val containerLinearLayout: LinearLayout = view.findViewById(R.id.containerLinearLayout)

        fun bind(meal: Meal, listener: IMealOnClick?) {
            val options = RequestOptions().placeholder(R.drawable.no_data_yellow)
            Glide.with(imageView.context)
                .applyDefaultRequestOptions(options)
                .load(meal.imageUrl).into(imageView)
            nameTextView.text = meal.name
            detailTextView.text = "içindeki, içindeki .." //meal.ingredients.toString()
            priceTextView.text = "$${meal.price}" //TODO formatlamak lazım..

            containerLinearLayout.setOnClickListener {
                listener?.onClick(meal)
            }
            filterImageButton.setOnClickListener {
                listener?.onFilter(meal)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meal_item_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mealList[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int = mealList.size

    fun setData(mealList: List<Meal>?) {
        mealList?.let {
            this.mealList = mealList
            notifyDataSetChanged()
        }
    }

    fun addListener(listener: IMealOnClick?) {
        this.listener = listener
    }

}