package com.example.splashdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

//public class ChatAdapter extends BaseAdapter {
//
//    private Context context;
//    private List<ChatMessage> chatMessages;
//    private LayoutInflater inflater;
//    private SimpleDateFormat dateFormat;
//
//    public ChatAdapter(Context context, List<ChatMessage> chatMessages) {
//        this.context = context;
//        this.chatMessages = chatMessages;
//        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
//    }
//
//    @Override
//    public int getCount() {
//        return chatMessages.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return chatMessages.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.item_message, parent, false);
//        }
//
//        TextView textViewMessage = convertView.findViewById(R.id.textViewMessage);
//        TextView textViewTime = convertView.findViewById(R.id.textViewTime);
//
//        ChatMessage message = chatMessages.get(position);
//        textViewMessage.setText(message.getMessage());
//        textViewTime.setText(dateFormat.format(message.getTimestamp()));
//
//        return convertView;
//    }
//}


public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder> {
    private List<ChatMessage> messageList;
    private String currentUser;

    public ChatAdapter(List<ChatMessage> messageList, String currentUser) {
        this.messageList = messageList;
        this.currentUser = currentUser;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        ChatMessage message = messageList.get(position);
        holder.bindMessage(message);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewMessage;

        public MessageViewHolder(View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.textViewMessage);
        }

        public void bindMessage(ChatMessage message) {
            textViewMessage.setText(message.getText());
            // Customize message appearance based on sender (currentUser or other user)
            if (message.getSender().equals(currentUser)) {
                // Current user's message
                textViewMessage.setBackgroundResource(R.drawable.bg_message_sent);
            } else {
                // Other user's message
                textViewMessage.setBackgroundResource(R.drawable.bg_message_received);
            }
        }
    }
}
