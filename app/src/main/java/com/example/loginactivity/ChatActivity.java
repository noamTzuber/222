package com.example.loginactivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import com.example.loginactivity.adapters.ContactsListAdapter;
import com.example.loginactivity.databinding.ActivityChatBinding;
import com.example.loginactivity.myObjects.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    List<String> list;
    private AppDB db;
    private ContactDao contactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "roomDB")
            .allowMainThreadQueries()
                .build();

        contactDao = db.contactDao();

        FloatingActionButton btnAdd= findViewById(R.id.chatActivityAddButton);
        btnAdd.setOnClickListener(view->{
            Intent i =new Intent(this, AddContactActivity.class);
            startActivity(i);
        });

        RecyclerView lstContacts =findViewById(R.id.lstContacts);
        final ContactsListAdapter adapter = new ContactsListAdapter(this);
        lstContacts.setAdapter(adapter);
        lstContacts.setLayoutManager(new LinearLayoutManager(this));
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("noam", "nono", "1234", "", ""));
        adapter.setContacts(contacts);
        contacts.add(new Contact("noam", "neno", "1234", "", ""));
        adapter.setContacts(contacts);
    }
}