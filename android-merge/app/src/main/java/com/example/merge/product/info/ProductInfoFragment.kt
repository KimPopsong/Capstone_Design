package com.example.merge.product.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.merge.databinding.FragmentProductInfoBinding
import com.example.merge.entities.ProductInfo
import com.example.merge.product.ProductViewModel

class ProductInfoFragment : Fragment() {

    private val viewModel: ProductViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentProductInfoBinding
                .inflate(inflater, container, false)
                .apply { vm = viewModel }
                .apply { lifecycleOwner = this@ProductInfoFragment }
                .apply { setView(this) }
                .root
    }

    private fun setView(binding: FragmentProductInfoBinding) {
        with(binding.productAttributesRecyclerView) {
            adapter = ProductInfoAdapter()
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("productInfo")
        fun setProductInfo(recycler: RecyclerView, info: ProductInfo?) {
            (recycler.adapter as? ProductInfoAdapter)?.setProductInfo(info ?: return)
        }
    }
}