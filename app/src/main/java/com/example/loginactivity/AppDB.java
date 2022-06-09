package com.example.loginactivity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.loginactivity.myObjects.Contact;
import com.example.loginactivity.myObjects.User;

@Database(entities = {Contact.class, User.class}, version = 4)
public abstract class AppDB extends RoomDatabase{
    public abstract ContactDao contactDao();
    public abstract UserDao userDao();



}
