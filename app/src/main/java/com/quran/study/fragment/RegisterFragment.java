package com.quran.study.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quran.study.R;
import com.quran.study.activity.StartupActivity;

/**
 * @author AKBAR <akbar.attijani@gmail.com>
 */

public class RegisterFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private LinearLayout component;
    private RelativeLayout footer;
    private TextInputEditText etUsername;
    private TextInputEditText etPassword;
    private TextInputEditText etEmail;
    private TextInputEditText etName;
    private LinearLayout llCreate;
    private TextView tvForget;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register_fragment, container, false);

        component = (LinearLayout) v.findViewById(R.id.component);
        footer = (RelativeLayout) v.findViewById(R.id.footer);

        etUsername = (TextInputEditText) v.findViewById(R.id.etUsername);
        etEmail = (TextInputEditText) v.findViewById(R.id.etEmail);
        etName = (TextInputEditText) v.findViewById(R.id.etName);
        etPassword = (TextInputEditText) v.findViewById(R.id.etPassword);
        llCreate = (LinearLayout) v.findViewById(R.id.llCreate);
        tvForget = (TextView) v.findViewById(R.id.tvForget);

        llCreate.setOnClickListener(this);
        footer.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == llCreate) {
            String username = etUsername.getText().toString();
            String email = etEmail.getText().toString();
            String name = etName.getText().toString();
            String password = etPassword.getText().toString();

            if (username.equals("")) {
                etUsername.setError("Username is empty");
            } else if (email.equals("")) {
                etEmail.setError("Email is empty");
            } else if (name.equals("")) {
                etName.setError("Name is empty");
            } else if (password.equals("")) {
                etPassword.setError("Password is empty");
            } else {
                ((StartupActivity) context).toFragment(StartupActivity.RC_LOGIN);
            }
        } else if (v == footer) {
            ((StartupActivity) context).toFragment(StartupActivity.RC_LOGIN);
        }
    }
}
