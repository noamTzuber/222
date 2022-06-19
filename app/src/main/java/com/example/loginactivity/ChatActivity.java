package com.example.loginactivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.loginactivity.API.ContactAPI;
import com.example.loginactivity.API.ContactWebServiceAPI;
import com.example.loginactivity.API.TokenToId;
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
    private AlertDialog.Builder dialogB;
    private AlertDialog dialog;
    private Button changeServer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        String server=intent.getStringExtra("server");

        dbUser = Room.databaseBuilder(getApplicationContext(), AppDBIdUser.class, "roomDBIdUser.db")
                .fallbackToDestructiveMigration().allowMainThreadQueries()
                .build();
        idUserDao = dbUser.idUserDao();

//        if(!idUserDao.index().get(0).getServer().equals("10.0.2.2")){
//            idUserDao.deleteAll();
//        }

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(ChatActivity.this,
                instanceIdResult -> {
                    String newToken=instanceIdResult.getToken();
                    setToken(id,newToken);
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
                IdUser user=idUserDao.index().get(0);
                idUserDao.delete(user);
                user.setServer(server);
                idUserDao.insert(user);
            }
            else{
                idUserDao.deleteAll();
                idUserDao.insert(new IdUser(id,server));
                contactDao.deleteAll();
                messageDao.deleteAll();

            }
        }


        ContactAPI contactAPI = new ContactAPI(idUserDao.index().get(0).getServer().toString());
        ContactWebServiceAPI contactWebServiceAPI = contactAPI.getContactWebServiceAPI();
        getAllContacts(lstContacts,id,contactWebServiceAPI,server);

        FloatingActionButton btnAdd= findViewById(R.id.chatActivityAddButton);
        btnAdd.setOnClickListener(view->{
            Intent i =new Intent(this, AddContactActivity.class);
            i.putExtra("id",id);
            i.putExtra("server",server);
            startActivity(i);
        });

        FloatingActionButton btnSetting= findViewById(R.id.chatSettingActivityAddButton);
        btnSetting.setOnClickListener(view->{
            Intent in =new Intent(this, Setting1Activity.class);
            in.putExtra("id",id);
            in.putExtra("server",server);
            startActivity(in);
        });

    }

    @Override
    protected void onResume(){
        String g=idUserDao.index().get(0).getServer();
        if(!idUserDao.index().get(0).getServer().equals("localhost:1234") && !idUserDao.index().get(0).getServer().equals("10.0.2.2:1234")){
           dialogB= new AlertDialog.Builder(this);
           final View pop=getLayoutInflater().inflate(R.layout.activity_pop_up,null);
           dialogB.setView(pop);
           dialog=dialogB.create();
           dialog.show();
           Button bt_yes = (Button)dialog.findViewById(R.id.changeTheServerAfterError);
            bt_yes.setOnClickListener(view->{
                dialog.dismiss();
                Intent i =new Intent(this, Setting1Activity.class);
                startActivity(i);

            });
        }
            super.onResume();
//            Intent i = new Intent(this, ChatActivity.class);
//            startActivity(i);
            adapter.setContacts(contactDao.index());
            lstContacts.setAdapter(adapter);


    }
public void setToken(String id,String token){
    ContactAPI contactAPI = new ContactAPI(idUserDao.index().get(0).getServer());
    ContactWebServiceAPI contactWebServiceAPI = contactAPI.getContactWebServiceAPI();

    Call<Void> call =   contactWebServiceAPI.postToken(new TokenToId(id,token));
    call.enqueue(new Callback<Void>() {
        @Override
        public void onResponse( Call<Void> call, Response<Void> response) {
            //String s = response.body();
            boolean isSuccessful = response.isSuccessful();
            if (isSuccessful) {

            }
            else {
                TextView text = findViewById(R.id.addContactErrorMessage);
                text.setText(R.string.invitation_failed);
            }
        }

        @Override
        public void onFailure( Call<Void> call,  Throwable t) {
            TextView text= findViewById(R.id.addContactErrorMessage);
            text.setText(R.string.invitation_failed);
        }
    });

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