package com.rosstox26.pathos.projectpathos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
User inputs their basic information including height, weight, gender, etc..
This information will be added into a database recording user data.
It can be updated by accessing account settings from the menu.
 */

public class AccountSetup extends Activity implements View.OnClickListener {

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    //edit text
    private EditText etName;    //Want to update this information within a firebase database
    private EditText etWeight;
    private EditText etHeight1;
    private EditText etHeight2;
    //radio buttons
    private RadioButton rbGender1;
    private RadioButton rbGender2;
    private RadioButton rbGender3;
    private RadioGroup rgGender;
    //buttons
    private Button bGoBack;
    private Button bSumbit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setup);

        //edit text
        etName = (EditText) findViewById(R.id.etName);
        etWeight = (EditText) findViewById(R.id.etWeight);
        etHeight1 = (EditText) findViewById(R.id.etHeight1);
        etHeight2 = (EditText) findViewById(R.id.etHeight2);
        //radio buttons
        rbGender1 = (RadioButton) findViewById(R.id.rbGender1);
        rbGender2 = (RadioButton) findViewById(R.id.rbGender2);
        rbGender3 = (RadioButton) findViewById(R.id.rbGender3);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        //buttons
        bGoBack = (Button) findViewById(R.id.bGoBack);
        bSumbit = (Button) findViewById(R.id.bSubmit);

        //listeners
        bGoBack.setOnClickListener(this);
        bSumbit.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(AccountSetup.this, "User logged in" + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Toast.makeText(AccountSetup.this, "Nobody is logged in", Toast.LENGTH_SHORT).show();
                }
                // ...
            }
        };
    }

    @Override
    public void onClick (View View){

       //Not exactly sure what to do with the database stuff here.
        // We likely would want a different part of the database for this information
        //Should we be working off one firebase the whole time for this project?

        if (View == bSumbit) {
            //Check for current user
            //Update any changes into the Account Settings part of the database

        } else if (View == bGoBack){
            //Return to Welcome Screen
            Intent intentHome = new Intent(this, Homepage.class);
            startActivity(intentHome);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intentHome = new Intent(AccountSetup.this, MainActivity.class);
        Intent intentRewards = new Intent(AccountSetup.this, RedeemRewards.class);
        Intent intentGoals = new Intent(AccountSetup.this, Goals.class);
        Intent intentAccount = new Intent(AccountSetup.this, AccountSetup.class);
        Intent intentLogout = new Intent(AccountSetup.this, LoginScreen.class);

        if (mAuth.getCurrentUser() != null) {

            if (item.getItemId() == R.id.menuRewards) {
                startActivity(intentRewards);

            } else if (item.getItemId() == R.id.menuGoals) {
                startActivity(intentGoals);

            } else if (item.getItemId() == R.id.menuAccount) {
                //Essentially refreshes page
                startActivity(intentAccount);

            } else if(item.getItemId() == R.id.menuHome){
                startActivity(intentHome);

            } else if (item.getItemId() == R.id.menuLogout){
                mAuth.signOut();
                startActivity(intentLogout);
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
