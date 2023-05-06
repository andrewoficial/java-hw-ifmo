package kurs002.menu;

import kurs002.Command;

public class Save implements Command {
    @Override
    public void execute() {
        System.out.println("Заглушка выбора слотов сохранения (результат будет перезаписан!):");
        System.out.println("x. 05.05.2005 - Player One");
        System.out.println("x. 06.06.2006 - Player Two");
        System.out.println("x. 07.07.2007 - Player One");
        System.out.println("Или введите своё имя (дата добавится автоматически)");
    }
}
