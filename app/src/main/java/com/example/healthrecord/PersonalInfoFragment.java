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

public class PersonalInfoFragment extends Fragment {

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    FirebaseUser user;
    TextView fname, address, mobNumber, spec, spec1,  height, weight, bmi;
    Patient patient;
    int userType;
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
        spec.setVisibility(View.GONE);
        spec1 = view.findViewById(R.id.pi_spe);
        spec1.setVisibility(View.GONE);
        height = view.findViewById(R.id.pi_height1);
        weight = view.findViewById(R.id.pi_weight1);
        bmi = view.findViewById(R.id.pi_bmi1);
        db = FirebaseFirestore.getInstance();
        db.collection(Patient.collectionName).document(user.getEmail()).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                patient = task.getResult().toObject(Patient.class);
                fname.setText(patient.getFullName());
                address.setText(patient.getAddress());
                mobNumber.setText(patient.getMobNumber());
                height.setText(String.valueOf(patient.getHeight()));
                weight.setText(String.valueOf(patient.getWeight()));
                bmi.setText(String.format("%.2f",patient.getBMI()));
            }
        });


        return view;
    }
}
