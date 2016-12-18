package com.rosstox26.pathos.projectpathos;

/**
 * Created by cassiehoffman on 12/16/16.
 */

public class ActivityDataDB {
    String email;
    String goalLevel;
    int goalPoints;
    int goalSteps;
    int stepsReported;
    //String currentTime;

    ActivityDataDB(){}

    ActivityDataDB(String email, String goalLevel, int goalPoints, int goalSteps){
        this.email = email;
        this.goalLevel = goalLevel;
        this.goalPoints = goalPoints;
        this.goalSteps = goalSteps;
        //this.currentTime = currentTime;
    }

    ActivityDataDB(String email, int stepsReported, String currentTime){
        this.stepsReported = stepsReported;
        //this.currentTime = currentTime;
    }

}
