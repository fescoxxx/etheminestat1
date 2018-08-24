package com.rijsoft.ethermine.etherminestats;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.text.Html;

import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rijsoft.ethermine.etherminestats.ui.OverviewFragment;
import com.rijsoft.ethermine.etherminestats.ui.PaymentsFragment;
import com.rijsoft.ethermine.etherminestats.ui.SettingsFragment;
import com.rijsoft.ethermine.etherminestats.ui.WorkersFragment;

import java.lang.reflect.Field;


public class MainActivity extends AppCompatActivity
        implements OverviewFragment.OnFragmentInteractionListener,
                    WorkersFragment.OnFragmentInteractionListener,
                    SettingsFragment.OnFragmentInteractionListener,
                    PaymentsFragment.OnFragmentInteractionListener {


    private Context mContext;
    private Preferences preferences;
    private CurrentSelect currentSelect;
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_overview:
                    if (!currentSelect.equals(CurrentSelect.OVERVEW))
                        loadFragment(OverviewFragment.newInstance("1", "2"));
                    currentSelect = CurrentSelect.OVERVEW;
                    return true;
                case R.id.navigation_workers:
                    if (!currentSelect.equals(CurrentSelect.WORKERS))
                        loadFragment(WorkersFragment.newInstance("1", "2"));
                    currentSelect = CurrentSelect.WORKERS;
                    return true;
                case R.id.navigation_payments:
                    if (!currentSelect.equals(CurrentSelect.PAYMENTS))
                        loadFragment(PaymentsFragment.newInstance("1", "2"));
                    currentSelect = CurrentSelect.PAYMENTS;
                    return true;
                case R.id.navigation_settings:
                    if (!currentSelect.equals(CurrentSelect.SETTINGS))
                        loadFragment(SettingsFragment.newInstance("1", "2"));
                    currentSelect = CurrentSelect.SETTINGS;
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, fragment);
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.app_name) + "</font>"));
        currentSelect = CurrentSelect.OVERVEW;
        mContext = this;
        preferences = new Preferences(this);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (preferences.getMiner().equals("")) {
            navigation.setSelectedItemId(R.id.navigation_settings);
            currentSelect = CurrentSelect.SETTINGS;
        } else {
            loadFragment(OverviewFragment.newInstance("1", "2"));
            navigation.setSelectedItemId(R.id.navigation_overview);
            currentSelect = CurrentSelect.OVERVEW;
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        if(!preferences.getMiner().equals("")) {
            navigation.setSelectedItemId(R.id.navigation_overview);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
enum CurrentSelect{
    OVERVEW,
    WORKERS,
    PAYMENTS,
    SETTINGS
}
