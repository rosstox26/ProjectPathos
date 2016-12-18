package com.rosstox26.pathos.projectpathos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.auth.api.model.StringList;

/* A user is taken to this page when they select sign up.
This ensures we receive the user's basic information before they begin using the app.
All of their information is stored under user within the firebase database.
 */

public class CreateAccount extends Activity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText editTextCreateAccountEmail;
    private EditText editTextCreateAccountPassword;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private RadioButton radioButtonOther;
    private EditText editTextCreateAccountWeight;
    private EditText editTextCreateAccountHeightFt;
    private EditText editTextCreateAccountHeightIn;
    private Button buttonCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        editTextCreateAccountEmail = (EditText) findViewById(R.id.editTextCreateAccountEmail);
        editTextCreateAccountPassword = (EditText) findViewById(R.id.editTextCreateAccountPassword);
        radioButtonMale = (RadioButton) findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton) findViewById(R.id.radioButtonFemale);
        radioButtonOther = (RadioButton) findViewById(R.id.radioButtonOther);
        editTextCreateAccountWeight = (EditText) findViewById(R.id.editTextCreateAccountWeight);
        editTextCreateAccountHeightFt = (EditText) findViewById(R.id.editTextCreateAccountHeightFt);
        editTextCreateAccountHeightIn = (EditText) findViewById(R.id.editTextCreateAccountHeightIn);
        buttonCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);


        buttonCreateAccount.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    //Toast.makeText(CreateAccount.this, "User logged in " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    //Toast.makeText(CreateAccount.this, "Nobody is logged in", Toast.LENGTH_SHORT).show();
                }
                // ...
            }
        };

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

    @Override
    public void onClick(View view) {
        String email = editTextCreateAccountEmail.getText().toString();
        String password = editTextCreateAccountPassword.getText().toString();
        String weight = editTextCreateAccountWeight.getText().toString();
        String heightFt = editTextCreateAccountHeightFt.getText().toString();
        String heightIn = editTextCreateAccountHeightIn.getText().toString();

        if (view == buttonCreateAccount) {
            createAccount(email, password);
            userProfile(email, password, weight, heightFt, heightIn);
            Intent intentProceed = new Intent(CreateAccount.this, Goals.class);
            startActivity(intentProceed);
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
                            Toast.makeText(CreateAccount.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CreateAccount.this, "User Creation Successful", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
    public void userProfile(String email, String password, String weight, String heightFt, String heightIn) {
        UserInfoDB post = new UserInfoDB(email, password, weight, heightFt, heightIn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataPurchases = database.getReference("user");
        DatabaseReference dataNewPurchase = dataPurchases.push();
        dataNewPurchase.setValue(post);
    }
}
