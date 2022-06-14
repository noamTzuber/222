package com.example.loginactivity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.loginactivity.myObjects.IdUser;

@Database(entities = {IdUser.class}, version = 6)
    public abstract class AppDBIdUser extends RoomDatabase {

        public abstract IdUserDao idUserDao();
}
