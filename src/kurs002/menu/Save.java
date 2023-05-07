package kurs002.menu;

import kurs002.Card;
import kurs002.Command;
import kurs002.GameState;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Save implements Command {
    GameState state = null;
    public Save(GameState state){
        this.state = state;
    }

       private void writeSave(GameState state, String position){
           HashMap<String, Card> myHashMap = new HashMap<>();
           myHashMap.put(position, state.getState());

           try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("hashmap.ser"))) {
               outputStream.writeObject(myHashMap);
               System.out.println("HashMap has been written to the file.");
           } catch (IOException e) {
               e.printStackTrace();
           }
    }
    private boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @Override
    public void execute() {
        MenuInvoker saveMenu = new MenuInvoker();
        Scanner scanner = new Scanner(System.in);
        while(true){
            saveMenu.menu.clear();
            saveMenu.menu.add("Меню сохранения ^___^ \n Укажите номер или введите новое имя");
            saveMenu.menu.add("x. 05.05.2005 - Player One");
            saveMenu.menu.add("x. 06.06.2006 - Player Two");
            saveMenu.menu.add("x. 07.07.2007 - Player One");
            saveMenu.menu.add("Выход из сохранений");
            saveMenu.renderMenu();


            String input = scanner.nextLine();
            if(input.equals("4")){
                break;
            }else{
                if(state == null){
                    System.out.println("Сохранение не найдено в памяти.");
                    break;
                }
                if(isDigit(input)){
                    writeSave(state, input);
                    break;
                }else{
                    writeSave(state, input);
                    break;
                }
            }

        }


    }
}
