package com.example.sonrisaplantjv.presentation.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sonrisaplantjv.R;
import com.example.sonrisaplantjv.domain.dto.Category.CategoryDto;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder>{
    public final List<CategoryDto> categories;
    private final CategoryAdapter.ClickListener clickListener;
    private int selectedPosition = 0;

    public CategoryAdapter(List<CategoryDto> categories, CategoryAdapter.ClickListener clickListener) {
        this.categories = categories;
        categories.add(new CategoryDto("All"));
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        CategoryDto categoryDto = categories.get(position);
        holder.cate.setText(categoryDto.name);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.cate.getLayoutParams();
        params.setMargins(10, 4, 10, 4);
        if (position == selectedPosition) {
            holder.cate.setBackgroundResource(R.drawable.round_tv_cate_selected);
            holder.cate.setTextColor(Color.WHITE);
            holder.cate.setLayoutParams(params);
        } else {
            holder.cate.setBackgroundResource(R.drawable.round_text_view_cate);
            holder.cate.setTextColor(ContextCompat.getColor(holder.cate.getContext(), R.color.green_app_light));
            holder.cate.setLayoutParams(params);

        }
    }

    @Override
    public int getItemCount() {
        if (categories != null && categories.size() > 0)
            return categories.size();
        else
            return 0;
    }
    public void addItems(List<CategoryDto> items) {
        int startPosition = categories.size();
        categories.addAll(items);
        notifyItemRangeInserted(startPosition, items.size());
    }
    public interface ClickListener {
        void onItemClick(int position, View v);
    }
    public class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView cate;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cate = itemView.findViewById(R.id.tv_category_home);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position >= 0) {
                notifyItemChanged(selectedPosition);
                selectedPosition = position;
                notifyItemChanged(selectedPosition);
                clickListener.onItemClick(position, v);

            }
        }
    }
}
