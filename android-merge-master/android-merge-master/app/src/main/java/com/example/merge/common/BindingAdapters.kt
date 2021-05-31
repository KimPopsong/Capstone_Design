package com.example.merge.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.merge.entities.ProductInfo
import com.example.merge.product.ProductListAdapter

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?){
    url ?: return
    Glide.with(view.context).load(url).into(view)
}

@BindingAdapter("productsList")
fun setSearchResults(view: RecyclerView, products: List<ProductInfo>?) {
    products ?: return

    (view.adapter as? ProductListAdapter)?.products = products
}