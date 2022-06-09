package com.example.loginactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.loginactivity.API.InvitationsAPI;
import com.example.loginactivity.API.InvitationsMessage;
import com.example.loginactivity.API.InvitationsWebServiceAPI;
import com.example.loginactivity.API.UserAPI;
import com.example.loginactivity.API.UserWebServiceAPI;
import com.example.loginactivity.myObjects.Contact;
import com.example.loginactivity.myObjects.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            // contact validation
            Intent intent = getIntent();
            String id=intent.getStringExtra("id");

            InvitationsAPI invitationsAPI=new InvitationsAPI(server.getText().toString());
            InvitationsWebServiceAPI invitationsWebServiceAPI = invitationsAPI.getInvitationsWebServiceAPI();
            invite(invitationsWebServiceAPI,id,name.getText().toString(),nickName.getText().toString(), server.getText().toString());

        });
          }
    public void invite(@NonNull InvitationsWebServiceAPI invitationsWebServiceAPI, String id , String name,String nickName, String server) {

        Call<Void> call =   invitationsWebServiceAPI.inviteContact(new InvitationsMessage(id,name,server),id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse( Call<Void> call, Response<Void> response) {
                //String s = response.body();
                boolean isSuccessful = response.isSuccessful();
                if (isSuccessful) {
                    Contact contact = new Contact(name, nickName,
                            server, "", "");
                    contactDao.insert(contact);
                    ///post
                    finish();
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
}