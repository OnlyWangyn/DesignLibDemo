package com.wyn.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.wyn.adapter.RecyclerViewAdapter;
import com.wyn.widget.NestScrollingCoordinatorLayout;

public class MainActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener,
        NavigationView.OnNavigationItemSelectedListener{



    private Context mContext;
    private Toolbar mToolbar;
    private SharedPreferences.OnSharedPreferenceChangeListener mOnSharePreferenceListener;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private final String Selected_Item_Id = "SelectedItemId";
    private int mSelectedItemId = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        //Finally, let's add the Toolbar
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(mContext.getResources().getString(R.string.toolbar_title));
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_left_drawer));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }

            }
        });
        mToolbar.inflateMenu(R.menu.menu_main);
        mToolbar.setOnMenuItemClickListener(this);

        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,
               R.string.drawer_open,R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        if(savedInstanceState != null){

            mSelectedItemId = savedInstanceState.getInt(Selected_Item_Id);
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if(mSelectedItemId != -1){
            navigationView.getMenu().findItem(mSelectedItemId).setChecked(true);
        }
        navigationView.setNavigationItemSelectedListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycleView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mContext);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);


        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NestScrollingCoordinatorLayout layout = (NestScrollingCoordinatorLayout)
                        findViewById(R.id.coordinatorLayout);
                Snackbar.make(layout, "Dismiss",
                        Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();
            }
        });
        mOnSharePreferenceListener =
                new SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                        if(!key.equals("dark_theme")) return;
                        finish();
                        Intent intent = getIntent();
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                };
//        PreferenceManager.getDefaultSharedPreferences(mContext).
//                registerOnSharedPreferenceChangeListener(mOnSharePreferenceListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        if(item.isChecked()){
            item.setChecked(false);
        }else{
            item.setChecked(true);
        }
        switch(id){
            case R.id.menu_set_mode:
                //not good enough,I just wanna try OnSharedPreferenceChangeListener
//                if(item.isChecked()){
//                    setTheme(R.style.AppTheme_Dark);
//                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
//                    editor.putBoolean("dark_theme",true);
//                    editor.putBoolean("light_theme",false);
//                    editor.commit();
//
//                }
//                else{
//                    setTheme(R.style.AppTheme_Base);
//                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
//                    editor.putBoolean("light_theme",true);
//                    editor.putBoolean("dark_theme", false);
//                    editor.commit();
//                }
                break;
            case R.id.menu_red:
                mToolbar.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    if(window != null){
                        window.setStatusBarColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
                    }
                }
                break;
            case R.id.menu_yellow:
                mToolbar.setBackgroundColor(mContext.getResources().getColor(R.color.primary_color_yellow));
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    if(window != null){
                        window.setStatusBarColor(mContext.getResources().getColor(R.color.primary_color_dark_yellow));
                    }
                }
                break;
        }
       return false;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mSelectedItemId = menuItem.getItemId();
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(Selected_Item_Id, mSelectedItemId);
        super.onSaveInstanceState(outState);
    }
}
