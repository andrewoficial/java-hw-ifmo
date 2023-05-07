package kurs002.menu;

import kurs002.Command;
import kurs002.GameState;

import java.util.Scanner;

public class Save implements Command {
    GameState state = null;
    public Save(GameState state){
        this.state = state;
    }
    @Override
    public void execute() {
        MenuInvoker saveMenu = new MenuInvoker();
        Scanner scanner = new Scanner(System.in);
        while(true){
            saveMenu.menu.clear();
            saveMenu.menu.add("Заглушка выбора слотов сохранения:");
            saveMenu.menu.add("x. 05.05.2005 - Player One");
            saveMenu.menu.add("x. 06.06.2006 - Player Two");
            saveMenu.menu.add("x. 07.07.2007 - Player One");
            saveMenu.menu.add("Выход из сохранений");
            saveMenu.renderMenu();

            String input = scanner.nextLine();
            if(input.equals("4")){
                break;
            }
/*
    public void save(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("05.05.2005 - "+filename+".ser"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 */
        }


    }
}
