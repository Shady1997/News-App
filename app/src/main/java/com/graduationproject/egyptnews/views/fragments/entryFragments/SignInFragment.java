package com.graduationproject.egyptnews.views.fragments.entryFragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.graduationproject.egyptnews.R;
import com.graduationproject.egyptnews.popup.PopupDialog;
import com.graduationproject.egyptnews.sessionmanager.SessionManager;
import com.graduationproject.egyptnews.views.activities.EntryActivity;
import com.graduationproject.egyptnews.views.activities.MainNewsActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignInFragment extends Fragment  {

    //declare objects
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignin;
    private CheckBox checkBox;
    private TextView tvRegister;

    private FirebaseAuth mAuth;
    private ProgressDialog dialog;
    private SessionManager sessionManager;

    String userName = null;

    Snackbar snackbar;

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //declare firebase instance
        mAuth = FirebaseAuth.getInstance();

        //declare sessionmanager instance
        sessionManager = new SessionManager(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        //initialize the objects
        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        btnSignin = view.findViewById(R.id.btn_signin);
        checkBox = view.findViewById(R.id.cbrememberMe);
        tvRegister = view.findViewById(R.id.tv_register);

        //progress dialog
        dialog = new ProgressDialog(getContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
        }

        //manage sign in button in sign in fragment
        manageSigninButtonInSigninFragment();

        //reset password
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        openPopup();
    }

    private void openPopup() {
                PopupDialog popupDialog = new PopupDialog();
                popupDialog.show(getActivity().getSupportFragmentManager(), "passwordReset");
         }


    private void manageSigninButtonInSigninFragment() {
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    final String email = etEmail.getText().toString().trim();
                    final String password = etPassword.getText().toString().trim();

                    if (etEmail.getText().toString().equals("") || etPassword.getText().toString().equals("")) {
                        Toast.makeText(getContext(), getString(R.string.emptymsg), Toast.LENGTH_LONG).show();
                    } else {
                        if (!isEmailValid(etEmail.getText().toString())) {
                            etEmail.setError(getString(R.string.invalidemail));
                        } else {
                            dialog.setMessage("Loading .......");
                            dialog.show();
                            signInFirebase(email, password);
                        }
                    }
                }
            }
        });

    }

    private void signInFirebase(final String email, final String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            if (mAuth.getCurrentUser().isEmailVerified()) {
                                //manageCheckbox in sign in fragment
                                if (checkBox.isChecked()) {
                                    // manageCheckboxInSigninFragment(userName, email, password);
                                    sessionManager.createUserSession(email, password, userName);
                                } else {
                                    sessionManager.setUserName(userName);
                                }
                                //hide progressDialog if successful
                                dialog.dismiss();

                                //start main news activity
                                Intent intent = new Intent(getContext(), MainNewsActivity.class);
                                intent.putExtra("email", email);
                                // Access a Cloud Firestore instance from your Activity
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                db.collection("users")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        if(document.getString("email").equals(email))
                                                        {
                                                            sessionManager.setUserName(document.getString("name"));
                                                        }
                                                        Log.d("documentId", document.getId() + " => " + document.getData());
                                                    }
                                                } else {
                                                    Log.w("Error", "Error getting documents.", task.getException());
                                                }
                                            }
                                        });
                                startActivity(intent);
                                Toast.makeText(getContext(), getString(R.string.welcome), Toast.LENGTH_SHORT).show();
                            } else {
                                dialog.dismiss();
                                Toast.makeText(getContext(), getString(R.string.verifyEmail), Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            dialog.dismiss();
                            Toast.makeText(getContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                            snackbar = snackbar.make(getParentFragment().getView(), getString(R.string.incorrectemailpass), snackbar.LENGTH_INDEFINITE);
                            snackbar.setAction(getString(R.string.close), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    snackbar.dismiss();
                                }
                            })
                                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                                    .show();
                        }
                    }
                });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
