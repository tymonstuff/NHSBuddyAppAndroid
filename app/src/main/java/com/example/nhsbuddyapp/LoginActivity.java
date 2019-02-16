package com.example.nhsbuddyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity
{
    private EditText m_email, m_password;
    private Button m_login_btn;
    private TextView m_register_btn;

    private FirebaseAuth m_auth;

    //TODO: Attempt to invoke virtual method 'com.google.android.gms.tasks.Task com.google.firebase.auth.FirebaseAuth.createUserWithEmailAndPassword(java.lang.String, java.lang.String)' on a null object reference

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        m_auth = FirebaseAuth.getInstance();

        m_email = findViewById(R.id.email_field);
        m_password = findViewById(R.id.password_field);
        m_login_btn = findViewById(R.id.login_btn);
        m_register_btn = findViewById(R.id.sign_in_btn);

    }

    public void onLoginClick(View view)
    {
        String email_txt = m_email.getText().toString();
        String password_txt = m_password.getText().toString();

        // Ensure that fields are not empty on click
        if(TextUtils.isEmpty(email_txt) || TextUtils.isEmpty(password_txt))
        {
            Toast.makeText(LoginActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            m_auth.signInWithEmailAndPassword(email_txt, password_txt)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "Authentication failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void onRegisterLinkClick(View view)
    {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }


}
