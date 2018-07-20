package com.rijsoft.ethermine.etherminestats.ui;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.rijsoft.ethermine.etherminestats.contracts.PayoutsContract;
import com.rijsoft.ethermine.etherminestats.intractors.GetPayoutsIntractorImpl;
import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;
import com.rijsoft.ethermine.etherminestats.model.payouts.Payouts;
import com.rijsoft.ethermine.etherminestats.presenters.PayoutsPresentrImpl;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PaymentsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PaymentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentsFragment extends Fragment implements PayoutsContract.MainView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Payouts payouts;
    private ProgressBar progressBar;
    private PayoutsContract.presenter presenter;
    
    private OnFragmentInteractionListener mListener;

    public PaymentsFragment() {
        // Required empty public constructor
    }

    /**
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaymentsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentsFragment newInstance(String param1, String param2) {
        PaymentsFragment fragment = new PaymentsFragment();
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
        presenter = new PayoutsPresentrImpl(this, new GetPayoutsIntractorImpl(),
                getActivity());
        presenter.requestDataFromServer();
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

    private void initializeView() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_payments, container, false);
    }

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
    public void setDataToShow(Payouts payouts) {
        this.payouts = payouts;
        Log.d("payouts ",payouts.getStatus());
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(),
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    /**
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            Log.d("refreshpay","refresh_payments");
            presenter.onRefreshButtonClick();
        }
        return super.onOptionsItemSelected(item);
    }
}
