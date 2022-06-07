package com.example.loginactivity.myObjects;

import androidx.room.Entity;

import java.util.List;

@Entity
public class Chat {
    private int id;
    private String user1;
    private String user2;
    private List<Message> messages;

    public Chat() {}
    public Chat(int id, String user1, String user2, List<Message> messages) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.messages = messages;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    public void addMessage(Message message){
        messages.add(message);
    }


}
