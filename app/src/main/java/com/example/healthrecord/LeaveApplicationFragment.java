package com.example.healthrecord;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class LeaveApplicationFragment extends Fragment {

    private int userType;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leave_application, container, false);
        ImageView add = view.findViewById(R.id.leaveApp_add);
        add.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddLeaveApplication.class);
            startActivity(intent);
        });
        ListView listView = view.findViewById(R.id.la_list);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        db.collection(Patient.collectionName).document(user.getEmail()).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Patient patient = task.getResult().toObject(Patient.class);
                userType = patient.isStudent() ? 0 : 1;
                Log.d("TAG", String.valueOf(userType));
                if (userType == 1) {
                    Log.d("TAG", "FFFF");
                    add.setVisibility(View.GONE);
                    db.collection(LeaveApplication.collectionName).whereEqualTo("fID", user.getEmail())
                            .get()
                            .addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {
                                    ArrayList<LeaveApplication> applications = new ArrayList<>();
                                    ArrayList<String> ids = new ArrayList<>();
                                    for (QueryDocumentSnapshot snapshot : task1.getResult()) {
                                        applications.add(snapshot.toObject(LeaveApplication.class));
                                        ids.add(snapshot.getId());
                                    }
                                    listView.setAdapter(new LeaveAppAdapter(applications, getContext(), userType, ids));
                                }
                            });
                }
                else {
                    Log.d("TAG", "SSSS");
                    db.collection(LeaveApplication.collectionName).whereEqualTo("sID", user.getEmail())
                            .get()
                            .addOnCompleteListener(task1 -> {
                                if(task1.isSuccessful()) {
                                    ArrayList<LeaveApplication> applications = new ArrayList<>();
                                    ArrayList<String> ids = new ArrayList<>();
                                    for (QueryDocumentSnapshot snapshot : task1.getResult()) {
                                        applications.add(snapshot.toObject(LeaveApplication.class));
                                        ids.add(snapshot.getId());
                                    }
                                    listView.setAdapter(new LeaveAppAdapter(applications, getContext(), userType, ids));
                                }
                            });
                }
            }
        });

        return view;
    }
}
