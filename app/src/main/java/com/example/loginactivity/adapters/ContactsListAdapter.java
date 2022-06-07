package com.example.loginactivity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginactivity.R;
import com.example.loginactivity.myObjects.Contact;

import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {

    public ContactsListAdapter(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    public ContactsListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
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

    private final LayoutInflater mInflater;
    private List<Contact> contacts;


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.contact_layout, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsListAdapter.ContactViewHolder holder, int position) {
        if (contacts != null) {
            final Contact current = contacts.get(position);
            holder.tvName.setText(current.getName());
            holder.tvLast.setText(current.getLast());
            holder.tvLastTime.setText(current.getLastDate());
        }
    }

    public void setContacts(List<Contact> s){
        contacts = s;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        if(contacts != null){
            return contacts.size();
        }
        else return 0;
    }
    public List<Contact> getContacts(){
        return contacts;
    }

}
