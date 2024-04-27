package uta.cse3310;

public class Message {
    private String sender;
    private String content;

    public Message() {
        // Default constructor
        String content;
    }

    public Message(Player sender, String content) {
        
        this.sender = sender.getName();
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
