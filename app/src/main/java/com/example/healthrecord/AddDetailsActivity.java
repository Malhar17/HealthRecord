package com.example.healthrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Objects;

public class AddDetailsActivity extends AppCompatActivity {

    private static final String TAG = "AddDetailsActivity";
    EditText fName, lName, address, mobNum, height, weight;
    Button submit;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    RelativeLayout ayd_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        fName = findViewById(R.id.firstName);
        lName = findViewById(R.id.lastName);
        address = findViewById(R.id.addresss);
        mobNum = findViewById(R.id.mobNumber);
        weight = findViewById(R.id.weight1);
        height = findViewById(R.id.height);
        submit = findViewById(R.id.submit);
        ayd_main = findViewById(R.id.ayd_main);
        ayd_main.setVisibility(View.INVISIBLE);
        updateUI(mAuth);
        submit.setOnClickListener((view) -> {
            if(checkDetails()){
                double temp = Math.random();
                boolean isStudent = temp < .5;
                Patient patient = new Patient(fName.getText().toString(), lName.getText().toString(), address.getText().toString(), mobNum.getText().toString(), isStudent);
                patient.setWeight(Double.parseDouble(weight.getText().toString()));
                patient.setHeight(Double.parseDouble(height.getText().toString()));
                db.collection(Patient.collectionName).document(Objects.requireNonNull(mAuth.getCurrentUser().getEmail())).set(patient).addOnSuccessListener(aVoid -> {
                    Log.d(TAG, "Document successfully created");
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Log.d(TAG, "Error writing document", e);
                });
            }
        });
    }

    private void updateUI(FirebaseAuth mAuth) {
        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference patientUser = db.collection(Patient.collectionName).document(user.getEmail());
        patientUser.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                DocumentSnapshot document = task.getResult();
                if(document.exists()){
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        DocumentReference docUser = db.collection(Doctor.collectionName).document(user.getEmail());
        docUser.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                DocumentSnapshot document = task.getResult();
                if(document.exists()){
                    Intent intent = new Intent(this, DMainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            ayd_main.setVisibility(View.VISIBLE);
        }, 7000);
    }

    private boolean checkDetails() {
        if(fName.getText().toString() == null){
            fName.setError("This field should not be empty");
            return false;
        }
        if(lName.getText().toString() == null){
            lName.setError("This field should not be empty");
            return false;
        }
        if(address.getText().toString() == null){
            address.setError("This field should not be empty");
            return false;
        }
        if(mobNum.getText().toString() == null){
            mobNum.setError("This field should not be empty");
            return false;
        }
        if(mobNum.getText().toString().length() != 10){
            fName.setError("Moblie number should of 10 digits");
            return false;
        }
        if(height.getText().toString() == null){
            height.setError("This field should not be empty");
            return false;
        }
        if(weight.getText().toString() == null){
            weight.setError("This field should not be empty");
            return false;
        }
        return true;
    }
}
