package com.example.loginactivity.myObjects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class IdUser {

    @PrimaryKey(autoGenerate=false)
    @NonNull
    String id;
    String server;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public IdUser(@NonNull String id, String server) {
        this.id = id;
        this.server = server;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
