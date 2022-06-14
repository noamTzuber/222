package com.example.loginactivity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.loginactivity.myObjects.Contact;
import com.example.loginactivity.myObjects.User;

@Database(entities = {Contact.class}, version = 5)
public abstract class AppDB extends RoomDatabase{
    public abstract ContactDao contactDao();
}
