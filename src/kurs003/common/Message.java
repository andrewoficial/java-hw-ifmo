package kurs003.common;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message extends Package implements Serializable {
    private String text;
    private String identify;
    private LocalDateTime sent;

    public Message(String text, String identify) {
        this.text = text;
        this.identify = identify;
    }

    public void setSent(LocalDateTime sent) {
        this.sent = sent;
    }

    public String getText() {
        return text;
    }

    public String getKey(){
        return identify;
    }
    public LocalDateTime getSent() {
        return sent;
    }
}