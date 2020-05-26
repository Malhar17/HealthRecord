package com.example.healthrecord;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DLeaveApplicationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leave_application, container, false);
        ImageView add = view.findViewById(R.id.leaveApp_add);
        add.setVisibility(View.INVISIBLE);
        return view;
    }
}
