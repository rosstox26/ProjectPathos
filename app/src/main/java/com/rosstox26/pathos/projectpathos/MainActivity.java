package com.rosstox26.pathos.projectpathos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
/*
The main activity is where a user can report their daily step count, check their progress toward
their goals, and attempt to redeem awards.
 */
public class MainActivity extends Activity implements View.OnClickListener {


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Button buttonUpdateSteps;
    private Button buttonRedeemReward;
    private TextView textViewSteps;
    private TextView textViewDailySteps;
    private TextView textViewPoints;
    private String steps;
    private int goalSTPS;
    private int numericSteps;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth= FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener()

        {
            @Override
            public void onAuthStateChanged (@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(MainActivity.this, "User Logged In " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Toast.makeText(MainActivity.this, "Nobody Logged In.", Toast.LENGTH_SHORT).show();
                }
            }
        };

        buttonUpdateSteps = (Button) findViewById(R.id.buttonUpdateSteps);
        buttonRedeemReward = (Button) findViewById(R.id.buttonRedeemReward);
        textViewSteps = (TextView) findViewById(R.id.textViewSteps);
        textViewDailySteps = (TextView) findViewById(R.id.textViewDailySteps);
        textViewPoints = (TextView) findViewById(R.id.textViewPoints);

        buttonUpdateSteps.setOnClickListener(this);
        buttonRedeemReward.setOnClickListener(this);

        //Goal steps are updated to reflect the goal option selected
        Intent pastIntent = getIntent();
        goalSTPS = pastIntent.getIntExtra("goalSTPS", goalSTPS);
        textViewDailySteps.setText("Daily Goal: " + String.valueOf(goalSTPS) + " steps");
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
        Intent intentHome = new Intent(MainActivity.this, MainActivity.class);
        Intent intentRewards = new Intent(MainActivity.this, RedeemRewards.class);
        Intent intentGoals = new Intent(MainActivity.this, Goals.class);
        Intent intentAccount = new Intent(MainActivity.this, AccountSetup.class);
        Intent intentLogout = new Intent(MainActivity.this, LoginScreen.class);

        if (mAuth.getCurrentUser() != null) {

            if (item.getItemId() == R.id.menuRewards) {
                startActivity(intentRewards);

            } else if (item.getItemId() == R.id.menuGoals) {
                startActivity(intentGoals);

            } else if (item.getItemId() == R.id.menuAccount) {
                startActivity(intentAccount);

            } else if(item.getItemId() == R.id.menuHome){
                //Essentially refreshes page
                startActivity(intentHome);

            } else if (item.getItemId() == R.id.menuLogout){
                mAuth.signOut();
                startActivity(intentLogout);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
       //A user self reports their stepcount from an activity tracker through dialog box
        if(v == buttonUpdateSteps) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("STEPS");
            alertDialog.setMessage("Enter today's step total from your fitness tracker or android device");

            final EditText input = new EditText(MainActivity.this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            alertDialog.setView(input);

            alertDialog.setPositiveButton("Enter",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            //String email = "aaa";
                            steps = input.getText().toString();
                            numericSteps = Integer.parseInt(steps);
                            /*String date = "12-18-2016";

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference dataDailySteps = database.getReference();
                            DatabaseReference dataNewDailySteps = dataDailySteps.child(email).child(date).push();



                            DailySteps dailySteps = new DailySteps(email, date, steps);
                            dataNewDailySteps.setValue(dailySteps);*/

                            textViewSteps.setText(steps);
                            textViewPoints.setText(steps + " points");
                        }
                    });

            alertDialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            alertDialog.show();
        }
        //user redeems rewards, if earned
        else if(v == buttonRedeemReward) {

            if(numericSteps < goalSTPS) {
                Toast.makeText(MainActivity.this, "Not enough steps for reward", Toast.LENGTH_SHORT).show();
            }
            else if(numericSteps >= goalSTPS) {
                Intent intent = new Intent(MainActivity.this, RedeemRewards.class);
                startActivity(intent);
            }
        }
    }
}
