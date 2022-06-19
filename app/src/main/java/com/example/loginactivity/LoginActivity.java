package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.loginactivity.API.ContactAPI;
import com.example.loginactivity.API.ContactWebServiceAPI;
import com.example.loginactivity.API.InvitationsMessage;
import com.example.loginactivity.API.UserAPI;
import com.example.loginactivity.API.UserWebServiceAPI;
import com.example.loginactivity.databinding.ActivityLoginBinding;
import com.example.loginactivity.myObjects.Contact;
import com.example.loginactivity.myObjects.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private AppDB db;
    private ContactDao contactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.LoginToRegisterButton.setOnClickListener(v -> {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        });
        binding.LoginButton.setOnClickListener(v -> {
            UserAPI userAPI = new UserAPI();
            UserWebServiceAPI userWebServiceAPI = userAPI.getUserWebServiceAPI();
            get(userWebServiceAPI);

        });


    }

    public void startIntent(String server ,String id){
        Intent i = new Intent(this, ChatActivity.class);
        i.putExtra("server",server);
                    i.putExtra("id",id);
                    startActivity(i);
//        ContactAPI contactAPI = new ContactAPI();
//        ContactWebServiceAPI contactWebServiceAPI = contactAPI.getContactWebServiceAPI();
//        getAllContacts(id,contactWebServiceAPI,server,i);

    }


    public void get( UserWebServiceAPI userWebServiceAPI) {
        Call<List<User>> call = userWebServiceAPI.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse( Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                TextView usernameTV = findViewById(R.id.LoginUsername);
                String username = usernameTV.getText().toString();

                TextView passwordTV = findViewById(R.id.LoginPassword);
                String password = passwordTV.getText().toString();

                boolean foundUser = false;
                for(int i = 0 ; i < users.size(); i ++){
                    String s1 = users.get(i).getId();
                    String s2 = users.get(i).getPassword();
                    if(username.equals(s1) && password.equals(s2)){
                        foundUser = true;
                        startIntent(users.get(i).getServer(),users.get(i).getId());
                    }
                }
                //itay
                if(!foundUser) {
                    TextView errorMessage = (TextView) findViewById(R.id.loginErrorMessage);
                    errorMessage.setText("Username or Password incorrect");
                }
            }
            @Override
            public void onFailure( Call<List<User>> call,  Throwable t) {

            }
        });
    }
}