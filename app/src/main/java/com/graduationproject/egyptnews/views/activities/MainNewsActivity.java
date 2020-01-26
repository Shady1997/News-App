package com.graduationproject.egyptnews.views.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.graduationproject.egyptnews.R;
import com.graduationproject.egyptnews.notification.NotificationGenerator;
import com.graduationproject.egyptnews.sessionmanager.SessionManager;

import java.util.Calendar;

public class MainNewsActivity extends AppCompatActivity {

    //declare side menu parameters
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    //declare sessionmanager object
    private SessionManager sessionManager;

    //navheader welcome textview
    private TextView nameTextView;


    //declare toggle for side menu
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_news);

        sessionManager = new SessionManager(this);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);

        //manage toggle for side menu
        manageToggle();

        //manage side menu
        manageSideMenu();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //firestore
        firestore();

    }

    private void firestore() {
        SessionManager sessionManager = new SessionManager(this);
        View view = navigationView.getHeaderView(0);
        nameTextView = view.findViewById(R.id.tv_user);
        nameTextView.setText("Welcome " + sessionManager.getUserName());
    }

    private void manageToggle() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(MainNewsActivity.this, MainNewsActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {

            case R.id.gnews:
                finish();
                startActivity(new Intent(this, MainNewsActivity.class));
                break;
            case R.id.enews:
                finish();
                startActivity(new Intent(this, EnvironmentalNewsActivity.class));
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void manageSideMenu() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        finish();
                        startActivity(new Intent(MainNewsActivity.this, MainNewsActivity.class));
                        break;
                    case R.id.action_enews:
                        finish();
                        startActivity(new Intent(MainNewsActivity.this, EnvironmentalNewsActivity.class));
                        break;
                    case R.id.action_back:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.action_signout:
                        sessionManager.clearSession();
                        startActivity(new Intent(MainNewsActivity.this, EntryActivity.class));
                        break;
                    case R.id.action_exit:
                        ProgressDialog.Builder dialog = new ProgressDialog.Builder(MainNewsActivity.this);
                        dialog.setMessage(getString(R.string.exitApp));
                        dialog.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        dialog.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                //finish();
                            }
                        });
                        dialog.setCancelable(false);
                        dialog.show();
                        break;
                }
                return true;
            }
        });
    }

}
