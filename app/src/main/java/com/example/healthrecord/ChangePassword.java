package com.example.healthrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        submit = findViewById(R.id.cp_submit);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        submit.setOnClickListener(v -> {
            if(newPass.getText().toString().length() <6){
                newPass.setError("Password should be atleast 6 characters long");
                return;}
            AuthCredential credential = EmailAuthProvider.getCredential(Objects.requireNonNull(user.getEmail()), oldPass.getText().toString());
            user.reauthenticate(credential)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            user.updatePassword(newPass.getText().toString()).addOnCompleteListener(task1 -> {
                                if(task1.isSuccessful()){
                                    Log.d(TAG, "Password Changed");
                                    Toast.makeText(this, "Password Changed", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {Log.d(TAG, "Error changing password");
                                    Toast.makeText(this, "Error changing password", Toast.LENGTH_SHORT).show();}
                            });
                        }
                        else {Log.d(TAG, "Error in auth");
                            oldPass.setError("Wrong old password");}
                    });
        });
    }
}
