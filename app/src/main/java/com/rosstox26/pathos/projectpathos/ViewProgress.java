package com.rosstox26.pathos.projectpathos;

import android.app.Activity;
import android.os.Bundle;

/*The user is brought to this page when they click check progress after reporting their activity.
This Screen shows the user their progress towards their goals
it will compare the user's reported activity with the set goal threshold.
Given this, the progress bar will reflect how close the user is to completing the goal.
*/

public class ViewProgress extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_progress);
    }
}
