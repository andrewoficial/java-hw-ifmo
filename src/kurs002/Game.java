package kurs002;
import kurs002.menu.*;
//import kurs002.menu.Save;

import java.util.*;

public class Game extends MenuItem {
    private final Card startCard;
    private Card currentCard;
    private Scanner sc = new Scanner(System.in);
    private Menu menu;

    public Game(Card card, Menu menu){
        super("Начать игру", 1);
        this.currentCard = card;
        this.startCard = card;
        this.menu = menu;
    }

    public Game(Game game){
        //Создаются пункты....
        super("Начать игру", 1);
        this.currentCard = game.getCurrentCard();
        this.startCard = game.getCurrentCard();
        this.menu = game.getMenu();
    }


    public void start(){
        int numberOfItems = menu.renderMenu("Главное меню");
        String userInp = null;
        int userAns = 0;
        do {
            userInp = sc.nextLine();
            userAns = Utils.getDigit(userInp);
        }while(userAns <= 0 || userAns > numberOfItems);
        //Получили ответ от пользователя
        menu.getByNumber(userAns).execute(this);
    }

    public void playCards(Card card){
        if(card != null)
            currentCard = card;

        if(menu.getByName("Вернутся к игре") != null){
            //Убираю пункты "Сохранить" и "Вернуться....", пользователь уже играет.
            //System.out.println("Do short menu");
            menu.removeCommand(menu.getByName("Сохранить"));
            menu.removeCommand(menu.getByName("Вернутся к игре"));
        }


        if(card.getNext() == null || card.getPrev() == null){
            /*
            //DBG
            if(card.getNext() == null){
                System.out.println("card.getNext() == null");
            }else if(card.getPrev() == null){
                System.out.println("card.getPrev() == null");
            }else{
                System.out.println("DUNNO!");
            }
            //System.out.println(card.toString());
            //endDBG

             */
            Utils.myPrint(card.getContent());
            backToMenu();
        }

        Utils.myPrint(card.getContent());
        menu.getByNumber(1).setName(card.getNext().getTitle());
        menu.getByNumber(2).setName(card.getPrev().getTitle());
        menu.renderMenu(card.getTitle());
        String userInp = null;
        int userAns = 0;
        do {
            userInp = sc.nextLine();
            userAns = Utils.getDigit(userInp);
        }while(userAns <= 0 || userAns > 3);

        switch (userAns){
            case 1:
                playCards(currentCard.getNext());
                break;
            case 2:
                playCards(currentCard.getPrev());
                break;
            case 3:
                backToMenu();
        }
    }

    private void backToMenu(){
        menu.getByNumber(1).setName("Начать игру");
        menu.getByNumber(2).setName("Загрузить игру");
        menu.insertCommand(new Save(this), menu.getByName("Загрузить игру").getNumber()+1);
        //menu.addCommand(new ReturnToGame(this)); //Еще не дописано
        start();
    }
    public Card getCurrentCard(){
        if(currentCard != null){
            return currentCard;
        }else{
            return startCard;
        }
    }

    public Menu getMenu(){
        return menu;
    }

    @Override
    public void execute(Game game) {
        System.out.println("Run game via execute");
        game.playCards(game.getCurrentCard());
    }


    @Override
    public String toString() {
        return "Game{" +
                "currentCard=" + currentCard.getTitle() +
                ", menu=" + menu +
                '}';
    }
}

