package com.example.healthrecord;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100;
    private static final String TAG = "tag";
    Button login;
    EditText email, pass;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        login.setOnClickListener( view -> {
            if(email.getText().toString() == null){
                email.setError("Email address is required");
            }
            if(pass.getText().toString() == null){
                pass.setError("Password is required");
            }
            mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            Log.d(TAG, "signin user successfully");
                            updateUI();
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUI();
    }

    private void updateUI() {
        FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currUser != null){
            Intent intent = new Intent(this, AddDetailsActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
