package kurs002.menu;

import kurs002.Command;

public class Save implements Command {
    @Override
    public void execute() {
        System.out.println("Сохранение игры");
    }
}
