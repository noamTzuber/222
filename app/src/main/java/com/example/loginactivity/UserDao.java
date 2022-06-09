package com.example.loginactivity;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.loginactivity.myObjects.Contact;
import com.example.loginactivity.myObjects.User;

import java.util.List;


@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<Contact> index();

    @Query("SELECT * FROM user WHERE id = :id")
    Contact get(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User... users);
}
