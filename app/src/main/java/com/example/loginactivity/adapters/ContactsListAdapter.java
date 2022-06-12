package com.example.loginactivity.adapters;

import static com.example.loginactivity.MyApplication.context;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginactivity.ChatActivity;
import com.example.loginactivity.ContactActivity;
import com.example.loginactivity.R;
import com.example.loginactivity.RegisterActivity;
import com.example.loginactivity.myObjects.Contact;

import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {
    private final LayoutInflater mInflater;
    private List<Contact> contacts;
    private Context context;
    private mClickListener mClickListener;

    public ContactsListAdapter(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    public ContactsListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context=context;
    }


    class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvLast;
        private final TextView tvLastTime;


        private ContactViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvLast = itemView.findViewById(R.id.tvLast);
            tvLastTime = itemView.findViewById(R.id.tvLastTime);
        }
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.contact_layout, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsListAdapter.ContactViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (contacts != null) {
            final Contact current = contacts.get(position);
            holder.tvName.setText(current.getName());
            holder.tvLast.setText(current.getLast());
            holder.tvLastTime.setText(current.getLastDate());
            Contact i = contacts.get(position);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,ContactActivity.class);
                Contact current = contacts.get(position);
                i.putExtra("id",current.getId() );
                i.putExtra("name", current.getId());

                context.startActivity(i);

            }
        });
    }


    public void setContacts(List<Contact> s) {
        contacts = s;

    }

    @Override
    public int getItemCount() {
        if (contacts != null) {
            return contacts.size();
        } else return 0;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public interface mClickListener {
        public void mClick(View v, int position);
    }

}
