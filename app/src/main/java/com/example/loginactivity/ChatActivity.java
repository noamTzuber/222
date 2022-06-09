package com.example.loginactivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.loginactivity.adapters.ContactsListAdapter;
import com.example.loginactivity.databinding.ActivityChatBinding;
import com.example.loginactivity.myObjects.Contact;
import com.example.loginactivity.myObjects.IdUser;
import com.example.loginactivity.myObjects.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity  {


    private ActivityChatBinding binding;
    List<String> list;
    private ContactsListAdapter adapter;
    private AppDB db;
    private ContactDao contactDao;

    private AppDBIdUser dbUser;
    private IdUserDao idUserDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");


        // room for contacts
        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "roomDB.db")
                .fallbackToDestructiveMigration().allowMainThreadQueries()
                .build();
        contactDao = db.contactDao();

        // room for user
        dbUser = Room.databaseBuilder(getApplicationContext(), AppDBIdUser.class, "roomDBIdUser.db")
                .fallbackToDestructiveMigration().allowMainThreadQueries()
                .build();
        idUserDao = dbUser.idUserDao();

        getAllContacts(id);
        List<IdUser> userId=idUserDao.index();
        if(userId.size()==0){
            idUserDao.insert(new IdUser(id));
        }
        else{
            String previousId=userId.get(0).getId();
            if(previousId.equals(id)){
            }
            else{
                idUserDao.deleteAll();
                idUserDao.insert(new IdUser(id));
                contactDao.deleteAll();

            }
        }

        FloatingActionButton btnAdd= findViewById(R.id.chatActivityAddButton);
        btnAdd.setOnClickListener(view->{
            //String id = intent.getStringExtra("id");
            String server = intent.getStringExtra("server");
            Intent i =new Intent(this, AddContactActivity.class);
            i.putExtra("id",id);
            i.putExtra("server",server);
            startActivity(i);
        });


        RecyclerView lstContacts =findViewById(R.id.lstContacts);
        adapter = new ContactsListAdapter(this);
        lstContacts.setAdapter(adapter);
        lstContacts.setLayoutManager(new LinearLayoutManager(this));
        adapter.setContacts(contactDao.index());
//        contactDao.delete(contactDao.get("noam"));
    }

    @Override
    protected void onResume(){
        super.onResume();
        adapter.setContacts(contactDao.index());

    }
    public void getAllContacts(String id){

    }
}