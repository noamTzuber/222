package com.example.loginactivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.loginactivity.API.ContactAPI;
import com.example.loginactivity.API.ContactWebServiceAPI;
import com.example.loginactivity.adapters.ContactsListAdapter;
import com.example.loginactivity.databinding.ActivityChatBinding;
import com.example.loginactivity.myObjects.Contact;
import com.example.loginactivity.myObjects.IdUser;
import com.example.loginactivity.myObjects.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity  {


    private ActivityChatBinding binding;
    List<String> list;
    private ContactsListAdapter adapter;
    private AppDB db;
       private ContactDao contactDao;
    private AppDBIdUser dbUser;
    private IdUserDao idUserDao;
    private AppDBMessage dbMessage;
    private MessageDao messageDao;
    private RecyclerView lstContacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        String server=intent.getStringExtra("server");

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(ChatActivity.this,
                instanceIdResult -> {
                    String newToken=instanceIdResult.getToken();
                });


        // room for contacts
        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "roomDB.db")
                .fallbackToDestructiveMigration().allowMainThreadQueries()
                .build();
        contactDao = db.contactDao();

        dbMessage = Room.databaseBuilder(getApplicationContext(), AppDBMessage.class, "roomDBMessage.db")
                .fallbackToDestructiveMigration().allowMainThreadQueries()
                .build();
        messageDao = dbMessage.messageDao();

        // room for user
        dbUser = Room.databaseBuilder(getApplicationContext(), AppDBIdUser.class, "roomDBIdUser.db")
                .fallbackToDestructiveMigration().allowMainThreadQueries()
                .build();
        idUserDao = dbUser.idUserDao();

        lstContacts = findViewById(R.id.lstContacts);
        adapter = new ContactsListAdapter(this);
        lstContacts.setAdapter(adapter);
        lstContacts.setLayoutManager(new LinearLayoutManager(this));

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "roomDB.db")
                .fallbackToDestructiveMigration().allowMainThreadQueries()
                .build();
        contactDao = db.contactDao();


        List<IdUser> userId=idUserDao.index();
        if(userId.size()==0){
            idUserDao.insert(new IdUser(id,server));
        }
        else{
            String previousId=userId.get(0).getId();
            if(previousId.equals(id)){

            }
            else{
                idUserDao.deleteAll();
                idUserDao.insert(new IdUser(id,server));
                contactDao.deleteAll();
                messageDao.deleteAll();

            }
        }

        ContactAPI contactAPI = new ContactAPI();
        ContactWebServiceAPI contactWebServiceAPI = contactAPI.getContactWebServiceAPI();
        getAllContacts(lstContacts,id,contactWebServiceAPI,server);

        FloatingActionButton btnAdd= findViewById(R.id.chatActivityAddButton);
        btnAdd.setOnClickListener(view->{
            Intent i =new Intent(this, AddContactActivity.class);
            i.putExtra("id",id);
            i.putExtra("server",server);
            startActivity(i);
        });

        FloatingActionButton btnSetting= findViewById(R.id.settingActivityAddButton);
        btnSetting.setOnClickListener(view->{
            Intent i =new Intent(this, SettingActivity.class);
            i.putExtra("id",id);
            i.putExtra("server",server);
            startActivity(i);
        });



//        contactDao.delete(contactDao.get("noam"));
    }

    @Override
    protected void onResume(){
        super.onResume();
        adapter.setContacts(contactDao.index());
        lstContacts.setAdapter(adapter);

    }

    public void getAllContacts(RecyclerView lstContacts ,String id,ContactWebServiceAPI contactWebServiceAPI,String server){

        Call<List<Contact>> call =   contactWebServiceAPI.getContacts(id);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse( Call<List<Contact>> call, Response<List<Contact>> response) {
                //String s = response.body();
                boolean isSuccessful = response.isSuccessful();
                if (isSuccessful) {
                    contactDao.deleteAll();
                    adapter.setContacts(response.body());
                    lstContacts.setAdapter(adapter);
                    contactDao.insertAllOrders(response.body());
                    //livaData - changing the room contact to server DB contacts
                }
                else {
                    TextView text = findViewById(R.id.addContactErrorMessage);
                    text.setText(R.string.invitation_failed);
                }
            }

            @Override
            public void onFailure( Call<List<Contact>> call,  Throwable t) {
                TextView text= findViewById(R.id.addContactErrorMessage);
                text.setText(R.string.invitation_failed);
            }
        });

    }
}