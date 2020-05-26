package com.example.healthrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;


public class AddMedRecord extends AppCompatActivity {

    private EditText email, disease, addSym, addMed;
    private Button submit;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med_record);
        email = findViewById(R.id.amr_email);
        disease = findViewById(R.id.amr_disease);
        addSym = findViewById(R.id.addsym);
        addMed = findViewById(R.id.addmed);
        submit = findViewById(R.id.amr_submit);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        submit.setOnClickListener( v -> {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            HealthRecords records = new HealthRecords();
            records.setpID(email.getText().toString());
            records.setdId(user.getEmail());
            records.setDisease(disease.getText().toString());
            DateFormat dateFormat = DateFormat.getDateInstance();
            String date = dateFormat.format(Calendar.getInstance().getTime());
            records.setDate(date);
            records.setSymptoms(Arrays.asList(addSym.getText().toString().split(" ")));
            records.setMedicine(Arrays.asList(addMed.getText().toString().split(" ")));
            db.collection(HealthRecords.collectionName).add(records).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(this, "Record Added to DB", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
