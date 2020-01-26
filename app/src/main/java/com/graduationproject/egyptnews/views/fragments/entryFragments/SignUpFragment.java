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
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.graduationproject.egyptnews.R;
import com.graduationproject.egyptnews.sessionmanager.SessionManager;
import com.graduationproject.egyptnews.views.activities.MainNewsActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpFragment extends Fragment {

    //declare sign up objects
    private EditText etname;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignup;
//    private TextView login;
    private ProgressDialog dialog;
//    private CheckBox checkBoxr;
    private FirebaseAuth mAuth;
    Snackbar snackbar;


    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //declare firebase instance
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        etname = view.findViewById(R.id.et_namer);
        etEmail = view.findViewById(R.id.et_emailr);
        etPassword = view.findViewById(R.id.et_passwordr);
        btnSignup = view.findViewById(R.id.btn_signup);
        //login = view.findViewById(R.id.tv_login);
//        checkBoxr = view.findViewById(R.id.cbrememberMer);
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

        //move to Main news activity in successful sign up
        manageSuccessfulSignup();

    }

    private void manageSuccessfulSignup() {
        btnSignup.setOnClickListener(new View.OnClickListener() {
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

                    final String name = etname.getText().toString().trim();
                    final String email = etEmail.getText().toString().trim();
                    final String password = etPassword.getText().toString().trim();


                    if(etEmail.getText().toString().equals("") || etPassword.getText().toString().equals("") || etname.getText().toString().equals(""))
                    {
                        Toast.makeText(getContext(), getString(R.string.emptymsg), Toast.LENGTH_LONG).show();
                    }
                    else {
                        if (!isEmailValid(etEmail.getText().toString())) {
                            etEmail.setError(getString(R.string.invalidemail));
                        } else {
                            dialog.setMessage("Loading .......");
                            dialog.show();
                            signUpFirebase(name, email, password);
                        }
                    }

                }

            }
        });
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void signUpFirebase(final String name, final String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mAuth.getCurrentUser().sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                                //close progressdialog in successful sign up
                                                dialog.dismiss();

                                                Toast.makeText(getContext(), getString(R.string.registsucc), Toast.LENGTH_LONG).show();
                                                etEmail.setText("");
                                                etPassword.setText("");
                                                etname.setText("");
                                                //manage check box in signup fragment
//                                                if (checkBoxr.isChecked()) {
//                                                    // manageCheckboxInSigninFragment(userName, email, password);
//                                                    sessionManager.createUserSession(email, password, name);
//                                                } else {
//                                                    sessionManager.setUserName(name);
//                                                }
                                                //add name to a firestor object:
                                                // Access a Cloud Firestore instance from your Activity
                                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                                // Create a new user with a first and last name
                                                Map<String, Object> user = new HashMap<>();
                                                user.put("name", name);
                                                user.put("email", email);
                                                user.put("pass", password);

                                                // Add a new document with a generated ID
                                                db.collection("users")
                                                        .add(user)
                                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                            @Override
                                                            public void onSuccess(DocumentReference documentReference) {
                                                                Log.d("success", "DocumentSnapshot added with ID: " + documentReference.getId());
                                                            }
                                                        })
                                                        .addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                Log.w("failuer", "Error adding document", e);
                                                            }
                                                        });
                                                //start Main news activity
//                            Intent intent = new Intent(getContext(), MainNewsActivity.class);
//                            intent.putExtra("email", email);
//                            startActivity(intent);
//                            Toast.makeText(getContext(), "Welcome", Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                //close progressdialog in successful sign up
                                                dialog.dismiss();
                                                Toast.makeText(getContext(), task.getException().toString(), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        } else {
                            dialog.dismiss();
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
                    }

                });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
