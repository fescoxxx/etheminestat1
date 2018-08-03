package com.rijsoft.ethermine.etherminestats.ui;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rijsoft.ethermine.etherminestats.R;
import com.rijsoft.ethermine.etherminestats.adapters.OverviewAdapter;
import com.rijsoft.ethermine.etherminestats.contracts.OverviewContract;
import com.rijsoft.ethermine.etherminestats.intractors.GetOverviewIntractorImpl;
import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;
import com.rijsoft.ethermine.etherminestats.presenters.OverviewPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OverviewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OverviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OverviewFragment extends Fragment implements OverviewContract.MainView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CurrentStats currentStats;

    private OnFragmentInteractionListener mListener;

    private ProgressBar progressBar;
    private OverviewContract.presenter presenter;

    private RecyclerView recyclerView;

    public OverviewFragment() {
    }

    /**
    *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Overview.
     */
    public static OverviewFragment newInstance(String param1, String param2) {
        OverviewFragment fragment = new OverviewFragment();
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

            initProgressBar();
            showProgress();

            presenter = new OverviewPresenterImpl(this,
                    new GetOverviewIntractorImpl(),
                    getActivity());
            presenter.requestDataFromServer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        recyclerView = view.findViewById(R.id.my_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    private void initializeView() {
    }

    private void initProgressBar() {
        progressBar = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyleLarge);
        progressBar.getIndeterminateDrawable()
                .setColorFilter(Color.parseColor("#DF691A"),
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
    public void setDataToShow(CurrentStats currentStats) {
        this.currentStats = currentStats;
        OverviewAdapter adapter = new OverviewAdapter(getActivity(), currentStats);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter != null) {
            presenter.onDestroy();
        }
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(),
                "Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    /**
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //refresh
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            Log.d("refreshpay","refresh_over");
            presenter.onRefreshButtonClick();
        }
        return super.onOptionsItemSelected(item);
    }

}

