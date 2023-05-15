package kurs002.menu;

import kurs002.Game;

public class Exit extends MenuItem {
    public Exit(String name, boolean hidden) {
        super(name, hidden);
    }

    @Override
    public void execute(Game game) {
        System.out.println("Спасибо за игру!");
        System.exit(0);
    }
}
