package com.rosstox26.pathos.projectpathos;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by nickreitnour on 12/15/16.
 */

//@IgnoreExtraProperties //added
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

    /*@Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("password", password);
        result.put("weight", weight);
        result.put("heightFt", heightFt);
        result.put("heightIn", heightIn);

        return result;
    }*/

}
