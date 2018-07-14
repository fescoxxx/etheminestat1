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

    private TextView mTextMessage;
    private Context mContext;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_overview:
                    loadFragment(OverviewFragment.newInstance("1","2"));
                    return true;
                case R.id.navigation_workers:
                    loadFragment(WorkersFragment.newInstance("1","2"));
                    return true;
                case R.id.navigation_payments:
                    loadFragment(PaymentsFragment.newInstance("1","2"));
                    return true;
                case R.id.navigation_settings:
                    loadFragment(SettingsFragment.newInstance("1","2"));
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
        mContext = this;
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(OverviewFragment.newInstance("1","2"));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
