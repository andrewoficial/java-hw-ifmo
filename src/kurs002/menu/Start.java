package kurs002.menu;


import kurs002.Card;
import kurs002.Game;


import java.util.Scanner;

//Класс начала игры
public class Start extends MenuItem {

    public Start(String name){
        super(name, false);
    }

    @Override
    public void execute(Game game) {
        game.playCards(game.getCurrentCard());
    }

}
