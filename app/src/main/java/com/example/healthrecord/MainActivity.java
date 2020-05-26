package com.example.healthrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new PersonalInfoFragment()).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.pInfo:
                    Toast.makeText(MainActivity.this, R.string.personal_info, Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new PersonalInfoFragment()).commit();
                    break;
                case R.id.medRecord:
                    Toast.makeText(MainActivity.this, R.string.medical_records, Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MedRecordFragment()).commit();
                    break;
                case R.id.leaveApp:
                    Toast.makeText(MainActivity.this, R.string.leave_application, Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new LeaveApplicationFragment()).commit();
                    break;
                case R.id.labs:
                    Toast.makeText(MainActivity.this, R.string.labs, Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new LabsFragment()).commit();
                    break;
            }
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signout:
                mAuth.signOut();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                return (true);
            case R.id.change_password:
                Intent intent1 = new Intent(this, ChangePassword.class);
                startActivity(intent1);
                return (true);
        }
        return(super.onOptionsItemSelected(item));
    }
}
