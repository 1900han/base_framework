package com.example.han.framwork.core;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.example.han.framwork.HomeActivity;

/**
 * Created by han on 2016/7/5.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if (CustomApplication.mAppStatus == -1) {
            protectApp();
        } else {
            setupData();
        }
    }

    protected void protectApp() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("action", "force_kill");
        startActivity(intent    );
    }

    protected void setupData() {

    }
}
