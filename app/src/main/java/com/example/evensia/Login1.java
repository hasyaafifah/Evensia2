package com.example.evensia;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Login1 extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button pindahactivity;
    FirebaseAuth fAuth;
    TextView pindahactivity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        mEmail =findViewById(R.id.email);
        mPassword = findViewById(R.id.pass);
        pindahactivity = findViewById(R.id.login);
        pindahactivity2 = findViewById(R.id.bl);

        fAuth = FirebaseAuth.getInstance();

//        if(fAuth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(), Login1.class));
//            finish();
//        }

        pindahactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email Harus diisi");
                }

                else if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password harus diisi");
                }

                else if (password.length() < 6) {
                    mPassword.setError("Password harus diisi dari 6 karakter");
                } else {
                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            Toast.makeText(Login1.this, "Berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }else {
                            Toast.makeText(Login1.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        
        pindahactivity2.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
    }
}