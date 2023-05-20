package kurs002.menu;

import kurs002.Game;

public class Exit extends MenuItem {
    public Exit(Game game) {
        super("Выход из игры", game.getMenu().getSize()+1);
    }
    public Exit(Game game, Menu menu) {
        super("Выход из игры", menu.getSize()+1);
    }
    @Override
    public void execute(Game game) {
        System.out.println("Спасибо за игру!");
        System.exit(0);
    }
}
