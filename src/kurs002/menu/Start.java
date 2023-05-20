package kurs002.menu;


import kurs002.Card;
import kurs002.Game;


import java.util.Scanner;

//Класс начала игры
public class Start extends MenuItem {

    public Start(String name){
        super("Начать игру", 1);
    }

    @Override
    public void execute(Game game) {
        game.playCards(game.getCurrentCard());
    }

}
