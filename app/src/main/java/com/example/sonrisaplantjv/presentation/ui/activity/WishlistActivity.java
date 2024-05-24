package com.example.sonrisaplantjv.presentation.ui.activity;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sonrisaplantjv.databinding.ActivityWishlistBinding;
import com.example.sonrisaplantjv.domain.dto.Category.CategoryDto;
import com.example.sonrisaplantjv.domain.dto.Plant.PlantDto;
import com.example.sonrisaplantjv.domain.dto.Plant.PlantRequestParameters;
import com.example.sonrisaplantjv.presentation.adapter.CategoryAdapter;
import com.example.sonrisaplantjv.presentation.adapter.PlantAdapter;
import com.example.sonrisaplantjv.presentation.ui.components.PaginationScrollListener;
import com.example.sonrisaplantjv.presentation.viewmodels.CategoryViewModel;
import com.example.sonrisaplantjv.presentation.viewmodels.PlantViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WishlistActivity extends AppCompatActivity {
    ActivityWishlistBinding binding;
    PlantViewModel plantViewModel;
    CategoryViewModel categoryViewModel;
    CategoryAdapter categoryAdapter;
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager layoutCategory;
    PlantAdapter plantAdapter;
    boolean isLoading = false;
    boolean isLastPage = false;
    boolean wait = false;
    boolean isFirstReloadCategory = true;
    UUID idCate = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityWishlistBinding.inflate(getLayoutInflater());
//        binding = DataBindingUtil.setContentView(WishlistActivity.this, R.layout.activity_wishlist);
        initMostPopular();
        initCategory();
        init();

        setContentView(binding.getRoot());
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_wishlist), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
    private void init(){
        binding.toolBarWishlist.tvToolbar.setText("My Wishlist");
        binding.toolBarWishlist.btnArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.toolBarWishlist.btnSearchToolBar.setVisibility(View.VISIBLE);
    }
    private void initCategory(){
        categoryAdapter = new CategoryAdapter(new ArrayList<CategoryDto>(), new CategoryAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                isFirstReloadCategory = true;
                idCate = categoryAdapter.categories.get(position).id;
                getListOfMostPopular(idCate);
            }
        });
        binding.rcvWishlistCate.setAdapter(categoryAdapter);
        binding.rcvWishlistCate.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.right = 12;
            }
        });
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        layoutCategory = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        getListCategory();
    }
    private void getListCategory() {
        categoryViewModel.getAll().observe(this, categoryDtos -> {
            setRecyclerViewCategory(categoryDtos);
        });
    }

    private void setRecyclerViewCategory(List<CategoryDto> categoryDtos) {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.rcvWishlistCate.setLayoutManager(layoutCategory);
        } else {
            binding.rcvWishlistCate.setLayoutManager(new GridLayoutManager(this, 4));
        }
        categoryAdapter.addItems(categoryDtos);
    }

    //3
    private void initMostPopular(){
        plantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);
        plantAdapter = new PlantAdapter(new ArrayList<PlantDto>(), new PlantAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
            }

            @Override
            public void onClickLove(int position, View v) {
                if (plantAdapter.plants.get(position).isLove){
                    plantViewModel.removeFromWishList(plantAdapter.plants.get(position).id);
                    plantViewModel.getRemoveFromWishListResult().observe(WishlistActivity.this,result ->{
                        if (result){
                            plantAdapter.plants.get(position).isLove = false;
                        }
                    });
                }else {
                    plantViewModel.addToWishList(plantAdapter.plants.get(position).id);
                    plantViewModel.getAddToWishListResult().observe(WishlistActivity.this,result ->{
                        if (result){
                            plantAdapter.plants.get(position).isLove = true;
                        }
                    });
                }
            }
        });
        binding.rcvWishlistPlant.setAdapter(plantAdapter);
        binding.rcvWishlistPlant.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.right = 24;
            }
        });
        gridLayoutManager = new GridLayoutManager(this, 2);
        binding.rcvWishlistPlant.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @Override
            public void loadMoreItem() {
                isLoading =true;
                isFirstReloadCategory = false;
                if (!wait) {
                    plantViewModel.setPageNumber(new MutableLiveData<>(plantViewModel.getPageNumber().getValue() + 1));
                    getListOfMostPopular(idCate);
                }
                isLoading = false;
            }
            @Override
            public boolean isLoading() {
                return isLoading;
            }
            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });
        getListOfMostPopular(idCate);
    }
    private void getListOfMostPopular(@Nullable UUID idCate) {
        PlantRequestParameters request = new PlantRequestParameters();
        request.categoryName = PlantRequestParameters.CategoryName.MostPopular;
        request.IdCategory = idCate;
        request.PageSize = plantViewModel.getPageSize().getValue();
        wait = true;
        plantViewModel.getPageNumber().observe(WishlistActivity.this, page ->{
            request.PageNumber = page;
            plantViewModel.getPlantInWishList(request).observe(WishlistActivity.this, plantDtos -> {
                if (plantDtos.isEmpty()){
                    isLastPage = true;
                    return;
                }else if (plantDtos.size() < plantViewModel.getPageSize().getValue()){
                    isLastPage = true;
                }
                setRecyclerViewMostPopular(plantDtos, isFirstReloadCategory);
            });
        });
        if (isFirstReloadCategory){
            plantViewModel.getIsNullCall().observe(WishlistActivity.this, isNull -> {
                if (isNull){
                    plantAdapter.remove();
                }
            });
        }
    }

    private void setRecyclerViewMostPopular(List<PlantDto> plantDtos, boolean isFirst) {
        binding.rcvWishlistPlant.setLayoutManager(gridLayoutManager);
        if (isFirst){
            plantAdapter.setItems(plantDtos);
        }else {
            plantAdapter.addItems(plantDtos);
        }
        if (!isLastPage) wait = false;
    }
}