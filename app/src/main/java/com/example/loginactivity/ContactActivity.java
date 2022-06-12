package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.loginactivity.adapters.ContactsListAdapter;
import com.example.loginactivity.adapters.MessagesListAdapter;

import com.example.loginactivity.myObjects.Message;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {


    private MessagesListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        TextView t=findViewById(R.id.contactActivity_contactName);
        t.setText(name);

        List<Message> me=new ArrayList<Message>();
        me.add(new Message(1,"hihihihihihihihihihi","9.0",true));
        me.add(new Message(2,"bibi","10.0",false));
        me.add(new Message(3,"bibi","10.0",false));
        me.add(new Message(4,"bibi","10.0",true));

        RecyclerView lstMessages = findViewById(R.id.lstMessages);
        adapter=new MessagesListAdapter(this);
        lstMessages.setAdapter(adapter);
        lstMessages.setLayoutManager(new LinearLayoutManager(this));
        adapter.setMessages(me);

    }
}