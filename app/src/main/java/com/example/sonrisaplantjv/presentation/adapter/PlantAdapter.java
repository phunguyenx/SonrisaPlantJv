package com.example.sonrisaplantjv.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sonrisaplantjv.R;
import com.example.sonrisaplantjv.domain.dto.Plant.PlantDto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantHomeHolder> {
    public List<PlantDto> plants;
    private final PlantAdapter.ClickListener clickListener;
    public PlantAdapter(List<PlantDto> plants, ClickListener clickListener){
        this.plants = plants;
        this.clickListener = clickListener;
    }
    @NonNull
    @Override
    public PlantHomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_item_home, parent, false);
        return new PlantHomeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantHomeHolder holder, int position) {
        PlantDto plant = plants.get(position);
        holder.name.setText(plant.name);
        holder.quantitySold.setText(plant.quantitySold + " Sold");
        holder.ratingPoint.setText(plant.ratingPoint + "");
        holder.price.setText(plant.price + "$");
        Picasso.get().load(plant.image).into(holder.imgPlant);
        if (plant.isLove){
            holder.addToWishList.setBackgroundResource(R.drawable.fill_heart_36);
        }else {
            holder.addToWishList.setBackgroundResource(R.drawable.icons8_heart_36);
        }
    }

    @Override
    public int getItemCount() {
        if (plants != null && plants.size() > 0)
            return plants.size();
        else
            return 0;
    }
    public void addItems(List<PlantDto> items) {
        int startPosition = plants.size();
        plants.addAll(items);
        notifyItemRangeInserted(startPosition, items.size());
    }
    public void setItems(List<PlantDto> items) {
        remove();
        addItems(items);
    }
    public void remove(){
        notifyItemRangeRemoved(0, plants.size());
        plants.clear();
    }
    public interface ClickListener {
        void onItemClick(int position, View v);

        void onClickLove(int position, View v);

    }

    public class PlantHomeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgPlant;
        TextView name, ratingPoint, quantitySold, price;
        Button addToWishList;
        public PlantHomeHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imgPlant = itemView.findViewById(R.id.img_plant_home);
            name = itemView.findViewById(R.id.tv_name_plant_home);
            ratingPoint = itemView.findViewById(R.id.tv_rating_point_home);
            quantitySold = itemView.findViewById(R.id.tv_quantity_sold_home);
            price = itemView.findViewById(R.id.tv_plant_price_home);
            addToWishList = itemView.findViewById(R.id.btn_add_wishlist);
            addToWishList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!plants.get(getAdapterPosition()).isLove){
                        addToWishList.setBackgroundResource(R.drawable.fill_heart_36);
                    }else {
                        addToWishList.setBackgroundResource(R.drawable.icons8_heart_36);
                    }
                    clickListener.onClickLove(getAdapterPosition(), v);
                }
            });
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position >= 0) {
                clickListener.onItemClick(position, v);
            }
        }
    }

}
