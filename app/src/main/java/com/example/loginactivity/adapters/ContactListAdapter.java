package com.example.loginactivity.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginactivity.R;
import com.example.loginactivity.myObjects.Contact;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder>{

    class ContactViewHolder extends RecyclerView.ViewHolder {
//        private final TextView tvId;
        private final TextView tvName;
//        private final TextView tvServer;
        private final TextView tvLast;
        private final TextView tvLastTime;

        private ContactsViewHolder(View itemView) {
            super(itemView);
//            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
//            tvServer = itemView.findViewById(R.id.tvServer);
            tvLast = itemView.findViewById(R.id.tvLast);
            tvLastTime = itemView.findViewById(R.id.tvLastTime);
        }

    }

    private final LayoutInflater mInflater;
    private List<Contact> contacts;

    public ContactListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.contact_layout, parent, false);
        return new ContactViewHolder(itemView);
    }

//    @Override
//    public void onBindViewHolder(@NonNull ContactsListAdapter.ContactViewHolder holder, int position) {
//        if(contacts != null){
//            final Contact current = contacts.get(position);
////            holder.tvId.setText(current.getId());
//            holder.tvName.setText(current.getName());
////            holder.tvServer.setText(current.getServer());
//            holder.tvLast.setText(current.getLast());
//            holder.tvLastTime.setText(current.getLastDate());
//        }
//    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        if(contacts != null){
            final Contact current = contacts.get(position);
//            holder.tvId.setText(current.getId());
            holder.tvName.setText(current.getName());
//            holder.tvServer.setText(current.getServer());
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
