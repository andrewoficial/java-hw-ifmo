package kurs002.menu;

import kurs002.Command;

public class Exit implements Command {
    @Override
    public void execute() {
        System.out.println("Выход из игры");
    }
}
