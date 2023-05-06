package kurs002;

public class Card {
    private Card prev;
    private Card next;
    private String title;
    private String content;

    public Card (String title, String content){
        if(title == null || content == null){
            throw new IllegalArgumentException("Wrong content");
        }
        this.title = title;
        this.content = content;
    }
    public void setNext(Card card){
        next = card;
    }

    public void setPrev(Card card){
        prev = card;
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

    public void printContent(){
        System.out.println(content);
    }

    public String getTitle(){
        return title;
    }

    public void printTitle(){
        System.out.println(title);
    }
}
