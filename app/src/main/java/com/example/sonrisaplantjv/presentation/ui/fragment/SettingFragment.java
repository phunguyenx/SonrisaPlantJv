package com.example.sonrisaplantjv.presentation.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sonrisaplantjv.R;
import com.example.sonrisaplantjv.common.Constant;
import com.example.sonrisaplantjv.databinding.FragmentHomeBinding;
import com.example.sonrisaplantjv.databinding.FragmentSettingBinding;
import com.example.sonrisaplantjv.presentation.ui.activity.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {
    FragmentSettingBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
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

        try {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constant.SHARED_PREFERENCES, Context.MODE_PRIVATE);
            String accessToken = sharedPreferences.getString(Constant.SP_ACCESS_TOKEN   , null);
            String refreshToken = sharedPreferences.getString(Constant.SP_REFRESH_TOKEN, null);
            if (accessToken != null) {
                // Token đã tồn tại, người dùng đã đăng nhập trước đó
                // Bạn có thể chuyển họ đến màn hình chính hoặc bất kỳ màn hình nào bạn muốn
                // Ví dụ: Chuyển đến MainActivity
            } else {
                // Token chưa tồn tại, người dùng chưa đăng nhập trước đó
                // Hiển thị màn hình đăng nhập
                // Ví dụ: Chuyển đến LoginActivity
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish(); // Đóng activity hiện tại
            }
        }catch (Exception e){
            Log.e("Home", e.getMessage());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(getLayoutInflater(), container, false);
        // Inflate the layout for this fragment
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constant.SHARED_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(Constant.SP_ACCESS_TOKEN);
                editor.remove(Constant.SP_REFRESH_TOKEN);
                editor.apply();
            }
        });
        return binding.getRoot();

    }

}