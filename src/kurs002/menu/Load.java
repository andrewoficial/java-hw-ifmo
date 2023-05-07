package kurs002.menu;

import kurs002.Card;
import kurs002.Command;
import kurs002.GameState;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Load implements Command {
    HashMap<String, Card> myHashMap;
    public Map <String, Card> readSave(){
        HashMap<String, Card> myHashMap = null;

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("hashmap.ser"))) {
            myHashMap = (HashMap<String, Card>) inputStream.readObject();
            System.out.println("HashMap has been read from the file.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (myHashMap != null) {
            for (String key : myHashMap.keySet()) {
                System.out.println(key + ": " + myHashMap.get(key));
            }
        }
        // Выводим содержимое HashMap
        System.out.println(myHashMap);
        return myHashMap;
    }
    @Override
    public void execute() {
        MenuInvoker loadMenu = new MenuInvoker();
        Scanner scanner = new Scanner(System.in);
        while(true){
            loadMenu.menu.clear();
            loadMenu.menu.add("Заглушка выбора слотов сохранения:");
            loadMenu.menu.add("...1");
            loadMenu.menu.add("...2");
            loadMenu.menu.add("...3");
            loadMenu.menu.add("Выход из сохранений");
            loadMenu.renderMenu();


            String input = scanner.nextLine();
            if(input.equals("1")){
                //new Start(new GameState(null)).execute();
            }else if(input.equals("2")){
                readSave();
            }
            if(input.equals("4")){
                break;
            }
            /*
            new Start(gameState)
             */

        }
    }
}
