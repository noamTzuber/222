package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.loginactivity.API.InvitationsMessage;
import com.example.loginactivity.API.UserAPI;
import com.example.loginactivity.API.UserWebServiceAPI;
import com.example.loginactivity.myObjects.Contact;
import com.example.loginactivity.myObjects.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button registerButton = findViewById(R.id.RegisterRegisterButton);
        registerButton.setOnClickListener(v -> {
            String problemsInForm = "";
            problemsInForm = checkForm();
            TextView errorMessage = (TextView) findViewById(R.id.RegisterErrorMessage);

            if (problemsInForm.equals("")) {
                errorMessage.setText("");

                UserAPI userAPI = new UserAPI();
                UserWebServiceAPI userWebServiceAPI = userAPI.getUserWebServiceAPI();
                getAllUsers(userWebServiceAPI);
            } else {
                errorMessage.setText(problemsInForm);
            }
        });
    }

    String checkForm() {
        String errors = "";
        TextView usernameTV = findViewById(R.id.RegisterUsername);
        String username = usernameTV.getText().toString();
        if (username.equals("")) {
            errors += "please enter Username\n";
        } else if (!username.matches("[a-zA-Z]*$")) {
            errors += "Use only English and numbers in Username\n";
        }

        TextView nicknameTV = findViewById(R.id.RegisterNickname);
        String nickname = nicknameTV.getText().toString();

        if (nickname.equals("")) {
            errors += "please enter Nickname\n";
        } else if (!nickname.matches("[a-zA-Z]*$")) {
            errors += "Use only English and numbers in Nickname\n";
        } else if (nickname.length() > 8) {
            errors += "Use only 8 characters in Nickname\n";
        }

        TextView passwordTV = findViewById(R.id.RegisterPassword);
        String password = passwordTV.getText().toString();

        if (password.length() < 8) {
            errors += "Use 8 characters or more for your password\n";
        } else {
            if (password.matches("[0-9]*$"))
                errors += "Use numbers for your password\n";
            if (password.matches("[a-zA-Z]*$"))
                errors += "Use letters for your password\n";
            if (!password.matches("[a-zA-Z0-9]*$"))
                errors += "Use only English and numbers for your password\n";
        }

        TextView passwordConfirmTV = findViewById(R.id.RegisterConfirmPassword);
        String passwordConfirm = passwordConfirmTV.getText().toString();

        if (!password.matches(passwordConfirm)) {
            errors += "The confirm password is not match\n";
        }

        return errors;
    }

    public void startIntent(String id,String server){
        Intent i = new Intent(this, ChatActivity.class);
        i.putExtra(("id"),id);
        i.putExtra(("server"),server);
        startActivity(i);
    }

    public void getAllUsers( UserWebServiceAPI userWebServiceAPI) {
        Call<List<User>> call = userWebServiceAPI.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse( Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                TextView usernameTV = findViewById(R.id.RegisterUsername);
                String username = usernameTV.getText().toString();
                boolean foundUser = false;
                for(int i = 0 ; i < users.size(); i ++){
                    if(username.equals(users.get(i).getId())){
                        foundUser = true;
                        TextView errorMessage = (TextView) findViewById(R.id.RegisterErrorMessage);
                        errorMessage.setText(R.string.username_already_exist);
                    }
                }
                if(!foundUser) {
                    TextView nicknameTV = findViewById(R.id.RegisterNickname);
                    String nickname = nicknameTV.getText().toString();
                    TextView passwordTV = findViewById(R.id.RegisterPassword);
                    String password = passwordTV.getText().toString();
                    List<Contact> contacts = new ArrayList<>();
                    postUser(userWebServiceAPI,new User(username,nickname
                    ,password,"10.0.2.2:1234",contacts));

               ///
                    //// startIntent();
                }
            }
            @Override
            public void onFailure( Call<List<User>> call,  Throwable t) {
                TextView errorMessage = (TextView) findViewById(R.id.RegisterErrorMessage);
                errorMessage.setText(R.string.Server_error);
            }
        });
    }
    public void postUser(UserWebServiceAPI userWebServiceAPI,User user){
        Call<Void> call =   userWebServiceAPI.createUser(user);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse( Call<Void> call, Response<Void> response) {
                //String s = response.body();
                boolean isSuccessful = response.isSuccessful();
                if (isSuccessful) {
                    startIntent(user.getId(),user.getServer());
                }
                TextView text= findViewById(R.id.addContactErrorMessage);
                text.setText(R.string.invitation_failed);

            }

            @Override
            public void onFailure( Call<Void> call,  Throwable t) {
                TextView text= findViewById(R.id.addContactErrorMessage);
                text.setText(R.string.invitation_failed);
            }
        });
    }
}
