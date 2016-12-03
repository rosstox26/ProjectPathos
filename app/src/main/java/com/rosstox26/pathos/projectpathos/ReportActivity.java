package com.rosstox26.pathos.projectpathos;

import android.app.Activity;
import android.os.Bundle;

/* User will self report their activity on this page (unless we can figure out how to grab
health data from the android device). When the user clicks the "Save" button, this information
will be saved with their name in the FireBase database related to their activity.
When they click the "See progress" button it will take them to the ViewProgress page.
*/


public class ReportActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
    }
}
