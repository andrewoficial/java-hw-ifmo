package kurs002.menu;

import kurs002.Command;
import kurs002.GameState;

import java.util.Scanner;

public class Load implements Command {
    @Override
    public void execute() {
        MenuInvoker loadMenu = new MenuInvoker();
        Scanner scanner = new Scanner(System.in);
        while(true){
            loadMenu.menu.clear();
            loadMenu.menu.add("Заглушка выбора слотов сохранения:");
            loadMenu.menu.add("x. 05.05.2005 - Player One (begin)");
            loadMenu.menu.add("x. 06.06.2006 - Player Two");
            loadMenu.menu.add("x. 07.07.2007 - Player One");
            loadMenu.menu.add("Выход из сохранений");
            loadMenu.renderMenu();


            String input = scanner.nextLine();
            if(input.equals("1")){
                new Start(new GameState(null)).execute();
            }
            if(input.equals("4")){
                break;
            }
            /*
            new Start(gameState)
             */

            /*
    public static Card load(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            return (Card) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
             */
        }
    }
}
