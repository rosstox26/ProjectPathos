package com.rosstox26.pathos.projectpathos;

/**
 * Created by joeberkowitz on 12/18/16.
 */

/*This class would be used to store daily steps in the Firebase Databsae. Ideally, we
would connect this to native tracking data so that steps are being added throughout the day.
See the attached step counter mini-app. We could not explore the accuracy of this functionality and
thus left it separate from this prototype/minimum viable product.
 */

public class DailySteps {
    String email;
    String date;
    String steps;

    public DailySteps() {

    }

    public DailySteps(String email, String date, String steps) {
        this.email = email;
        this.date = date;
        this.steps = steps;
    }
}