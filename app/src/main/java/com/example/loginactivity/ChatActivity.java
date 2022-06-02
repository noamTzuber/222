package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.loginactivity.databinding.ActivityChatBinding;
import com.example.loginactivity.databinding.ActivityLoginBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list=new ArrayList<>();
        list.add("noam");
        list.add("itay");
        list.add("tom");
        list.add("dvir");

        ListView listOfChats = binding.ListOfChats;
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this,
                                 android.R.layout.simple_list_item_1,
                                 list);

    }
}