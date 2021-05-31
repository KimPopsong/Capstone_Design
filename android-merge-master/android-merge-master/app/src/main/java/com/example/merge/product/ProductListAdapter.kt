package com.example.merge.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.merge.Config
import com.example.merge.R
import com.example.merge.entities.ProductInfo
import com.example.merge.untility.GoogleTranslate

/**
 * 제품 목록 어댑터!
 * SearchActivity 와 MainActivity 에서 사용됨!
 */
class ProductListAdapter(
    private val onItemClick: (ProductInfo) -> Any?
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    var products: List<ProductInfo> = listOf()
        set(value) {
            field = value

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = products[position]

        holder.bind(item)
    }

    override fun getItemCount() = products.size

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ProductInfo) {
            view.findViewById<LinearLayout>(R.id.rootView).setOnClickListener {
                onItemClick(item)
            }

            val imgUrl: String = Config.productImageBaseUrl + item.image_name;

            Glide.with(view.context)
                .load(imgUrl)
                .into(view.findViewById(R.id.productImage))

            view.findViewById<TextView>(R.id.productName).text = item.name

            if (Config.selectedLanguage.equals("ko")) {
                view.findViewById<TextView>(R.id.productNameTranslated).text = null
                view.findViewById<TextView>(R.id.productNameTranslated).visibility = View.GONE
            } else {
                view.findViewById<TextView>(R.id.productNameTranslated).text =
                    GoogleTranslate.translate(item.name)
            }

            view.findViewById<TextView>(R.id.productManufacturer).text = item.manufacturer
        }
    }
}