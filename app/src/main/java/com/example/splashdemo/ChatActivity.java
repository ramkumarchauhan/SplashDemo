package com.example.splashdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;

//public class ChatActivity extends AppCompatActivity {
//
//    private ListView listViewChat;
//    private EditText editTextMessage;
//    private Button buttonSend;
//    private ChatAdapter chatAdapter;
//    private List<ChatMessage> chatMessageList;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chat);
//
//        listViewChat = findViewById(R.id.layoutMessage);
//        editTextMessage = findViewById(R.id.editTextMessage);
//        buttonSend = findViewById(R.id.buttonSend);
//
//        chatMessageList = new ArrayList<>();
//        chatAdapter = new ChatAdapter(this, chatMessageList);
//        listViewChat.setAdapter(chatAdapter);
//
//        buttonSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sendMessage();
//            }
//        });
//    }
//
//    private void sendMessage() {
//        String messageText = editTextMessage.getText().toString().trim();
//        if (!messageText.isEmpty()) {
//            ChatMessage message = new ChatMessage(messageText, System.currentTimeMillis());
//            chatMessageList.add(message);
//            chatAdapter.notifyDataSetChanged();
//            listViewChat.setSelection(chatAdapter.getCount() - 1); // Scroll to the latest message
//            editTextMessage.setText(""); // Clear the input field
//        }
//    }
//}


public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerViewMessages;
    private EditText editTextMessage;
    private Button buttonSend;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Initialize views and adapters
        recyclerViewMessages = findViewById(R.id.recyclerViewMessages);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);

        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(messageList, getCurrentUserId());

        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMessages.setAdapter(chatAdapter);

        // Set up Firebase Realtime Database reference for messages
        DatabaseReference messagesRef = FirebaseDatabase.getInstance().getReference("messages");

        // Attach a listener to receive new messages
        messagesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ChatMessage message = dataSnapshot.getValue(ChatMessage.class);
                messageList.add(message);
                chatAdapter.notifyDataSetChanged();
                // Scroll to the latest message
                recyclerViewMessages.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database errors here
                Log.e("Firebase", "Database error: " + databaseError.getMessage());
            }

        });

        // Set up click listener to send messages
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = editTextMessage.getText().toString().trim();
                if (!messageText.isEmpty()) {
                    long timestamp = System.currentTimeMillis();
                    ChatMessage message = new ChatMessage(messageText, getCurrentUserId(), timestamp);
                    messagesRef.push().setValue(message);
                    editTextMessage.setText("");
                }
            }
        });
    }

    private String getCurrentUserId() {
        // Implement logic to get the current user's ID
        return "user123"; // Replace with actual user ID retrieval
    }
}
