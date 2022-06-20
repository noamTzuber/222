package com.example.loginactivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.loginactivity.API.ContactAPI;
import com.example.loginactivity.API.ContactWebServiceAPI;
import com.example.loginactivity.API.MessageAPI;
import com.example.loginactivity.API.MessageForServer;
import com.example.loginactivity.API.MessageToTransfer;
import com.example.loginactivity.API.MessageTransferAPI;
import com.example.loginactivity.API.MessageTransferWebServiceAPI;
import com.example.loginactivity.API.MessageWebServiceAPI;
import com.example.loginactivity.adapters.ContactsListAdapter;
import com.example.loginactivity.adapters.MessagesListAdapter;

import com.example.loginactivity.myObjects.Contact;
import com.example.loginactivity.myObjects.IdUser;
import com.example.loginactivity.myObjects.Message;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactActivity extends AppCompatActivity {


    private MessagesListAdapter adapter;
    private AppDBMessage dbMessage;
    private MessageDao messageDao;
    RecyclerView lstMessages;
    private AppDBIdUser dbUser;
    private IdUserDao idUserDao;
    private ContactDao contactDao;
    private AppDB dbContact;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Intent intent = getIntent();
        String name = intent.getStringExtra("nameContact");
        String idContact = intent.getStringExtra("idContact");
        String serverContact = intent.getStringExtra("serverContact");
        TextView t = findViewById(R.id.contactActivity_contactName);
        t.setText(name);

        dbMessage = Room.databaseBuilder(getApplicationContext(), AppDBMessage.class, "roomDBMessage.db")
                .fallbackToDestructiveMigration().allowMainThreadQueries()
                .build();
        messageDao = dbMessage.messageDao();

        dbContact = Room.databaseBuilder(getApplicationContext(), AppDB.class, "roomDB.db")
                .fallbackToDestructiveMigration().allowMainThreadQueries()
                .build();
        contactDao = dbContact.contactDao();

        dbUser = Room.databaseBuilder(getApplicationContext(), AppDBIdUser.class, "roomDBIdUser.db")
                .fallbackToDestructiveMigration().allowMainThreadQueries()
                .build();
        idUserDao = dbUser.idUserDao();
        String idUser=idUserDao.index().get(0).getId();
        messageDao.deleteAll();



        lstMessages = findViewById(R.id.lstMessages);
        adapter = new MessagesListAdapter(this);
        lstMessages.setAdapter(adapter);
        lstMessages.setLayoutManager(new LinearLayoutManager(this));

        MessageAPI messageAPI = new MessageAPI(idUserDao.index().get(0).getServer()
        );
        MessageWebServiceAPI messageWebServiceAPI = messageAPI.getMessageWebServiceAPI();
        getAllMessages(lstMessages,messageWebServiceAPI, idUser, idContact);




        FloatingActionButton btnAdd= findViewById(R.id.ContactActivitySendButton);
        btnAdd.setOnClickListener(view->{
         TextView tvInput=findViewById(R.id.ContactActivityInput);
         String input=tvInput.getText().toString();
         tvInput.setText("");
         String time=LocalDateTime.now(ZoneId.of("Asia/Jerusalem")).toString();

         Message message=new Message(0,input, time,true);

         List<Message> listM=messageDao.index();
         listM.add(message);
         messageDao.insert(message);
         adapter.setMessages(listM);
         lstMessages.setAdapter(adapter);
//            transferMessage(serverContact,time,messageWebServiceAPI,new MessageForServer(idContact,input),idUser);
            transferMessage(time,serverContact,idUser,idContact,input);
        });


    }
    @Override
    public void onStop(){
        super.onStop();
        finish();
    }

    public void transferMessage(String time,String serverContact,String connectedId,String contact,String content){

        MessageTransferAPI messageTransferAPI = new MessageTransferAPI(serverContact);
        MessageTransferWebServiceAPI  messageTransferWebServiceAPI = messageTransferAPI.getMessageTransferServiceAPI();

        Call<Void> call =   messageTransferWebServiceAPI.transferMessage(new MessageToTransfer(connectedId,contact,content));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse( Call<Void> call, Response<Void> response) {
                //String s = response.body();

                boolean isSuccessful = response.isSuccessful();
                if (isSuccessful) {
                    Contact c = contactDao.get(contact);
                    contactDao.delete(c);
                    c.setLastdate(time);
                    c.setLast(content);
                    contactDao.insert(c);
                    }
                else {
                }
            }
            @Override
            public void onFailure( Call<Void> call,  Throwable t) {

            }
        });
    }

    public void getAllMessages(RecyclerView lstMessages,MessageWebServiceAPI messageWebServiceAPI, String id, String id2) {

        Call<List<Message>> call = messageWebServiceAPI.getMessages(id2, id);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                //String s = response.body();
                boolean isSuccessful = response.isSuccessful();
                if (isSuccessful) {
                    int i=0;
                    messageDao.insertAllMessages(response.body());
                    adapter.setMessages(response.body());
                    lstMessages.setAdapter(adapter);

                    //livaData - changing the room contact to server DB contacts
                } else {
                    TextView text = findViewById(R.id.addContactErrorMessage);
                    text.setText(R.string.invitation_failed);
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });
    }
}
