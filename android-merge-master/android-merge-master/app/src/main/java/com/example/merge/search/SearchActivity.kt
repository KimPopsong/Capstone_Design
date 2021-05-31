package com.example.merge.search

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.example.merge.Config
import com.example.merge.R
import com.example.merge.databinding.ActivitySearchBinding
import com.example.merge.product.ProductActivity
import com.example.merge.product.ProductListAdapter

class SearchActivity : LocalizationActivity() {

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLanguage(Config.selectedLanguage)
        setView()
        observeEvents()
    }

    private fun setView() {
        val binding: ActivitySearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        binding.apply {
            lifecycleOwner = this@SearchActivity
            vm = viewModel
        }

        with(binding.resultsRecycler) {
            adapter = ProductListAdapter {
                showProductDetails(it.id)
            }
        }

        with(binding.mpSearchfield) {
            requestFocus();
        }
    }

    private fun showProductDetails(productId: Long) {
        startActivity(ProductActivity.callingIntentWithProductId(this, productId))
    }

    private fun observeEvents() {
        viewModel.goBackEvent.observe(this) {
            finish()
        }

        viewModel.requestFailedEvent.observe(this) {
            Toast.makeText(this, "메롱: ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }
}