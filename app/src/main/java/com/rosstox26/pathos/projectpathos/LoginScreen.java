package com.rosstox26.pathos.projectpathos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.api.model.StringList;

public class LoginScreen extends Activity implements View.OnClickListener {
    //comment

    private EditText editTextEmailAddress;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonCreateUser;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        editTextEmailAddress = (EditText) findViewById(R.id.editTextEmailAddress);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonCreateUser = (Button) findViewById(R.id.buttonCreateUser);

        buttonLogin.setOnClickListener(this);
        buttonCreateUser.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(LoginScreen.this, "User logged in " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Toast.makeText(LoginScreen.this, "Nobody is logged in", Toast.LENGTH_SHORT).show();
                }
                // ...
            }
        };

    }

    @Override
    public void onClick(View view) {
        String email = editTextEmailAddress.getText().toString();
        String password = editTextPassword.getText().toString();

        if (view == buttonLogin) {
            signIn(email, password);
        } else if (view == buttonCreateUser) {
            Intent intentCreateUser = new Intent(LoginScreen.this, CreateAccount.class);
            startActivity(intentCreateUser);
            //createAccount(email, password);
            //signIn(email, password);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginScreen.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginScreen.this, "User Creation Successful", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            //Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(LoginScreen.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(LoginScreen.this, Goals.class);
                            startActivity(intent);
                        }
                        // ...
                    }
                });
    }


}
