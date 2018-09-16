package com.quran.study.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.quran.study.R;
import com.quran.study.fragment.LoginFragment;
import com.quran.study.fragment.RegisterFragment;

/**
 * @author AKBAR <akbar.attijani@gmail.com>
 */

public class StartupActivity extends AppCompatActivity {
    private LoginFragment loginFragment = new LoginFragment();
    private RegisterFragment registerFragment = new RegisterFragment();

    public static final int RC_LOGIN = 0;
    public static final int RC_REGISTER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, loginFragment)
                .add(R.id.container, registerFragment)
                .show(loginFragment)
                .hide(registerFragment)
                .commit();
    }

    public void toFragment(int requestCode) {
        if (requestCode == 0) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_top)
                    .show(loginFragment)
                    .hide(registerFragment)
                    .commit();
        } else if (requestCode == 1) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_top)
                    .show(registerFragment)
                    .hide(loginFragment)
                    .commit();
        }
    }
}
