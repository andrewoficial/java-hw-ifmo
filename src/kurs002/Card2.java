package kurs002;

import java.io.Serializable;

public class Card2 implements Serializable {
    private Card2 prev;
    private Card2 next;
    private String title;
    private String content;

    public Card2(String title, String content){
        if(title == null || content == null){
            throw new IllegalArgumentException("Wrong content");
        }
        this.title = title;
        this.content = content;
    }
    public void setNext(Card2 card){
        this.next = card;
    }
    void setPrev(Card2 card){
        this.prev = card;
    }
    public Card2 getNext(){
        return next;
    }
    public Card2 getPrev(){
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
                "type='" + "Card'" +
                "title='" + title + '\'' +
                '}';
    }


}
