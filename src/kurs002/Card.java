package kurs002;

import java.io.Serializable;

public class Card implements Serializable {
    private Card prev;
    private Card next;
    private String title;
    private String content;

    public Card(String title, String content){
        if(title == null || content == null){
            throw new IllegalArgumentException("Wrong content");
        }
        this.title = title;
        this.content = content;
    }
    public void setNext(Card card){
        this.next = card;
    }
    void setPrev(Card card){
        this.prev = card;
    }
    public Card getNext(){
        return next;
    }
    public Card getPrev(){
        return prev;
    }
    public String getContent(){
        return content;
    }
    public String getTitle(){
        return title;
    }


    @Override
    public String toString() {
        return "Card{" +
                "prev=" + prev +
                ", next=" + next +
                ", title='" + title + '\'' +
                '}';
    }
}
