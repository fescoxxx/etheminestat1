package com.rijsoft.ethermine.etherminestats.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rijsoft.ethermine.etherminestats.Preferences;
import com.rijsoft.ethermine.etherminestats.R;
import com.rijsoft.ethermine.etherminestats.contracts.SettingsContract;
import com.rijsoft.ethermine.etherminestats.intractors.GetSettingsIntractorImpl;
import com.rijsoft.ethermine.etherminestats.model.settings.Settings;
import com.rijsoft.ethermine.etherminestats.presenters.SettingsPresenterImpl;

import java.util.Locale;
import java.util.Objects;

public class SettingsFragment extends Fragment implements SettingsContract.MainView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Settings settings;
    private ProgressBar progressBar;
    private SettingsContract.presenter presenter;

    private EditText wallet_adr;
    private TextView link_edit_site;
    private TextView line_tree_email;
    private TextView line_payment_eth;
    private TextView line_public_ip2;

    private ImageView iv_checkbox;
   // private CheckBox checkBoxMonitor;

    private Button buttonOk;
    private OnFragmentInteractionListener mListener;
    private BottomNavigationView navigation;

    private Preferences preferences;

    public SettingsFragment() {

    }

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        initializeView();
        initProgressBar();
        showProgress();
        presenter = new SettingsPresenterImpl(this, new GetSettingsIntractorImpl(), getActivity());
        presenter.requestDataFromServer();
    }

    private void initProgressBar() {
        progressBar = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyleLarge);
        progressBar.getIndeterminateDrawable()
                .setColorFilter(Color.parseColor("#ebcd99"),
                        android.graphics.PorterDuff.Mode.MULTIPLY);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(getActivity());
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);
        getActivity().addContentView(relativeLayout, params);
    }

    private void initializeView() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        preferences = new Preferences(getActivity());
        buttonOk = view.findViewById(R.id.buttonOk);
        wallet_adr = view.findViewById(R.id.wallet_adr);
        link_edit_site = view.findViewById(R.id.link_edit_site);
        line_tree_email = view.findViewById(R.id.line_tree_email);
        line_payment_eth = view.findViewById(R.id.line_payment_eth);
        line_public_ip2 = view.findViewById(R.id.line_public_ip2);
        iv_checkbox = (ImageView) view.findViewById(R.id.iv_checkbox);

        wallet_adr.getBackground().mutate().setColorFilter(
                getResources().getColor(android.R.color.holo_orange_light),
                PorterDuff.Mode.SRC_ATOP);
        Spanned text = Html.fromHtml("<a href='https://ethermine.org/miners/" + preferences.getMiner()+"/settings'>Edit on ethermine.org</a>");
        link_edit_site.setText(text);
        link_edit_site.setMovementMethod(LinkMovementMethod.getInstance());

        navigation = Objects.requireNonNull(getActivity()).findViewById(R.id.navigation);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickOk(view, wallet_adr.getText().toString());
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        hideProgress();
        mListener = null;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToShow(Settings settings, String tagAction) {
        this.settings = settings;
        String payouStr;
        Double payoutDouble;
        String email;
        String ip = "-";
        Boolean monitor = false;
        if (tagAction.equals("BTNCLICK")) {
            loadFragment(SettingsFragment.newInstance("1","2"));
            navigation.setSelectedItemId(R.id.navigation_overview);
        } else

        try {
            monitor = settings.getData().getMonitor().equals("1");
        } catch (Exception ex) {
            monitor = false;
        }

        try {
            ip = settings.getData().getIp();
        } catch (Exception ex) {
            ip = "-";
        }
        
        try {
            email = settings.getData().getEmail();
        } catch (Exception ex) {
            email = "-";
        }
        try {
            payoutDouble = Double.valueOf(settings.getData().getMinPayout())*0.000000000000000001;
            payouStr = String.format(Locale.US,"%.2g%n", payoutDouble)+ " ETH";
        }
        catch (Exception ex) {
            payouStr = "0.0 ETH";
        }

        line_public_ip2.setText(ip);
        line_tree_email.setText(email);
        line_payment_eth.setText(payouStr);
        wallet_adr.setText(preferences.getMiner());
        if(monitor) {
            iv_checkbox.setImageResource(R.drawable.ic_check_box_yen);
        } else {
            iv_checkbox.setImageResource(R.drawable.ic_check_box_no);
        }
      //  checkBoxMonitor.setChecked(monitor);

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(),
                "Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }


    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, fragment);
        ft.commit();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            presenter.onRefreshButtonClick();
        }
        return super.onOptionsItemSelected(item);
    }
}
