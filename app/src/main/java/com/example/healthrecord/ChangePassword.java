package com.example.healthrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class ChangePassword extends AppCompatActivity {

    private static final String TAG = "TAG";
    EditText oldPass, newPass;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        oldPass = findViewById(R.id.oldPass);
        newPass = findViewById(R.id.newPass);
        submit = findViewById(R.id.submit);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        AuthCredential credential = EmailAuthProvider.getCredential(Objects.requireNonNull(user.getEmail()), oldPass.getText().toString());
        user.reauthenticate(credential)
                .addOnCompleteListener(task -> {
                   if (task.isSuccessful()){
                       user.updatePassword(newPass.getText().toString()).addOnCompleteListener(task1 -> {
                           if(task1.isSuccessful()){
                               Log.d(TAG, "Password Changed");
                               Intent intent = new Intent(this, MainActivity.class);
                               startActivity(intent);
                               finish();
                           }
                           else Log.d(TAG, "Error changing password");
                       });
                   }
                   else Log.d(TAG, "Error in auth");
                });
    }
}
