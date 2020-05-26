package com.example.healthrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddLabTestResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lab_test_result);
        EditText email, testname;
        Switch result;
        Button submit;
        email = findViewById(R.id.alt_email);
        testname = findViewById(R.id.alt_testname);
        result = findViewById(R.id.alt_result);
        submit = findViewById(R.id.alt_submit);
        submit.setOnClickListener(v -> {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            LabTests labTests = new LabTests();
            labTests.setdID(user.getEmail());
            labTests.setpID(email.getText().toString());
            labTests.setTestName(testname.getText().toString());
            labTests.setResult(result.isChecked());
            db.collection(LabTests.collectionName).add(labTests).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(this, "Labtest result Added to DB", Toast.LENGTH_SHORT).show();
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
