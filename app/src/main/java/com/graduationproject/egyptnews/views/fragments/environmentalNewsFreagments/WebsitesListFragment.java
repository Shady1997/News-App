package com.graduationproject.egyptnews.views.fragments.environmentalNewsFreagments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.graduationproject.egyptnews.R;
import com.graduationproject.egyptnews.adapters.WebsitesListAdapter;
import com.graduationproject.egyptnews.models.details.EnvironmentalNewsModel;
import com.graduationproject.egyptnews.views.activities.EnvironmentalNewsActivity;
import com.graduationproject.egyptnews.views.activities.MainNewsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebsitesListFragment extends Fragment {


    //for RecyclerView
    private RecyclerView websitesListRecyclerView;
    private WebsitesListAdapter websitesListAdapter;
    private List websitesTitleList = new ArrayList<>();
    private List websitesImageList = new ArrayList<>();
    private List websitesUrlList = new ArrayList<>();
    private FloatingActionButton fab;

    public WebsitesListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_websites_list, container, false);
        websitesListRecyclerView = view.findViewById(R.id.websiyeList_recyclerview);
        fab = view.findViewById(R.id.fab);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle(getString(R.string.chooseoflist));

        //add data to our lists
        addDataToOurLists();

        setuoFab();

        //pass lists to Environmental News Model
        passDataListsToEnvironmentalModel();

        setUpRecyclerViewClickListener();
        setUpRecyclerView();

    }

    private void passDataListsToEnvironmentalModel() {
        EnvironmentalNewsModel.setWebsiteTitleList(websitesTitleList);
        EnvironmentalNewsModel.setWebsiteImageList(websitesImageList);
        EnvironmentalNewsModel.setWebsiteUrleList(websitesUrlList);
    }

    private void addDataToOurLists() {
        websitesTitleList.add(getString(R.string.youm7));
        websitesImageList.add(R.drawable.youm7);
        websitesUrlList.add("https://www.youm7.com/Tags/Index?id=16248&tag=%d9%88%d8%b2%d8%a7%d8%b1%d8%a9-%d8%a7%d9%84%d8%a8%d9%8a%d8%a6%d8%a9");

        websitesTitleList.add(getString(R.string.dubaiMunipality));
        websitesImageList.add(R.drawable.dubaimunicipalitylogo);
        websitesUrlList.add("https://www.env-news.com/");

        websitesTitleList.add(getString(R.string.skynews));
        websitesImageList.add(R.drawable.skynews);
        websitesUrlList.add("https://www.skynewsarabia.com/topic/774402-%D8%A8%D9%8A%D9%8A%D9%94%D8%A9");

        websitesTitleList.add(getString(R.string.almasryalyoum));
        websitesImageList.add(R.drawable.elmasryoum);
        websitesUrlList.add("https://www.almasryalyoum.com/news/tag/129337");

        websitesTitleList.add(getString(R.string.enviMinistry));
        websitesImageList.add(R.drawable.environmini);
        websitesUrlList.add("http://www.eeaa.gov.eg/ar-eg/%D9%85%D9%88%D8%B6%D9%88%D8%B9%D8%A7%D8%AA%D8%A8%D9%8A%D8%A6%D9%8A%D8%A9.aspx");

        websitesTitleList.add(getString(R.string.googleNews));
        websitesImageList.add(R.drawable.googlenews);
        websitesUrlList.add("https://news.google.com/search?q=%D8%A8%D9%8A%D8%A6%D8%A9&hl=ar&gl=EG&ceid=EG%3Aar");

        websitesTitleList.add(getString(R.string.nabd));
        websitesImageList.add(R.drawable.nabd);
        websitesUrlList.add("https://nabd.com/");

        websitesTitleList.add(getString(R.string.ahram));
        websitesImageList.add(R.drawable.ahram);
        websitesUrlList.add("http://gate.ahram.org.eg/Search/%D8%A7%D9%84%D8%A8%D9%8A%D8%A6%D8%A9.aspx");

        websitesTitleList.add(getString(R.string.wataninet));
        websitesImageList.add(R.drawable.wataninet);
        websitesUrlList.add("https://www.wataninet.com/category/%D8%A3%D8%AE%D8%A8%D8%A7%D8%B1-%D9%88%D8%AA%D9%82%D8%A7%D8%B1%D9%8A%D8%B1/%D8%A8%D9%8A%D8%A6%D8%A9/");

        websitesTitleList.add(getString(R.string.dostor));
        websitesImageList.add(R.drawable.dostor);
        websitesUrlList.add("http://www.dostor.org/list.aspx?ifr=1&w=%u0627%u0644%u0628%u064A%u0626%u0629&exp=1438848");

        websitesTitleList.add(getString(R.string.masrawy));
        websitesImageList.add(R.drawable.masrawy);
        websitesUrlList.add("https://www.masrawy.com/news/tag/6563/%D9%88%D8%B2%D8%A7%D8%B1%D8%A9-%D8%A7%D9%84%D8%A8%D9%8A%D8%A6%D8%A9");

        websitesTitleList.add(getString(R.string.ourenviroment));
        websitesImageList.add(R.drawable.ourenviroment);
        websitesUrlList.add("https://www.ourenviroment.org/");


        websitesTitleList.add(getString(R.string.elbalad));
        websitesImageList.add(R.drawable.elbalad);
        websitesUrlList.add("https://www.elbalad.news/list.aspx?kwn=%D8%A7%D8%AE%D8%A8%D8%A7%D8%B1+%D9%88%D8%B2%D8%A7%D8%B1%D8%A9+%D8%A7%D9%84%D8%A8%D9%8A%D8%A6%D8%A9&kw=15449");

        websitesTitleList.add(getString(R.string.shorouknews));
        websitesImageList.add(R.drawable.elshrok);
        websitesUrlList.add("https://www.shorouknews.com/search/default.aspx?q=%d8%a8%d9%8a%d8%a6%d8%a9&cx=005947815717218703378%3a8-h7rg-nci8&cof=FORID%3a9&hl=ar");


    }

    private void setUpRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);

        websitesListRecyclerView.setItemAnimator(new DefaultItemAnimator());

        websitesListRecyclerView.addItemDecoration(new DividerItemDecoration(websitesListRecyclerView.getContext()
                , layoutManager.getOrientation()));

        websitesListRecyclerView.setLayoutManager(layoutManager);
        websitesListRecyclerView.setAdapter(websitesListAdapter);
    }

    private void setUpRecyclerViewClickListener() {
        websitesListAdapter = new WebsitesListAdapter(getContext(), new WebsitesListAdapter.onItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = saveNewsDataToBundle(position);
                navigateToSecondFragment(view, bundle);
            }
        });
    }

    private Bundle saveNewsDataToBundle(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("webUrl", EnvironmentalNewsModel.getWebsiteUrleList().get(position).toString());
        return bundle;
    }

    private void navigateToSecondFragment(View view, Bundle bundle) {
        Navigation.findNavController(view).navigate(R.id.action_websitesListFragment_to_environmentalNewsFragment, bundle);
    }

    private void setuoFab() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getContext(), view);
                popupMenu.getMenu().add(1, R.id.gnews, 1, getString(R.string.gNews));
                popupMenu.getMenu().add(1, R.id.enews, 2, getString(R.string.eNews));
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
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
