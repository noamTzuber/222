package com.example.loginactivity.myObjects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class IdUser {

    @PrimaryKey(autoGenerate=false)
    @NonNull
    String id;

    public IdUser(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
