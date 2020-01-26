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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.graduationproject.egyptnews.R;
import com.graduationproject.egyptnews.models.headNews.Articles;
import com.graduationproject.egyptnews.views.activities.EnvironmentalNewsActivity;
import com.graduationproject.egyptnews.views.activities.MainNewsActivity;

public class DetailsNewsFragment extends Fragment {

    private TextView tvNewsDescriptionDetails;
    private ImageView newsImageDetails;
    private TextView tvHeadNewsTitleDetails;
    private TextView tvWebsiteDetails;
    private TextView tvPublisherDetails;
    private TextView tvPublisherDateDetails;
    private FloatingActionButton fab;
    Snackbar snackbar;

    private TextView tvNoInternetdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_news, container, false);
        tvHeadNewsTitleDetails = view.findViewById(R.id.tvNewsTitleDetails);
        newsImageDetails = view.findViewById(R.id.imgNewsImage);
        tvNewsDescriptionDetails = view.findViewById(R.id.tvNewsDescriptionDetails);
        tvWebsiteDetails = view.findViewById(R.id.tvWebsiteDetails);
        tvPublisherDetails = view.findViewById(R.id.tvPublisherDetails);
        tvPublisherDateDetails = view.findViewById(R.id.tvPublishDateDetails);
        fab = view.findViewById(R.id.fab);
        tvNoInternetdd = view.findViewById(R.id.tv_no_internetdd);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle(getString(R.string.detailsnews));

        if (!isNetworkAvailable()) {
            tvNoInternetdd.setVisibility(View.VISIBLE);
            tvHeadNewsTitleDetails.setVisibility(View.GONE);
            newsImageDetails.setVisibility(View.GONE);
            tvNewsDescriptionDetails.setVisibility(View.GONE);
            tvWebsiteDetails.setVisibility(View.GONE);
            tvPublisherDetails.setVisibility(View.GONE);
            tvPublisherDateDetails.setVisibility(View.GONE);
            snackbar=snackbar.make(getParentFragment().getView(), getString(R.string.no_internet), snackbar.LENGTH_INDEFINITE);
            snackbar.setAction(getString(R.string.close), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    snackbar.dismiss();
                }
            })
                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                    .show();

        } else {
            tvNoInternetdd.setVisibility(View.GONE);
            tvHeadNewsTitleDetails.setVisibility(View.VISIBLE);
            newsImageDetails.setVisibility(View.VISIBLE);
            tvNewsDescriptionDetails.setVisibility(View.VISIBLE);
            tvWebsiteDetails.setVisibility(View.VISIBLE);
            tvPublisherDetails.setVisibility(View.VISIBLE);
            tvPublisherDateDetails.setVisibility(View.VISIBLE);
            getArticlesListFromHeadNewsFragment();
        }
        setuoFab();
    }

    private void getArticlesListFromHeadNewsFragment() {

        Bundle bundle = getArguments();
        if( bundle != null){
            Articles articles = (Articles) bundle.getSerializable("article");
            showTeamData(articles);
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void showTeamData(Articles articles) {
        tvHeadNewsTitleDetails.setText(articles.getNewsTitle());
        tvNewsDescriptionDetails.setText(articles.getNewsDescription());
        tvWebsiteDetails.setText(articles.getNewsURL());
        openNewsWebsite();
        tvPublisherDateDetails.setText(getResources().getString(R.string.publishat)+articles.getPublishDate());
        Glide.with(getActivity()).load(articles.getNewsImage()).into(newsImageDetails);
        tvPublisherDetails.setText(getResources().getString(R.string.publishby)+articles.getNewsSources().getSourceName());
    }

    private void openNewsWebsite() {
        final String articleWebsite = tvWebsiteDetails.getText().toString();
        tvWebsiteDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("url", articleWebsite);
                Navigation.findNavController(view).navigate(R.id.action_detailsNewsFragment_to_showOnWebFragment, bundle);

//                Uri uri = Uri.parse(articleWebsite);
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                getActivity().startActivity(intent);
            }
        });
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


}
