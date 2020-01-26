package com.graduationproject.egyptnews.views.fragments.entryFragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.graduationproject.egyptnews.R;
import com.graduationproject.egyptnews.adapters.ViewPagerAdapter;


public class HomeFragment extends Fragment {

    //declare ViewPager and TabLayout
    View view;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    //declare ViewPagerAdapter
    private ViewPagerAdapter viewPagerAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_home, container, false);
        //initialize ViewPager and TabLayout
        mViewPager=view.findViewById(R.id.view_pager);
        mTabLayout=view.findViewById(R.id.tab_layout);

        String signIn=getString(R.string.sign_in);
        String singUp=getString(R.string.sign_up);
        viewPagerAdapter=new ViewPagerAdapter(getChildFragmentManager(),signIn,singUp);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



}
