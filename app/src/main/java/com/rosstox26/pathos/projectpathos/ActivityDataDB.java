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

    ActivityDataDB(){}

    ActivityDataDB(String email, String goalLevel, int goalPoints, int goalSteps){
        this.email = email;
        this.goalLevel = goalLevel;
        this.goalPoints = goalPoints;
        this.goalSteps = goalSteps;
    }

    ActivityDataDB(String email, int stepsReported){
        this.stepsReported = stepsReported;
    }

}
