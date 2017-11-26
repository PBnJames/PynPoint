package com.example.android.pynpoint;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by rburr on 11/25/2017.
 */
@Entity
public class User {

    @PrimaryKey
    public final int id;
    public String date;
    public String length;
    public String complete;
    public String points;


    public User(int id, String length, String date,String complete,String points) {
        this.id = id;
        this.length = length;
        this.complete  = complete;
        this.points = points;
        this.date=date;
    }

}