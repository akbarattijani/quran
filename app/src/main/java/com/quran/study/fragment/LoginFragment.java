package com.quran.study.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quran.study.R;
import com.quran.study.activity.DashboardActivity;
import com.quran.study.activity.BeginActivity;

/**
 * @author AKBAR <akbar.attijani@gmail.com>
 */

public class LoginFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private LinearLayout llLogin;

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
        View v = inflater.inflate(R.layout.login_fragment, container, false);

        llLogin = (LinearLayout) v.findViewById(R.id.llLogin);
        llLogin.setOnClickListener(this);

        etEmail = (TextInputEditText) v.findViewById(R.id.etEmail);
        etPassword = (TextInputEditText) v.findViewById(R.id.etPassword);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == llLogin) {
            if (etEmail.getText().toString().equals("")) {
                etEmail.setError("Email is empty");
            } else if (etPassword.getText().toString().equals("")) {
                etPassword.setError("Password is empty");
            } else if (!etEmail.getText().toString().equals("admin") && !etPassword.getText().toString().equals("admin")) {
                Toast.makeText(context, "Email and password is not correct", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(context, DashboardActivity.class);
                startActivity(intent);
            }
        }
    }
}
