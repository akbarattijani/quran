package com.quran.study.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quran.study.R;
import com.quran.study.activity.BeginActivity;

/**
 * @author AKBAR <akbar.attijani@gmail.com>
 */

public class RegisterFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private LinearLayout llNext;

    private TextInputEditText etName;
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register_fragment, container, false);
        llNext = (LinearLayout) v.findViewById(R.id.llNext);
        llNext.setOnClickListener(this);

        etName = (TextInputEditText) v.findViewById(R.id.etName);
        etEmail = (TextInputEditText) v.findViewById(R.id.etEmail);
        etPassword = (TextInputEditText) v.findViewById(R.id.etPassword);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == llNext) {
            if (etName.getText().toString().equals("")) {
                etName.setError("Name is empty");
            } else if (etEmail.getText().toString().equals("")) {
                etEmail.setError("Email is empty");
            } else if (etPassword.getText().toString().equals("")) {
                etPassword.setError("Password is empty");
            } else {
                ((BeginActivity) context).toFragment(BeginActivity.RC_NEXT_STEP_REGISTER);
            }
        }
    }
}
