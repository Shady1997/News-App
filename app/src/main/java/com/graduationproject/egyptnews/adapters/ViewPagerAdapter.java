package com.graduationproject.egyptnews.adapters;

import android.content.Context;
import android.content.res.Resources;

import com.graduationproject.egyptnews.R;
import com.graduationproject.egyptnews.views.fragments.entryFragments.SignInFragment;
import com.graduationproject.egyptnews.views.fragments.entryFragments.SignUpFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    String signIn;
    String signUp;

    public ViewPagerAdapter(@NonNull FragmentManager fm,String signIn,String signUp) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.signIn=signIn;
        this.signUp=signUp;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new SignInFragment();
            case 1:
                return new SignUpFragment();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return signIn;

            case 1:
                return signUp;
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
