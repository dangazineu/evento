package dev.evento.examples.greeting;

public class Respond {

    private String to;
    private Message message;

    public String getTo() {
        return to;
    }

    public Message getMessage() {
        return message;
    }

    public enum Message {
        HELLO,
        GOODBYE
    }
}
