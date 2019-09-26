package com.anik.armr.rentmate.Fragments.Owner;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anik.armr.rentmate.R;

public class OwnerRentHistoryFragment extends Fragment {


    public OwnerRentHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_owner_rent_history, container, false);
    }

}
