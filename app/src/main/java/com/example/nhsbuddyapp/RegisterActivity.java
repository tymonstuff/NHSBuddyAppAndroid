package com.example.nhsbuddyapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity
{
    private EditText m_username, m_email, m_password;
    private Button m_register_btn;

    private FirebaseAuth mAuth;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mAuth.getInstance();
        setContentView(R.layout.activity_register);

        m_username = findViewById(R.id.username_field);
        m_email = findViewById(R.id.email_field);
        m_password = findViewById(R.id.password_field);
        m_register_btn = findViewById(R.id.register_btn);

    }



    public void onBackPressed()
    {
        super.onBackPressed();
        this.finish();
    }

    public void onRegisterClick(View view)
    {
        String username_txt = m_username.getText().toString();
        String email_txt = m_email.getText().toString();
        String password_txt = m_password.getText().toString();

        if(TextUtils.isEmpty(username_txt) ||TextUtils.isEmpty(email_txt) ||TextUtils.isEmpty(password_txt))
        {
            Toast.makeText(RegisterActivity.this, "All fields required!", Toast.LENGTH_SHORT).show();
        }
        else if(password_txt.length() < 6)
        {
            Toast.makeText(RegisterActivity.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
        }
        else
        {
            System.out.println(email_txt + "  " + password_txt);
            register(username_txt, email_txt, password_txt);
        }
    }

    private void register(final String username, String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                            //        Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user){}

//    private void register(final String username, String email, String password)
//    {
//
//        mAuth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(new OnCompleteListener<AuthResult>()
//            {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task)
//                {
//                    if(task.isSuccessful())
//                    {
//                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
//                        assert firebaseUser != null;
//                        String userid = firebaseUser.getUid();
//
//                        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
//
//                        HashMap<String, String> hashMap = new HashMap<>();
//                        hashMap.put("id", userid);
//                        hashMap.put("username", username);
//
//                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>()
//                        {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task)
//                            {
//                                if(task.isSuccessful())
//                                {
//                                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>()
//                                    {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task)
//                                        {
//                                            if(task.isSuccessful())
//                                            {
//                                                Toast.makeText(RegisterActivity.this, "Registered successfully. Please check your email for verification", Toast.LENGTH_SHORT).show();
//
//                                            }
//                                            else
//                                            {
//                                                Toast.makeText(RegisterActivity.this, "Failed to send email confirmation!", Toast.LENGTH_SHORT).show();
//                                            }
//
//                                        }
//                                    });
//                                }
//                            }
//                        });
//                    }
//
//                }
//            });
//    }

}





























