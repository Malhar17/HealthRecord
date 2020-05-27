package com.example.healthrecord;

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

public class MedRecordFragment extends Fragment {

    ImageView add;
    ListView list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_med_record, container, false);
        add = view.findViewById(R.id.med_add);
        list = view.findViewById(R.id.med_list);
        add.setVisibility(View.INVISIBLE);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d("TAG", "Before QQ");
        Log.d("TAG", user.getEmail());
        db.collection(HealthRecords.collectionName).whereEqualTo("pID", user.getEmail())
                .get()
                .addOnCompleteListener(task -> {
                    Log.d("TAG", "Task");
                    if (task.isSuccessful()){
                        Log.d("TAG", "Task1");
                        ArrayList<HealthRecords> records = new ArrayList<>();
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            records.add(documentSnapshot.toObject(HealthRecords.class));
                            Log.d("TAG", records.get(0).getpID());
                        }
                        list.setAdapter(new MedRecordsAdapter(records,getContext()));
                    }
                });
        return view;
    }

}
