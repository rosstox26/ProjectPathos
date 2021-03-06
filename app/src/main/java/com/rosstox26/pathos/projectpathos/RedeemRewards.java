package com.rosstox26.pathos.projectpathos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
User sees their available rewards. Ideally this page would populate any earned rewards.
On this product only one reward can be earned from the daily goal, demonstrating
the base process of the app.
*/

public class RedeemRewards extends Activity implements View.OnClickListener {

    private Button buttonReward;
    // /comment
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_rewards);

        buttonReward = (Button) findViewById(R.id.buttonReward);
        buttonReward.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(RedeemRewards.this, "User logged in " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Toast.makeText(RedeemRewards.this, "Nobody is logged in", Toast.LENGTH_SHORT).show();
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
            Intent intentHome = new Intent(RedeemRewards.this, MainActivity.class);
            Intent intentRewards = new Intent(RedeemRewards.this, RedeemRewards.class);
            Intent intentGoals = new Intent(RedeemRewards.this, Goals.class);
            Intent intentAccount = new Intent(RedeemRewards.this, AccountSetup.class);
            Intent intentLogout = new Intent(RedeemRewards.this, LoginScreen.class);

            if (mAuth.getCurrentUser() != null) {

                if (item.getItemId() == R.id.menuRewards) {
                    //Essentially refreshes page
                    startActivity(intentRewards);

                } else if (item.getItemId() == R.id.menuGoals) {
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

    @Override
    public void onClick(View view) {
        Intent toRedemption= new Intent(RedeemRewards.this, RedemtionCode.class);
        if (view == buttonReward) {
            String email = mAuth.getCurrentUser().getEmail();
            startActivity(toRedemption);
        }
    }
}
