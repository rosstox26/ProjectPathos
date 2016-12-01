package com.rosstox26.pathos.projectpathos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;

public class AccountSetup extends Activity implements View.OnClickListener {

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

    }

    @Override
    public void onClick (View View){

        if (View == bSumbit) {
            //Check for current user
            //Update any changes into the Account Settings part of the database

        } else if (View == bGoBack){
            Intent intentHome = new Intent(this, WelcomeScreen.class); //Return to Welcome Screen
            startActivity(intentHome);
        }
    }
}
