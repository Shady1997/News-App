package com.graduationproject.egyptnews.views.fragments.mainNewsFragments;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.graduationproject.egyptnews.R;
import com.graduationproject.egyptnews.models.headNews.Articles;
import com.graduationproject.egyptnews.views.activities.EnvironmentalNewsActivity;
import com.graduationproject.egyptnews.views.activities.MainNewsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowOnWebFragment extends Fragment {

    //declare object to web view
    private WebView mWebView;

    //declare uel
    private String websiteUrl;
    Uri uri;

    private TextView tvNoInternetd;

    private FloatingActionButton fab;

    Snackbar snackbar;

    public ShowOnWebFragment() {
        // Required empty public constructor
    }

    private void showRvWhenHavingNotes() {

        if (isNetworkAvailable()) {
            mWebView.setVisibility(View.VISIBLE);
            tvNoInternetd.setVisibility(View.GONE);
            Bundle bundle = getArguments();
            if( bundle != null){
                String websiteUrl =  bundle.getString("url");
                uri = Uri.parse(websiteUrl);
            }

            WebSettings webSettings=mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            mWebView.loadUrl(uri.toString());
            mWebView.setWebViewClient(new WebViewClient());

        } else {
            tvNoInternetd.setVisibility(View.VISIBLE);
            mWebView.setVisibility(View.GONE);
            snackbar=snackbar.make(getParentFragment().getView(), getString(R.string.no_internet), snackbar.LENGTH_INDEFINITE);
            snackbar.setAction(getString(R.string.close), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    snackbar.dismiss();
                }
            })
                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                    .show();

        }
    }

    private void setuoFab() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getContext(), view);
                popupMenu.getMenu().add(1, R.id.gnews, 1, getString(R.string.gNews));
                popupMenu.getMenu().add(1,R.id.enews,2,getString(R.string.eNews));
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId())
                        {
                            case R.id.gnews:
                                getActivity().finish();
                                startActivity(new Intent(getContext(), MainNewsActivity.class));
                                return true;
                            case R.id.enews:
                                getActivity().finish();
                                startActivity(new Intent(getContext(), EnvironmentalNewsActivity.class));
                                return true;
                        }
                        return true;
                    }
                });
            }
        });
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_show_on_web, container, false);
        mWebView=view.findViewById(R.id.wv1);
        tvNoInternetd = view.findViewById(R.id.tv_no_internetd);
        fab = view.findViewById(R.id.fab);
        return view;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle(getString(R.string.detailsnews));
        showRvWhenHavingNotes();
        setuoFab();
    }

}
