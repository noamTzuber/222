package com.example.loginactivity.myObjects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {

    @PrimaryKey(autoGenerate=false)
    @NonNull
    private String id;
    private String name;
    private String server;
    private String last;
    private String lastDate;

    public Contact(){}
    public Contact(String id, String name, String server, String last, String lastDate) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.last = last;
        this.lastDate = lastDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String name) {
        this.id = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
