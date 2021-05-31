package com.example.merge.product.review;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.merge.R;
import com.example.merge.entities.ProductReview;

import java.util.ArrayList;
import java.util.List;

public class ProductReviewAdapter extends RecyclerView.Adapter<ProductReviewAdapter.ViewHolder> {

    @NonNull
    private List<ProductReview> reviews = new ArrayList<>();

    public void setReviews(List<ProductReview> reviews) {
        this.reviews = reviews;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_review_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductReview item = reviews.get(position);

        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(ProductReview review) {
            TextView userNameTv = itemView.findViewById(R.id.userName);
            TextView titleTv = itemView.findViewById(R.id.review_title);
            TextView contentTv = itemView.findViewById(R.id.content);
            RatingBar ratingTv = itemView.findViewById(R.id.item_ratingBar);
            TextView reviewTv = itemView.findViewById(R.id.review_date);

            userNameTv.setText(review.getMember().getName());
            titleTv.setText(review.getTitle());
            contentTv.setText(review.getContents());
            ratingTv.setRating(review.getRating());
            reviewTv.setText(review.getCreatedAt());
        }
    }
}
