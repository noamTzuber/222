package com.example.loginactivity.API;

import androidx.room.Entity;

public class MessageForServer {

    String contact;
    String content;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageForServer(String contact, String content) {
        this.contact = contact;
        this.content = content;
    }
}
