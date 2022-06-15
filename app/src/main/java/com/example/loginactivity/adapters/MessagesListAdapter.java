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

import androidx.recyclerview.widget.RecyclerView;

import com.example.loginactivity.ChatActivity;
import com.example.loginactivity.ContactActivity;
import com.example.loginactivity.R;
import com.example.loginactivity.RegisterActivity;
import com.example.loginactivity.myObjects.Message;

import java.util.List;

public class MessagesListAdapter extends RecyclerView.Adapter<MessagesListAdapter.MessageViewHolder> {
    private final LayoutInflater mInflater;
    private List<Message> messages;
    private Context context;
    private String userId;
    private mClickListener mClickListener;

    public MessagesListAdapter(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    public MessagesListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context=context;

    }



    class MessageViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvContentMessage;
        private final TextView tvCreatedMessage;



        private MessageViewHolder(View itemView) {
            super(itemView);
            tvContentMessage = itemView.findViewById(R.id.tvContentMessage);
            tvCreatedMessage = itemView.findViewById(R.id.tvCreatedMessage);
//            tvSent= itemView.findViewById(R.id.tvSent);
        }

    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==1){
        View itemView = mInflater.inflate(R.layout.message_outgoing_layout ,parent, false);
        return new MessageViewHolder(itemView);
        }
        else{
            View itemView = mInflater.inflate(R.layout.message_incoming_layout, parent, false);
            return new MessageViewHolder(itemView);
        }
    }
    @Override
    public int getItemViewType(int position) {
        int viewType = 1;
        Message i = messages.get(position);//Default is 1
        if (!i.isSent())
            viewType = 0;
        return viewType;
    }
    public void setUserId(String s) {
        userId = s;

    }
    public String getUserId() {
        return this.userId;

    }

    @Override
    public void onBindViewHolder(@NonNull MessagesListAdapter.MessageViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (messages != null) {
            final Message current = messages.get(position);
            holder.tvContentMessage.setText(current.getContent());
            holder.tvCreatedMessage.setText(current.getCreated().substring(11, 16));
        }
    }


    public void setMessages(List<Message> s) {
        messages = s;
//        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (messages != null) {
            return messages.size();
        } else return 0;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public interface mClickListener {
        public void mClick(View v, int position);
    }

}
