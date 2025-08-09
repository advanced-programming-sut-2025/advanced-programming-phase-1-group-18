package io.github.group18.Model;

public class ChatMessage {
    String senderUsername;
    String message;
    boolean privateChat;
    String receiverUsername;

    public ChatMessage(String senderUsername, String message, boolean privateChat, String receiverUsername) {
        this.senderUsername = senderUsername;
        this.message = message;
        this.privateChat = privateChat;
        this.receiverUsername = receiverUsername;
    }

    public ChatMessage(String senderUsername, String message, boolean privateChat) {
        this.senderUsername = senderUsername;
        this.message = message;
        this.privateChat = privateChat;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isPrivateChat() {
        return privateChat;
    }

    public void setPrivateChat(boolean privateChat) {
        this.privateChat = privateChat;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }
}
