package com.example.sonrisaplantjv.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.sonrisaplantjv.R;
import com.example.sonrisaplantjv.common.Constant;
import com.example.sonrisaplantjv.databinding.ActivityHomeBinding;
import com.example.sonrisaplantjv.databinding.ActivityLoginBinding;
import com.example.sonrisaplantjv.presentation.ui.fragment.CartFragment;
import com.example.sonrisaplantjv.presentation.ui.fragment.HomeFragment;
import com.example.sonrisaplantjv.presentation.ui.fragment.OrderFragment;
import com.example.sonrisaplantjv.presentation.ui.fragment.SettingFragment;
import com.example.sonrisaplantjv.presentation.viewmodels.HomeViewModel;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_home), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.bottomNavMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.home_bottom_nav){
                    loadFragment(new HomeFragment(), false);
                } else if (itemId == R.id.cart_bottom_nav){
                    loadFragment(new CartFragment(), true);
                }else if (itemId == R.id.order_bottom_nav){
                    loadFragment(new OrderFragment(), true);
                } else if (itemId == R.id.profile_bottom_nav) {
                    loadFragment(new SettingFragment(), true);
                }
                return true;
            }
        });
        loadFragment(new HomeFragment(), true);
    }
    private void loadFragment(Fragment fragment, boolean isAppInitialized){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isAppInitialized){
            fragmentTransaction.add(R.id.layout_fragment, fragment);
        }else {
            fragmentTransaction.replace(R.id.layout_fragment, fragment);
        }
        fragmentTransaction.commit();
    }
    //

}