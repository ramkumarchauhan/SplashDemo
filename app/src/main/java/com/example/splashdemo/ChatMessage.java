package com.example.splashdemo;

//public class ChatMessage {
//    private String message;
//    private long timestamp;
//
//    public ChatMessage(String message, long timestamp) {
//        this.message = message;
//        this.timestamp = timestamp;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public long getTimestamp() {
//        return timestamp;
//    }
//}

public class ChatMessage {
    private String text;
    private String sender;
    private long timestamp;

    public ChatMessage() {
        // Default constructor required for Firebase Realtime Database
    }

    public ChatMessage(String text, String sender, long timestamp) {
        this.text = text;
        this.sender = sender;
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public String getSender() {
        return sender;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
