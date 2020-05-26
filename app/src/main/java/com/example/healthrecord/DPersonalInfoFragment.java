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
    TextView fname;
    Doctor doc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_info, container, false);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        fname = view.findViewById(R.id.pi_fname);
        db = FirebaseFirestore.getInstance();
        db.collection(Doctor.collectionName).document(user.getEmail()).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                doc = task.getResult().toObject(Doctor.class);
                fname.setText(doc.getFullName());
            }
        });

        return view;
    }
}
