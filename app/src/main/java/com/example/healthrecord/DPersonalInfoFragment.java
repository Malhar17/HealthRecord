package com.example.healthrecord;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class DPersonalInfoFragment extends Fragment {

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    FirebaseUser user;
    TextView fname,address,mobNumber, spec, spec1,  height, weight, bmi, height1, weight1, bmi1;
    Doctor doc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_info, container, false);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        fname = view.findViewById(R.id.pi_fname);
        address = view.findViewById(R.id.pi_address1);
        mobNumber = view.findViewById(R.id.pi_pnum);
        spec = view.findViewById(R.id.pi_spec);
        height = view.findViewById(R.id.pi_height);
        weight = view.findViewById(R.id.pi_weight);
        bmi = view.findViewById(R.id.pi_bmi);
        height1 = view.findViewById(R.id.pi_height1);
        weight1 = view.findViewById(R.id.pi_weight1);
        bmi1 = view.findViewById(R.id.pi_bmi1);
        bmi1.setVisibility(View.GONE);
        bmi.setVisibility(View.GONE);
        height.setVisibility(View.GONE);
        height1.setVisibility(View.GONE);
        weight.setVisibility(View.GONE);
        weight1.setVisibility(View.GONE);
        db = FirebaseFirestore.getInstance();
        db.collection(Doctor.collectionName).document(user.getEmail()).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                doc = task.getResult().toObject(Doctor.class);
                fname.setText(doc.getFullName());
                address.setText(doc.getAddress());
                mobNumber.setText(doc.getMobNumber());
                spec.setText(doc.getSpecialization());
            }
        });

        return view;
    }
}
