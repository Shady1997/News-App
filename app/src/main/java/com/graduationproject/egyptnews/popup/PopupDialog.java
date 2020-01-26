package com.graduationproject.egyptnews.popup;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.graduationproject.egyptnews.R;
import com.graduationproject.egyptnews.views.activities.EntryActivity;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class PopupDialog extends AppCompatDialogFragment {
    private EditText etEmailReset;
    private DialogListener listener;
    //designate a style
    AwesomeValidation mAwesomeValidation = new AwesomeValidation(BASIC);

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        etEmailReset = view.findViewById(R.id.et_email_reset);
        //mAwesomeValidation.addValidation(getActivity(), R.id.et_email_reset, android.util.Patterns.EMAIL_ADDRESS, R.string.errormsg);

        builder.setView(view)
                .setTitle(getString(R.string.resetpass))
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        };
                    }
                })
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        String emailReset=etEmailReset.getText().toString().trim();
                        if(!emailReset.equals(""))
                        {
                                listener.applyEmailReset(emailReset);
                        }
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
        listener=(DialogListener) context;}
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()
            +" must implement the interface");
        }
    }

    public interface DialogListener {
            void applyEmailReset (String email);
    }
}
