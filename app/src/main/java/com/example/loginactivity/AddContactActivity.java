package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginactivity.myObjects.Contact;

public class AddContactActivity extends AppCompatActivity {
    private AppDB db;
    private ContactDao contactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "roomDB.db")
                .allowMainThreadQueries()
                .build();
        contactDao = db.contactDao();

        Button btnAdd=findViewById(R.id.addContactAddButton);
        btnAdd.setOnClickListener(v->{

            EditText name=findViewById(R.id.addContactName);
            EditText nickName=findViewById(R.id.addContactNickName);
            EditText server=findViewById(R.id.addContactServer);
            //// contact validation
            Contact contact=new Contact(name.getText().toString(),nickName.getText().toString(),
                    server.getText().toString(),"","");
            contactDao.insert(contact);
            finish();

        });

    }


}