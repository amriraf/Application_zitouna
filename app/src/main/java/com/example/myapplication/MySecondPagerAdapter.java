package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MySecondPagerAdapter extends FragmentPagerAdapter {
    public MySecondPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FirstFragment_1();
            case 1:
                return new SecondFragment_1();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
