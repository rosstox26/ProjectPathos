package com.rosstox26.pathos.projectpathos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Goals extends Activity implements View.OnClickListener {
    //buttons
    private Button buttonBronze;
    private Button buttonSilver;
    private Button buttonGold;
    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        buttonBronze = (Button) findViewById(R.id.buttonBronze);
        buttonBronze.setOnClickListener(this);
        buttonSilver = (Button) findViewById(R.id.buttonSilver);
        buttonSilver.setOnClickListener(this);
        buttonGold = (Button) findViewById(R.id.buttonGold);
        buttonGold.setOnClickListener(this);

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

        //Menu
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

    @Override
    public void onClick(View view) {
        Intent toHome = new Intent(Goals.this, MainActivity.class);
        if (view == buttonBronze){
            String email =  mAuth.getCurrentUser().getEmail();
            String goalLVL = "bronze";
            int goalPTS = 100;
            int goalSTPS = 7500;
            userGoals(email, goalLVL, goalPTS, goalSTPS);
            toHome.putExtra("goalSTPS", goalSTPS);
            startActivity(toHome);

        } else if (view == buttonSilver){
            String email =  mAuth.getCurrentUser().getEmail();
            String goalLVL = "silver";
            int goalPTS = 150;
            int goalSTPS = 10000;
            userGoals(email, goalLVL, goalPTS, goalSTPS);
            toHome.putExtra("goalSTPS", goalSTPS);
            startActivity(toHome);

        } else if (view == buttonGold){
            String email =  mAuth.getCurrentUser().getEmail();
            String goalLVL = "gold";
            int goalPTS = 250;
            int goalSTPS = 15000;
            userGoals(email, goalLVL, goalPTS, goalSTPS);
            toHome.putExtra("goalSTPS", goalSTPS);
            startActivity(toHome);

        }
    }

    public void userGoals(String email, String goalLevel, int goalPoints, int goalSteps) {
        ActivityDataDB activity = new ActivityDataDB(email, goalLevel, goalPoints, goalSteps);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataGoals = database.getReference("userActivity");
        DatabaseReference dataNewGoals = dataGoals.push();
        dataNewGoals.setValue(activity);
    }
}

