package com.example.merge.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.merge.Config
import com.example.merge.R
import com.example.merge.entities.ProductInfo

class SearchAdapter(
        private val onItemClick: (ProductInfo) -> Any?
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var results: List<ProductInfo> = listOf()
        set(value) {
            field = value

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = results[position]

        holder.bind(item)
    }

    override fun getItemCount() = results.size

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ProductInfo) {
            view.findViewById<LinearLayout>(R.id.rootView).setOnClickListener {
                // 항목을 선택했을 때에는 어댑터가 생성자 인자로 받았던 그 클릭 리스너를 호출함으로써 어댑터를 사용하는 쪽(아마 액티비티?)에게 이 클릭 이벤트를 알린다!
                onItemClick(item)
            }

            val imgUrl: String = Config.productImageBaseUrl + item.image_name;

            Glide.with(view.context).load(imgUrl).into(view.findViewById<ImageView>(R.id.productImage))  // Glide로 imageView에 이미지 추가
            view.findViewById<TextView>(R.id.productName).text = item.name
            //view.findViewById<TextView>(R.id.productCompany).text = item.manufacturer
        }
    }
}