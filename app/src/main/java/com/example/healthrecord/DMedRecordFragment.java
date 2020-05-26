package com.example.healthrecord;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DMedRecordFragment extends Fragment {

    ImageView add;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_med_record, container, false);
        add = view.findViewById(R.id.med_add);
        add.setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(), AddMedRecord.class);
            startActivity(intent);
        });
        return view;
    }

}
