package com.example.fashionboomer.bottom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.fashionboomer.ClothActivity;
import com.example.fashionboomer.MainActivity;
import com.example.fashionboomer.R;
import com.example.fashionboomer.adapter.HomeAdapter;
import com.example.fashionboomer.adapter.MenuAdapter;
import com.example.fashionboomer.dto.CategoryBean;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentHome extends Fragment {
    private MainActivity mainActivity;
    private Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        CircleImageView circleHat = view.findViewById(R.id.circleHat);
        CircleImageView circleTop = view.findViewById(R.id.circleTop);
        CircleImageView circlePants = view.findViewById(R.id.circlePants);
        CircleImageView circleOuter = view.findViewById(R.id.circleOuter);
        CircleImageView circleOnepiece = view.findViewById(R.id.circleOnepiece);
        CircleImageView circleSkirt = view.findViewById(R.id.circleSkirt);
        CircleImageView circleShoes = view.findViewById(R.id.circleShoes);


        circleHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryString("hat");
            }
        });

        circleTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryString("top");
            }
        });

        circlePants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryString("pants");
            }
        });

        circleOuter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryString("outer");
            }
        });

        circleOnepiece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryString("onepiece");
            }
        });

        circleSkirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryString("skirt");
            }
        });

        circleShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryString("shoes");
            }
        });



        return view;
    }

    private void categoryString(String mainMenu) {
        BottomNavigationView bottom_menu = mainActivity.findViewById(R.id.menu_bottom_navigation);
        bottom_menu.setSelectedItemId(R.id.category);
        Bundle bundle = new Bundle();
        bundle.putString("homeFrag", mainMenu);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        FragmentCategory fragmentCategory = new FragmentCategory();
        fragmentCategory.setArguments(bundle);
        mainActivity.pauseForHome();
        fragmentTransaction.replace(R.id.menu_frame_layout, fragmentCategory);
        fragmentTransaction.commit();
    }
}