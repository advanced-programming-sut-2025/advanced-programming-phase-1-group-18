package io.github.group18.Model;



public class Message {
    private Player sender;
    private String content;

    public Message(Player sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public Player getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }
}
