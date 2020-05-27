package com.example.healthrecord;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class DLeaveApplicationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leave_application, container, false);
        ImageView add = view.findViewById(R.id.leaveApp_add);
        add.setVisibility(View.INVISIBLE);
        ListView listView = view.findViewById(R.id.la_list);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            add.setVisibility(View.GONE);
            db.collection(LeaveApplication.collectionName).whereEqualTo("dID", user.getEmail())
                    .get()
                    .addOnCompleteListener(task -> {
                        ArrayList<LeaveApplication> applications = new ArrayList<>();
                        ArrayList<String> ids = new ArrayList<>();
                        for(QueryDocumentSnapshot snapshot : task.getResult()){
                            applications.add(snapshot.toObject(LeaveApplication.class));
                            ids.add(snapshot.getId());
                        }
                        listView.setAdapter(new LeaveAppAdapter(applications, getContext(), 2, ids));
                    });

        return view;
    }
}
