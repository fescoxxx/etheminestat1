package com.rijsoft.ethermine.etherminestats;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.rijsoft.ethermine.etherminestats.model.history.History;
import com.rijsoft.ethermine.etherminestats.remote.RetrofitClient;
import com.rijsoft.ethermine.etherminestats.service.ApiService;
import com.rijsoft.ethermine.etherminestats.ui.OverviewFragment;
import com.rijsoft.ethermine.etherminestats.ui.PaymentsFragment;
import com.rijsoft.ethermine.etherminestats.ui.SettingsFragment;
import com.rijsoft.ethermine.etherminestats.ui.WorkersFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements OverviewFragment.OnFragmentInteractionListener,
                    WorkersFragment.OnFragmentInteractionListener,
                    SettingsFragment.OnFragmentInteractionListener,
                    PaymentsFragment.OnFragmentInteractionListener
{


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
                    if(!currentSelect.equals(CurrentSelect.OVERVEW))
                    loadFragment(OverviewFragment.newInstance("1","2"));
                    currentSelect = CurrentSelect.OVERVEW;
                    return true;
                case R.id.navigation_workers:
                    if(!currentSelect.equals(CurrentSelect.WORKERS))
                    loadFragment(WorkersFragment.newInstance("1","2"));
                    currentSelect = CurrentSelect.WORKERS;
                    return true;
                case R.id.navigation_payments:
                    if(!currentSelect.equals(CurrentSelect.PAYMENTS))
                    loadFragment(PaymentsFragment.newInstance("1","2"));
                    currentSelect = CurrentSelect.PAYMENTS;
                    return true;
                case R.id.navigation_settings:
                    if(!currentSelect.equals(CurrentSelect.SETTINGS))
                    loadFragment(SettingsFragment.newInstance("1","2"));
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
        currentSelect = CurrentSelect.OVERVEW;
        mContext = this;
        preferences = new Preferences(this);
        //preferences.setMiner("d83E0492108e809872178a325Eb784e1355780a3");
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if(preferences.getMiner().equals("")) {
          //  loadFragment(SettingsFragment.newInstance("1","2"));
            navigation.setSelectedItemId(R.id.navigation_settings);
            currentSelect = CurrentSelect.SETTINGS;
        } else {
          loadFragment(OverviewFragment.newInstance("1","2"));
            currentSelect = CurrentSelect.OVERVEW;
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
