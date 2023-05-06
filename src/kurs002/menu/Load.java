package kurs002.menu;

import kurs002.Command;

public class Load implements Command {
    @Override
    public void execute() {
        System.out.println("Заглушка выбора слотов сохранения:");
        System.out.println("x. 05.05.2005 - Player One");
        System.out.println("x. 06.06.2006 - Player Two");
        System.out.println("x. 07.07.2007 - Player One");
    }
}
