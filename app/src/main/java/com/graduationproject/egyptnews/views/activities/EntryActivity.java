package com.graduationproject.egyptnews.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.graduationproject.egyptnews.R;
import com.graduationproject.egyptnews.popup.PopupDialog;
import com.graduationproject.egyptnews.sessionmanager.SessionManager;

public class EntryActivity extends AppCompatActivity implements PopupDialog.DialogListener {

    //handle session manager in head news fragment
    SessionManager sessionManager;

    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);

        //declare firebase instance
        mAuth = FirebaseAuth.getInstance();
        firebaseUser=mAuth.getCurrentUser();

        //progress dialog
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    @Override
    public void onStart() {
        super.onStart();

        //check session manager to quickly login
        if(sessionManager.checkUserSession()){
            startActivity(new Intent(this, MainNewsActivity.class));
        }
    }

    @Override
    public void applyEmailReset(String email) {
        if(firebaseUser == null )
        {
            Toast.makeText(this, getString(R.string.emailnotexist), Toast.LENGTH_SHORT).show();
        }
        else if(!firebaseUser.isEmailVerified())
        {
            Toast.makeText(this, getString(R.string.emailnotverified), Toast.LENGTH_SHORT).show();
        }
        else{
        dialog.show();
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            dialog.dismiss();
                            Toast.makeText(EntryActivity.this, getString(R.string.checkemail), Toast.LENGTH_LONG).show();
                        } else {
                            dialog.dismiss();
                            Toast.makeText(EntryActivity.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });}
    }

}
