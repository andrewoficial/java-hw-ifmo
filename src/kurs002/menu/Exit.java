package kurs002.menu;

import kurs002.Command;

public class Exit implements Command {
    @Override
    public void execute() {
        System.out.println("Спасибо за игру!");
        System.exit(0);
    }
}
