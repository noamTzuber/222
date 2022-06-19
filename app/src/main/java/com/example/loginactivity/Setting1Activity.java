package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginactivity.myObjects.IdUser;

public class Setting1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting1);
        AppDBIdUser dbUser = Room.databaseBuilder(getApplicationContext(), AppDBIdUser.class, "roomDBIdUser.db")
                .fallbackToDestructiveMigration().allowMainThreadQueries()
                .build();
        IdUserDao idUserDao = dbUser.idUserDao();
        Button btnAdd=findViewById(R.id.SettingApplyButton);
        btnAdd.setOnClickListener(v-> {
            EditText server = findViewById(R.id.SettingServerInput);
            String newServer=server.getText().toString();
            IdUser user= idUserDao.index().get(0);
            idUserDao.delete();
            user.setServer(newServer);
            idUserDao.insert(user);

            finish();
    });
}
}