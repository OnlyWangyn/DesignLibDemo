package com.wyn.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by nancy on 15-8-31.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //not perfect,just try something
//        if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("dark_theme",false)){
//            setTheme(R.style.AppTheme_Dark);
//        }else {
//            setTheme(R.style.AppTheme_Base);
//        }
    }
}
