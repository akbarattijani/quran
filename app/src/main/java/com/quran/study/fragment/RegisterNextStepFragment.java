package com.quran.study.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.quran.study.R;
import com.quran.study.activity.BeginActivity;

/**
 * @author AKBAR <akbar.attijani@gmail.com>
 */

public class RegisterNextStepFragment extends Fragment implements View.OnClickListener {
    private Context context;

    private Spinner spDay;
    private Spinner spMonth;
    private TextInputEditText etYear;

    private LinearLayout llMale;
    private LinearLayout llFemale;
    private TextView tvMale;
    private TextView tvFemale;

    private Spinner spArea;
    private TextInputEditText etPhoneNumber;

    private LinearLayout llCreate;

    private final String[] days = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    private final String[] month = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
    private final String[] area = {"081", "021", "0251", "082", "085"};

    private final int RC_MALE = 0;
    private final int RC_FEMALE = 1;
    private int REQUEST_CODE_GENDER = RC_MALE;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register_fragment_step2, container, false);

        spDay = (Spinner) v.findViewById(R.id.spDay);
        spMonth = (Spinner) v.findViewById(R.id.spMonth);
        etYear = (TextInputEditText) v.findViewById(R.id.etYear);

        llMale = (LinearLayout) v.findViewById(R.id.llMale);
        llFemale = (LinearLayout) v.findViewById(R.id.llFemale);
        tvMale = (TextView) v.findViewById(R.id.tvMale);
        tvFemale = (TextView) v.findViewById(R.id.tvFemale);

        spArea = (Spinner) v.findViewById(R.id.spArea);
        etPhoneNumber = (TextInputEditText) v.findViewById(R.id.etPhoneNumber);

        ArrayAdapter<String> adapterDays = new ArrayAdapter<String>(context, R.layout.spinner_item, days);
        ArrayAdapter<String> adapterMonth = new ArrayAdapter<String>(context, R.layout.spinner_item, month);
        ArrayAdapter<String> adapterArea = new ArrayAdapter<String>(context, R.layout.spinner_item, area);

        llCreate = (LinearLayout) v.findViewById(R.id.llCreate);

        adapterDays.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spDay.setAdapter(adapterDays);
        spMonth.setAdapter(adapterMonth);
        spArea.setAdapter(adapterArea);

        llMale.setOnClickListener(this);
        llFemale.setOnClickListener(this);
        llCreate.setOnClickListener(this);

        //Inisialisasi Gender (Default Male)
        llMale.setBackground(context.getResources().getDrawable(R.drawable.border_white_not_padding_blue_left));
        llFemale.setBackground(context.getResources().getDrawable(R.drawable.border_white_not_padding));
        tvMale.setTextColor(context.getResources().getColor(R.color.white));
        tvFemale.setTextColor(context.getResources().getColor(R.color.merchantGray_2));

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == llCreate) {
            if (etYear.getText().toString().equals("")) {
                etYear.setError("Year is empty");
            } else if (etPhoneNumber.getText().toString().equals("")) {
                etPhoneNumber.setError("Phone number is empty");
            } else {
                ((BeginActivity) context).toFragment(BeginActivity.RC_LOGIN);
            }
        } else if (v == llMale) {
            REQUEST_CODE_GENDER = RC_MALE;
            llMale.setBackground(context.getResources().getDrawable(R.drawable.border_white_not_padding_blue_left));
            llFemale.setBackground(context.getResources().getDrawable(R.drawable.border_white_not_padding));
            tvMale.setTextColor(context.getResources().getColor(R.color.white));
            tvFemale.setTextColor(context.getResources().getColor(R.color.merchantGray_2));
        } else if (v == llFemale) {
            REQUEST_CODE_GENDER = RC_FEMALE;
            llMale.setBackground(context.getResources().getDrawable(R.drawable.border_white_not_padding));
            llFemale.setBackground(context.getResources().getDrawable(R.drawable.border_white_not_padding_blue_right));
            tvMale.setTextColor(context.getResources().getColor(R.color.merchantGray_2));
            tvFemale.setTextColor(context.getResources().getColor(R.color.white));
        }
    }
}
