package kurs002.menu;

import kurs002.Game;

public class Exit extends MenuItem {
    public Exit(Game game) {
        super("Ololo", 0, null);
        //Возможно сохранение чего-нибудь
    }

    @Override
    public void execute(Game game) {
        System.out.println("Спасибо за игру!");
        System.exit(0);
    }
}
