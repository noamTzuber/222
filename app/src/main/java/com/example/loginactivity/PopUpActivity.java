package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginactivity.myObjects.IdUser;

public class PopUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = 1000;
        int height = 1000;
        getWindow().setLayout(width, height);

        Button btnAdd = findViewById(R.id.changeTheServerAfterError);
        btnAdd.setOnClickListener(v -> {
            Intent i =new Intent(this, Setting1Activity.class);
            startActivity(i);
            finish();

        });
    }
}