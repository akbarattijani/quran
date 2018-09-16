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
import com.quran.study.activity.StartupActivity;

/**
 * @author AKBAR <akbar.attijani@gmail.com>
 */

public class LoginFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private LinearLayout component;
    private RelativeLayout footer;
    private TextInputEditText etUsername;
    private TextInputEditText etPassword;
    private LinearLayout llLogin;
    private TextView tvForget;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_fragment, container, false);

        component = (LinearLayout) v.findViewById(R.id.component);
        footer = (RelativeLayout) v.findViewById(R.id.footer);

        etUsername = (TextInputEditText) v.findViewById(R.id.etUsername);
        etPassword = (TextInputEditText) v.findViewById(R.id.etPassword);
        llLogin = (LinearLayout) v.findViewById(R.id.llLogin);
        tvForget = (TextView) v.findViewById(R.id.tvForget);

        llLogin.setOnClickListener(this);
        footer.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == llLogin) {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (username.equals("")) {
                etUsername.setError("Username is empty");
            } else if (password.equals("")) {
                etPassword.setError("Password is empty");
            } else if (!username.equals("admin") && !password.equals("admin")) {
                Toast.makeText(context, "Username and Password is not correct", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(context, DashboardActivity.class);
                startActivity(intent);
            }
        } else if (v == footer) {
            ((StartupActivity) context).toFragment(StartupActivity.RC_REGISTER);
        }
    }
}
