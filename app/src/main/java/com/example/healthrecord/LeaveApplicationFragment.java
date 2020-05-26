package com.example.healthrecord;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class LeaveApplicationFragment extends Fragment {

    Patient patient;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leave_application, container, false);
        ImageView add = view.findViewById(R.id.leaveApp_add);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(Patient.collectionName).document(user.getEmail()).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                patient = task.getResult().toObject(Patient.class);
                if(!patient.isStudent())
                    add.setVisibility(View.INVISIBLE);
                else add.setOnClickListener(v -> {
                    Intent intent = new Intent(getContext(), AddLeaveApplication.class);
                    startActivity(intent);
                });
            }
        });
        return view;
    }
}
