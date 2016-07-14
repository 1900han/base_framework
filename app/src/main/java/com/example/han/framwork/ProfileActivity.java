package com.example.han.framwork;

import android.os.Bundle;
import android.widget.TextView;

import com.example.han.framwork.core.BaseActivity;
import com.example.han.framwork.core.CustomApplication;

public class ProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupData() {
        setContentView(R.layout.activity_profile);
        TextView profile = (TextView) findViewById(R.id.txt_profile);
        profile.setText(CustomApplication.mTestNullPointers.toString());
    }

}
