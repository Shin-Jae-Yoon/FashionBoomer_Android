package com.example.fashionboomer.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fashionboomer.viewPager.viewPagerPhoto01;
import com.example.fashionboomer.viewPager.viewPagerPhoto02;
import com.example.fashionboomer.viewPager.viewPagerPhoto03;
import com.example.fashionboomer.viewPager.viewPagerPhoto04;

// PagerAdapter

public class ViewPagerAdapter extends FragmentStateAdapter {

    public int mCount;

    public ViewPagerAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if (index == 0) {
            return new viewPagerPhoto01();
        } else if (index == 1) {
            return new viewPagerPhoto02();
        } else if (index == 2) {
            return new viewPagerPhoto03();
        } else {
            return new viewPagerPhoto04();
        }
    }

    @Override
    public int getItemCount() { return 2000; }

    public int getRealPosition(int position) { return position % mCount; }
}