package com.graduationproject.egyptnews.views.fragments.mainNewsFragments;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.graduationproject.egyptnews.R;
import com.graduationproject.egyptnews.adapters.HeadNewsRecycViewAdapter;
import com.graduationproject.egyptnews.dagger.NewsApplications;
import com.graduationproject.egyptnews.models.headNews.Articles;
import com.graduationproject.egyptnews.models.headNews.News;
import com.graduationproject.egyptnews.models.headNews.NewsRoom;
import com.graduationproject.egyptnews.notification.NotificationGenerator;
import com.graduationproject.egyptnews.viewmodel.HomeViewModel;
import com.graduationproject.egyptnews.viewmodel.NewsVM;
import com.graduationproject.egyptnews.views.activities.EnvironmentalNewsActivity;
import com.graduationproject.egyptnews.views.activities.MainNewsActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static android.content.Context.ALARM_SERVICE;

public class HeadNewsFragment extends Fragment {

    //Create Injection for ViewModel in View Layer
    @Inject
    HomeViewModel homeViewModel;

    @Inject
    NewsVM newsVM;


    //for RecyclerView
    private RecyclerView headNewsRecyclerView;
    private HeadNewsRecycViewAdapter headNewsRecycViewAdapter;
    private List<Articles> articlesList = new ArrayList<>();
    private List<NewsRoom> newsList = new ArrayList<>();
    private TextView tvNoInternet;

    private FloatingActionButton fab;

    Snackbar snackbar;


    //Reference for live search
    EditText etSearch;

    // Rx stuff
    private SingleObserver<News> newsObserver;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((NewsApplications) getContext().getApplicationContext()).getComponent().inject(this);
    }


    public HeadNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_head_news, container, false);
        headNewsRecyclerView = view.findViewById(R.id.news_recyclerview);
        etSearch = view.findViewById(R.id.etsearch);
        tvNoInternet = view.findViewById(R.id.tv_no_internet);
        fab = view.findViewById(R.id.fab);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle(getString(R.string.headnews));
        showRvWhenHavingNotes();
        setuoFab();
        setUpRecyclerViewClickListener();
        setUpRecyclerView();
        searchEditText();
        setUpCompetitionObserver();
        doSubscription();
        roomSetup();
    }

    private void searchEditText() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void createNotification() {

        Calendar calendar = Calendar.getInstance();
        int hour=calendar.HOUR_OF_DAY;
        int minute=calendar.MINUTE;
        int milli=calendar.MILLISECOND;
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.HOUR, minute);
        calendar.set(Calendar.HOUR, milli);

        Intent intent = new Intent(getActivity().getApplicationContext(), NotificationGenerator.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*60*60*1000, pendingIntent);
    }

    private void roomSetup() {
        setUpObserver();
    }

    private void setUpObserver() {
        newsVM.getAllNews().observe(HeadNewsFragment.this, new Observer<List<NewsRoom>>() {
            @Override
            public void onChanged(List<NewsRoom> dbNewsEntities) {
                newsList.clear();
                newsList.addAll(dbNewsEntities);
            }
        });
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

    private void filter(String text) {

        ArrayList<Articles> filteredList = new ArrayList<>();

        for (Articles articles : articlesList) {
            if (articles.getNewsTitle().contains(text)) {
                filteredList.add(articles);
            }
        }
        headNewsRecycViewAdapter.filterlist(filteredList);
    }

    private void setUpRecyclerViewClickListener() {
        headNewsRecycViewAdapter = new HeadNewsRecycViewAdapter(articlesList, getContext(), new HeadNewsRecycViewAdapter.onItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = saveNewsDataToBundle(position);
                navigateToSecondFragment(view, bundle);
            }
        });
    }

    private Bundle saveNewsDataToBundle(int position) {
        Articles articles = articlesList.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("article", articles);
        return bundle;
    }

    private void navigateToSecondFragment(View view, Bundle bundle) {
        Navigation.findNavController(view).navigate(R.id.action_headNewsFragment_to_detailsNewsFragment, bundle);
    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);

        headNewsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        headNewsRecyclerView.addItemDecoration(new DividerItemDecoration(headNewsRecyclerView.getContext()
                , layoutManager.getOrientation()));

        headNewsRecyclerView.setLayoutManager(layoutManager);
        headNewsRecyclerView.setAdapter(headNewsRecycViewAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                articlesList.remove(position);
                headNewsRecycViewAdapter.notifyItemRemoved(position);
                headNewsRecycViewAdapter.notifyDataSetChanged();
            }
        }).attachToRecyclerView(headNewsRecyclerView);
    }

    private void showRvWhenHavingNotes() {

        if (!isNetworkAvailable()) {
            tvNoInternet.setVisibility(View.VISIBLE);
            headNewsRecyclerView.setVisibility(View.GONE);
            etSearch.setVisibility(View.GONE);
            snackbar = snackbar.make(getParentFragment().getView(), "There is no Internet", snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("CLOSE", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    snackbar.dismiss();
                }
            })
//                .setBackgroundColor(Color.parseColor("#CC000000")) // todo update your color
//                        .setTextColor(Color.WHITE)
                    //.setDuration(Duration.ofMillis(500))
                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                    .show();

        } else {
            etSearch.setVisibility(View.VISIBLE);
            headNewsRecyclerView.setVisibility(View.VISIBLE);
            tvNoInternet.setVisibility(View.GONE);

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void setUpCompetitionObserver() {

        newsObserver = new SingleObserver<News>() {

            @Override
            public void onSubscribe(Disposable disposable) {
                compositeDisposable.add(disposable);
            }

            @Override
            public void onSuccess(News news) {

                createNotification();
                getNewsList(news);
            }

            @Override
            public void onError(Throwable e) {
                //check network
                if (!isNetworkAvailable()) {
                    snackbar = snackbar.make(getParentFragment().getView(), getString(R.string.no_internet), snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction(getString(R.string.close), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            snackbar.dismiss();
                        }
                    })
                            .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                            .show();
                } else {
                    snackbar = snackbar.make(getParentFragment().getView(), getString(R.string.errormsg), snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction(getString(R.string.close), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            snackbar.dismiss();
                        }
                    })
                            .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                            .show();
                }
                //Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                Log.d("error", e.toString());
            }
        };

    }

    private void addnews() {
        newsVM.deleteAllNews();
        for (Articles arrayList : articlesList) {
            NewsRoom newsEntity = new NewsRoom(arrayList.getNewsAuthor(), arrayList.getNewsTitle(), arrayList.getNewsDescription(), arrayList.getNewsURL(), arrayList.getNewsImage(), arrayList.getPublishDate(), arrayList.getNewsContent());
            //newsList.add(newsEntity);
            newsVM.insertNews(newsEntity);
        }

    }


    private void getNewsList(News news) {
        articlesList.clear();
        articlesList.addAll(news.getArticlesList());
        addnews();
        headNewsRecycViewAdapter.notifyDataSetChanged();
    }


    private void doSubscription() {

        String competitionId = "top-headlines";
        homeViewModel.getNews(competitionId).subscribe(newsObserver);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

}
