package com.graduationproject.egyptnews.views.fragments.entryFragments;


import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.graduationproject.egyptnews.R;
import com.tomer.fadingtextview.FadingTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {

    private final int SPLASH_DISPLAY_LENGTH = 5000;
    //private SessionManager sessionManager;

    //declare textView
    FadingTextView tvSplashTitle;

    //declare mediaplayer
    MediaPlayer mediaPlayer;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_splash, container, false);

        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        view.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        //manage marquee
        tvSplashTitle=view.findViewById(R.id.tv_Title);
        //initialize mediaplayer
        mediaPlayer=MediaPlayer.create(getContext(),R.raw.startsound);
        mediaPlayer.start();


        //manage entry sound


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                //navigate to signin fragment
                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment);


            }
        }, SPLASH_DISPLAY_LENGTH);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mediaPlayer.release();
    }
}
