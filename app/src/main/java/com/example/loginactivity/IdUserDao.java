package com.example.loginactivity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.loginactivity.myObjects.Contact;
import com.example.loginactivity.myObjects.IdUser;

import java.util.List;

@Dao
public interface IdUserDao {

        @Query("SELECT * FROM idUser")
        List<IdUser> index();

        @Query("DELETE  FROM idUser")
        void deleteAll();

        @Query("SELECT * FROM idUser WHERE id = :id")
        IdUser get(String id);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(IdUser... idUser);

        @Update
        void update(IdUser... idUser);

        @Delete
        void delete(IdUser... idUser);
    }


