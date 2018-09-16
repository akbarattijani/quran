package com.quran.study.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.quran.study.R;
import com.quran.study.fragment.LoginFragment;
import com.quran.study.fragment.RegisterFragment;
import com.quran.study.fragment.RegisterNextStepFragment;

/**
 * @author AKBAR <akbar.attijani@gmail.com>
 */

public class BeginActivity extends BaseActivity {
    private LoginFragment loginFragment = new LoginFragment();
    private RegisterFragment registerFragment = new RegisterFragment();
    private RegisterNextStepFragment registerNextStepFragment = new RegisterNextStepFragment();

    public static final int RC_LOGIN = 0;
    public static final int RC_REGISTER = 1;
    public static final int RC_NEXT_STEP_REGISTER = 2;

    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = (TextView) findViewById(R.id.tvTitle);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int requestCode = bundle.getInt("requestCode", RC_LOGIN);

            if (requestCode == RC_LOGIN) {
                tvTitle.setText("Login");
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, loginFragment)
                        .add(R.id.container, registerFragment)
                        .add(R.id.container, registerNextStepFragment)
                        .show(loginFragment)
                        .hide(registerFragment)
                        .hide(registerNextStepFragment)
                        .commit();

            } else if (requestCode == RC_REGISTER) {
                tvTitle.setText("Signup Account");
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, loginFragment)
                        .add(R.id.container, registerFragment)
                        .add(R.id.container, registerNextStepFragment)
                        .show(registerFragment)
                        .hide(loginFragment)
                        .hide(registerNextStepFragment)
                        .commit();
            }
        }

    }

    public void toFragment(int requestCode) {
        if (requestCode == RC_LOGIN) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_top)
                    .show(loginFragment)
                    .hide(registerFragment)
                    .hide(registerNextStepFragment)
                    .commit();
        } else if (requestCode == RC_REGISTER) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_top)
                    .show(registerFragment)
                    .hide(loginFragment)
                    .hide(registerNextStepFragment)
                    .commit();
        } else if (requestCode == RC_NEXT_STEP_REGISTER) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_top)
                    .show(registerNextStepFragment)
                    .hide(loginFragment)
                    .hide(registerFragment)
                    .commit();
        }
    }
}
