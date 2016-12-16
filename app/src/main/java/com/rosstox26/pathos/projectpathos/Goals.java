package com.rosstox26.pathos.projectpathos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Goals extends Activity {
    //comment
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(Goals.this, "User logged in" + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Toast.makeText(Goals.this, "Nobody is logged in", Toast.LENGTH_SHORT).show();
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
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu, menu);
            return super.onCreateOptionsMenu(menu);

        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            Intent intentHome = new Intent(Goals.this, MainActivity.class);
            Intent intentRewards = new Intent(Goals.this, RedeemRewards.class);
            Intent intentGoals = new Intent(Goals.this, Goals.class);
            Intent intentAccount = new Intent(Goals.this, AccountSetup.class);
            Intent intentLogout = new Intent(Goals.this, LoginScreen.class);

            if (mAuth.getCurrentUser() != null) {

                if (item.getItemId() == R.id.menuRewards) {
                    startActivity(intentRewards);

                } else if (item.getItemId() == R.id.menuGoals) {
                    //Essentially refreshes page
                    startActivity(intentGoals);

                } else if (item.getItemId() == R.id.menuAccount) {
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
