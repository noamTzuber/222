package com.example.loginactivity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.loginactivity.myObjects.Contact;
import com.example.loginactivity.myObjects.Message;
import com.example.loginactivity.myObjects.User;

@Database(entities = {Message.class}, version = 5)
public abstract class AppDBMessage extends RoomDatabase{
    public abstract MessageDao messageDao();
}
