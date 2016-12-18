package com.rosstox26.pathos.projectpathos;

/**
 * Created by nickreitnour on 12/15/16.
 */

public class UserInfoDB {
    String email;
    String password;
    String weight;
    String heightFt;
    String heightIn;

    //empty constructor
    public UserInfoDB() {
    }

    //user information constructor
    public UserInfoDB(String email, String password, String weight, String heightFt, String heightIn) {
        this.email = email;
        this.password = password;
        this.weight = weight;
        this.heightFt = heightFt;
        this.heightIn = heightIn;
    }

}
