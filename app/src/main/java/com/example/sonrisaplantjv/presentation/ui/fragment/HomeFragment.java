package com.example.sonrisaplantjv.presentation.ui.fragment;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sonrisaplantjv.R;
import com.example.sonrisaplantjv.common.Constant;
import com.example.sonrisaplantjv.databinding.FragmentHomeBinding;
import com.example.sonrisaplantjv.domain.dto.Category.CategoryDto;
import com.example.sonrisaplantjv.domain.dto.Plant.PlantDto;
import com.example.sonrisaplantjv.domain.dto.Plant.PlantRequestParameters;
import com.example.sonrisaplantjv.presentation.adapter.CategoryAdapter;
import com.example.sonrisaplantjv.presentation.adapter.PlantAdapter;
import com.example.sonrisaplantjv.presentation.adapter.PlantHomeAdapter;
import com.example.sonrisaplantjv.presentation.ui.components.PaginationScrollListener;
import com.example.sonrisaplantjv.presentation.viewmodels.CategoryViewModel;
import com.example.sonrisaplantjv.presentation.viewmodels.PlantHomeViewModel;
import com.example.sonrisaplantjv.presentation.viewmodels.PlantViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    PlantHomeViewModel specialOrder;
    PlantViewModel mostPopular;
    CategoryViewModel categoryViewModel;
    PlantHomeAdapter adapter;
    PlantAdapter adapter1;
    CategoryAdapter categoryAdapter;
    boolean isLoading = false;
    boolean isLastPage = false;
    boolean wait = false;
    boolean isLoading1 = false;
    boolean isLastPage1 = false;
    boolean wait1 = false;
    boolean isFirst = true;
    UUID idCate = null;
    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager layoutCategory;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);
        if (Constant.isNetworkAvailable(getContext())) {
            initMostPopular();
            initSpecialOffer();
            initCategory();
        } else {
            Toast.makeText(getContext(), "No network connection", Toast.LENGTH_SHORT).show();
        }
        return binding.getRoot();

    }
    private void initSpecialOffer(){
        specialOrder = new ViewModelProvider(this).get(PlantHomeViewModel.class);
        adapter = new PlantHomeAdapter(new ArrayList<PlantDto>(), new PlantHomeAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Toast.makeText(v.getContext(), adapter.plants.get(position).id.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickLove(int position, View v) {
                if (adapter.plants.get(position).isLove){
                    specialOrder.removeFromWishList(adapter.plants.get(position).id);
                    specialOrder.getRemoveFromWishListResult().observe(getViewLifecycleOwner(),result ->{
                        if (result){
                            adapter.plants.get(position).isLove = false;
                        }
                    });
                }else {
                    specialOrder.addToWishList(adapter.plants.get(position).id);
                    specialOrder.getAddToWishListResult().observe(getViewLifecycleOwner(),result ->{
                        if (result){
                            adapter.plants.get(position).isLove = true;
                        }
                    });
                }
            }
        });
        binding.rcvSpecialOffers.setAdapter(adapter);
        binding.rcvSpecialOffers.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.right = 24;
            }
        });
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rcvSpecialOffers.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            public void loadMoreItem() {
                isLoading =true;
                if (!wait) {
                    specialOrder.setPageNumber(new MutableLiveData<>(specialOrder.getPageNumber().getValue() + 1));
                    getListOfSpecialPlants();
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
        getListOfSpecialPlants();
    }
    private void initCategory(){
        categoryAdapter = new CategoryAdapter(new ArrayList<CategoryDto>(), new CategoryAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                isFirst = true;
                idCate = categoryAdapter.categories.get(position).id;
                getListOfMostPopular(idCate);
            }
        });
        binding.rcvMostPopularCate.setAdapter(categoryAdapter);
        binding.rcvMostPopularCate.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.right = 12;
            }
        });
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        layoutCategory = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        getListCategory();
    }

    private void getListOfSpecialPlants() {
        PlantRequestParameters request = new PlantRequestParameters();
        request.categoryName = PlantRequestParameters.CategoryName.SpecialOffer;
        request.PageSize = specialOrder.getPageSize().getValue();
        wait = true;
        specialOrder.getPageNumber().observe(getViewLifecycleOwner(), page ->{
            request.PageNumber = page;
            specialOrder.getPlantPaging(request).observe(getViewLifecycleOwner(), plantDtos -> {
                if (plantDtos.isEmpty()){
                    isLastPage = true;
                    return;
                }else if (plantDtos.size() < specialOrder.getPageSize().getValue()){
                    isLastPage = true;
                }
                setRecyclerView(plantDtos);
            });
        });
    }

    private void setRecyclerView(List<PlantDto> plantDtos) {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.rcvSpecialOffers.setLayoutManager(linearLayoutManager);
        } else {
            binding.rcvSpecialOffers.setLayoutManager(new GridLayoutManager(getContext(), 4));
        }
        adapter.addItems(plantDtos);
        if (!isLastPage) wait = false;
    }
    private void getListCategory() {
        categoryViewModel.getAll().observe(getViewLifecycleOwner(), categoryDtos -> {
            setRecyclerViewCategory(categoryDtos);
        });
    }

    private void setRecyclerViewCategory(List<CategoryDto> categoryDtos) {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.rcvMostPopularCate.setLayoutManager(layoutCategory);
        } else {
            binding.rcvMostPopularCate.setLayoutManager(new GridLayoutManager(getContext(), 4));
        }
        categoryAdapter.addItems(categoryDtos);
    }

    //3
    private void initMostPopular(){
        mostPopular = new ViewModelProvider(this).get(PlantViewModel.class);
        adapter1 = new PlantAdapter(new ArrayList<PlantDto>(), new PlantAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
            }

            @Override
            public void onClickLove(int position, View v) {
                if (adapter1.plants.get(position).isLove){
                    mostPopular.removeFromWishList(adapter1.plants.get(position).id);
                    mostPopular.getRemoveFromWishListResult().observe(getViewLifecycleOwner(),result ->{
                        if (result){
                            adapter1.plants.get(position).isLove = false;
                        }
                    });
                }else {
                    mostPopular.addToWishList(adapter1.plants.get(position).id);
                    mostPopular.getAddToWishListResult().observe(getViewLifecycleOwner(),result ->{
                        if (result){
                            adapter1.plants.get(position).isLove = true;
                        }
                    });
                }
            }
        });
        binding.rcvMostPopularPlant.setAdapter(adapter1);
        binding.rcvMostPopularPlant.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.right = 24;
            }
        });
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.rcvMostPopularPlant.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @Override
            public void loadMoreItem() {
                isLoading1 =true;
                isFirst = false;
                if (!wait1) {
                    mostPopular.setPageNumber(new MutableLiveData<>(mostPopular.getPageNumber().getValue() + 1));
                    getListOfMostPopular(idCate);
                }
                isLoading1 = false;
            }
            @Override
            public boolean isLoading() {
                return isLoading1;
            }
            @Override
            public boolean isLastPage() {
                return isLastPage1;
            }
        });
        getListOfMostPopular(idCate);
    }
    private void getListOfMostPopular(@Nullable UUID idCate) {
        PlantRequestParameters request = new PlantRequestParameters();
        request.categoryName = PlantRequestParameters.CategoryName.MostPopular;
        request.IdCategory = idCate;
        request.PageSize = mostPopular.getPageSize().getValue();
        wait1 = true;
        mostPopular.getPageNumber().observe(getViewLifecycleOwner(), page ->{
            request.PageNumber = page;
            mostPopular.getPlantPaging(request).observe(getViewLifecycleOwner(), plantDtos -> {
                if (plantDtos.isEmpty()){
                    isLastPage1 = true;
                    return;
                }else if (plantDtos.size() < mostPopular.getPageSize().getValue()){
                    isLastPage1 = true;
                }
                setRecyclerViewMostPopular(plantDtos, isFirst);
            });
        });
        if (isFirst){
            mostPopular.getIsNullCall().observe(getViewLifecycleOwner(), isNull -> {
                if (isNull){
                    adapter1.remove();
                }
            });
        }
    }

    private void setRecyclerViewMostPopular(List<PlantDto> plantDtos, boolean isFirst) {
        binding.rcvMostPopularPlant.setLayoutManager(gridLayoutManager);
        if (isFirst){
            adapter1.setItems(plantDtos);
        }else {
            adapter1.addItems(plantDtos);
        }
        if (!isLastPage1) wait1 = false;
    }

}