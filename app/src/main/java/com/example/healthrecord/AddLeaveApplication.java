package com.example.healthrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class AddLeaveApplication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_leave_application);

        EditText d_email, f_email, stdate, enddate;
        Button submit = findViewById(R.id.alp_submit);
        d_email = findViewById(R.id.alp_d_email);
        f_email = findViewById(R.id.alp_f_email);
        stdate = findViewById(R.id.alp_startdate);
        enddate = findViewById(R.id.alp_endate);
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        stdate.setOnClickListener(v -> {
            DatePickerDialog picker = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
                String temp = dayOfMonth + "/" + month1 + "/" +year1;
                stdate.setText(temp);
            }, year, month, day);
            picker.show();
        });
        enddate.setOnClickListener(v -> {
            DatePickerDialog pickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
                String temp =  dayOfMonth + "/" + month1 + "/" +year1;
                enddate.setText(temp);
            }, year, month, day);
            pickerDialog.show();
        });
        submit.setOnClickListener(v -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            LeaveApplication application = new LeaveApplication();
            application.setsID(user.getEmail());
            application.setdID(d_email.getText().toString());
            application.setfID(f_email.getText().toString());
            application.setStartDate(stdate.getText().toString());
            application.setEndDate(enddate.getText().toString());
            application.setApprovedByDoc(0);
            application.setApprovedByFac(0);
            db.collection(LeaveApplication.collectionName).add(application).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(this, "Leave Application Added to DB", Toast.LENGTH_SHORT).show();
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
