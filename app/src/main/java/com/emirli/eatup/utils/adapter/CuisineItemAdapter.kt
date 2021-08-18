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
import com.emirli.eatup.model.entity.Cuisine
import com.emirli.eatup.utils.listener.ICuisineOnClick

class CuisineItemAdapter : RecyclerView.Adapter<CuisineItemAdapter.ViewHolder>() {
    private lateinit var cusineList: List<Cuisine>
    private var listener: ICuisineOnClick? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView: AppCompatTextView = view.findViewById(R.id.nameTextView)
        private val imageView: AppCompatImageView = view.findViewById(R.id.iconImageButton)
        private val containerLinearLayout: LinearLayout = view.findViewById(R.id.containerLinearLayout)

        fun bind(cuisine: Cuisine, listener: ICuisineOnClick?) {
            nameTextView.text = cuisine.name

            val options = RequestOptions().placeholder(R.mipmap.ic_launcher)    //TODO no data..
            Glide.with(imageView.context)
                .applyDefaultRequestOptions(options)
                .load(cuisine.imageUrl).into(imageView)

            containerLinearLayout.setOnClickListener {
                listener?.onClick(cuisine)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cuisine_item_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = cusineList[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int = cusineList.size

    fun setData(cusineList: List<Cuisine>?) {
        cusineList?.let {
            this.cusineList = cusineList
            notifyDataSetChanged()
        }
    }

    fun addListener(listener: ICuisineOnClick?) {
        this.listener = listener
    }

}