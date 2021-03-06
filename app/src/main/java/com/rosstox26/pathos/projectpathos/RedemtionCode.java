package com.rosstox26.pathos.projectpathos;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
A user can redeem their reward given the redemption code that populates.
Once redeemed the reward is gone, as it has been used and the user is taken
back to the homepage.
 */

public class RedemtionCode extends Activity  implements View.OnClickListener {
    //Added in menu to this page. No other java yet.
    private Button buttonRedemption;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redemtion_code);

        buttonRedemption = (Button) findViewById(R.id.buttonRedemption);
        buttonRedemption.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(RedemtionCode.this, "User logged in" + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Toast.makeText(RedemtionCode.this, "Nobody is logged in", Toast.LENGTH_SHORT).show();
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
        Intent intentHome = new Intent(RedemtionCode.this, MainActivity.class);
        Intent intentRewards = new Intent(RedemtionCode.this, RedeemRewards.class);
        Intent intentGoals = new Intent(RedemtionCode.this, Goals.class);
        Intent intentAccount = new Intent(RedemtionCode.this, AccountSetup.class);
        Intent intentLogout = new Intent(RedemtionCode.this, LoginScreen.class);

        if (mAuth.getCurrentUser() != null) {

            if (item.getItemId() == R.id.menuRewards) {
                startActivity(intentRewards);

            } else if (item.getItemId() == R.id.menuGoals) {
                startActivity(intentGoals);

            } else if (item.getItemId() == R.id.menuAccount) {
                startActivity(intentAccount);

            } else if (item.getItemId() == R.id.menuHome) {
                startActivity(intentHome);

            } else if (item.getItemId() == R.id.menuLogout) {
                mAuth.signOut();
                startActivity(intentLogout);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent toHome = new Intent(RedemtionCode.this, MainActivity.class);
        if (view == buttonRedemption) {
            String email = mAuth.getCurrentUser().getEmail();
            startActivity(toHome);
        }

    }
}
