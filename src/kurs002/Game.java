package kurs002;
import kurs002.menu.Exit;
import kurs002.menu.Menu;
import kurs002.menu.Start;
//import kurs002.menu.Save;

import java.util.*;

public class Game {
    private final Card startCard;
    private Card currentCard;
    private Scanner sc = new Scanner(System.in);
    private Menu menu;

    public Game(Card card, Menu menu){
        this.currentCard = card;
        this.startCard = card;
        this.menu = menu;
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
        // Как, например запустить класс Save или Load, если меню пункты меню нигде не хранят информацию о том что
        //они запускают.
        // Получается что нужно везде разбирать ответ от пользователя?
        System.out.println("Run execute");
        if(userAns == 1){
            playCards(currentCard);
        }else if(userAns == 3){
            System.exit(0);
        }
        //menu.getByNumber(userAns).execute(this);
    }

    public void playCards(Card card){
        if(card != null)
            currentCard = card;
        //Как мне менять меню в зависимости от текущего положения в игре,
        //если у MenuItem private final String name???
        menu.getByNumber(1).set
        int numberOfItems = menu.renderMenu(card.getTitle());
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
                menu.getByName("Сохранить").setHidden(false);
                menu.getByName("Вернуться в игру").setHidden(false);
                start();
        }
    }

    public Card getCurrentCard(){
        if(currentCard != null){
            return currentCard;
        }else{
            return startCard;
        }
    }
}

