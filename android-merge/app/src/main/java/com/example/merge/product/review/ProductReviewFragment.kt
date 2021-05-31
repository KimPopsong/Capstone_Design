package com.example.merge.product.review;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.merge.databinding.FragmentProductReviewBinding
import com.example.merge.entities.ProductReview
import com.example.merge.product.ProductViewModel
import com.example.merge.writereview.WriteReviewActivity

class ProductReviewFragment : Fragment() {

    private val viewModel: ProductViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentProductReviewBinding
                .inflate(inflater, container, false)
                .apply { vm = viewModel }
                .apply { lifecycleOwner = this@ProductReviewFragment }
                .apply { setView(this) }
                .root
    }

    private fun setView(binding: FragmentProductReviewBinding) {
        with(binding.reviewsRecyclerView) {
            adapter = ProductReviewAdapter()
        }

        with(viewModel) {
            writeNewReviewEvent.observe(this@ProductReviewFragment) {
                openWriteReviewActivity()
            }
        }
    }

    private fun openWriteReviewActivity() {
        startActivity(WriteReviewActivity.callingIntentWithProductId(context, viewModel.productId))
    }

    companion object {
        @JvmStatic
        @BindingAdapter("productReviews")
        fun setProductReview(recycler: RecyclerView, Review: List<ProductReview>?) {
            (recycler.adapter as? ProductReviewAdapter)?.setReviews(Review ?: return)
        }
    }
}