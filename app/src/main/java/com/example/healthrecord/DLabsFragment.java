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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class DLabsFragment extends Fragment {

    ImageView add;
    ListView list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_labs, container, false);
        add = view.findViewById(R.id.labs_add);
        add.setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(), AddLabTestResult.class);
            startActivity(intent);
        });
        list = view.findViewById(R.id.lt_list);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d("TAG", "Before QQ");
        Log.d("TAG", user.getEmail());
        db.collection(LabTests.collectionName).whereEqualTo("dID", user.getEmail())
                .get()
                .addOnCompleteListener(task -> {
                    Log.d("TAG", "Task");
                    if (task.isSuccessful()){
                        Log.d("TAG", "Task1");
                        ArrayList<LabTests> records = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            records.add(documentSnapshot.toObject(LabTests.class));
                            Log.d("TAG", records.get(0).getpID());
                        }
                        list.setAdapter(new LabsAdapter(records,getContext()));
                    }
                });
        return view;
    }
}
