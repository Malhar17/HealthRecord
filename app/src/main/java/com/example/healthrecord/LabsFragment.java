package com.example.healthrecord;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class LabsFragment extends Fragment {

    ImageView add;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_labs, container, false);
        add = view.findViewById(R.id.labs_add);
        add.setVisibility(View.INVISIBLE);
        return view;
    }
}
