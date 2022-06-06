package com.example.loginactivity.myObjects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.List;

@Entity
public class User {
    @PrimaryKey(autoGenerate=false)
    @NonNull
    private String id;
    private String name;
    private String password;
    private String server;
    private List<Contact> contacts;

    public User() {}

    public User(String id, String name, String password, String server, List<Contact> contacts) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.server = server;
        this.contacts = contacts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
    public void addContact(Contact contact){
        contacts.add(contact);
    }
}
