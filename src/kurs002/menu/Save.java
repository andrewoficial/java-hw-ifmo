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
    HashMap<String, Card> myHashMap;
       private void writeSave(GameState state, String position){
           HashMap<String, Card> myHashMap = new HashMap<>();
           try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("hashmap.ser"))) {
               myHashMap = (HashMap<String, Card>) inputStream.readObject();
               //System.out.println("HashMap has been read from the file.");
           } catch (IOException | ClassNotFoundException e) {

           }
           myHashMap.put(position, state.getState());
           try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("hashmap.ser"))) {
               outputStream.writeObject(myHashMap);
               System.out.println("HashMap has been written to the file.");
           } catch (IOException e) {
               e.printStackTrace();
           }
    }

    public HashMap <String, Card> readSave(){
        HashMap<String, Card> myHashMap = null;

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("hashmap.ser"))) {
            myHashMap = (HashMap<String, Card>) inputStream.readObject();
            //System.out.println("HashMap has been read from the file.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            myHashMap.put("Сохранений не найдено", null);
        }

        /* В случае отладки  ̶р̶а̶з̶б̶и̶т̶ь̶ ̶с̶т̶е̶к̶л̶о̶ раскомментировать
        if (myHashMap != null) {
            for (String key : myHashMap.keySet()) {
                System.out.println(key + ": " + myHashMap.get(key));
            }
        }
        */
        return myHashMap;
    }

    public static boolean isDigit(String s) throws NumberFormatException {
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
            HashMap <String, Card> myHashMap = readSave(); //Обновление переменной, что бы не считывать много раз
            saveMenu.menu.clear();  //Очистка массива с пунктами меню
            saveMenu.menu.add("Меню сохранения ^___^ \n Укажите номер или введите новое имя");
            for (String s : myHashMap.keySet()) {
                saveMenu.menu.add(s);//Добавление найденных сохранений в массив с пунктами меню
            }
            saveMenu.menu.add("Выход из сохранений");//Последний пункт
            saveMenu.renderMenu(); //Вывод через println

            String input = scanner.nextLine();

            if(isDigit(input)){//Если число, то попытка загрузить сохранение
                int answ = Integer.parseInt(input); //Ответ в число
                if(answ > 0 && answ <= saveMenu.menu.size()-2){ //Если существующий пункт меню
                    if(myHashMap.get(saveMenu.getItemName(answ)) != null) { //Если сохранений не, то ничего не делать
                        writeSave(state, saveMenu.getItemName(answ));
                    }
                }else if(input.equals(saveMenu.menu.size()-1+"")){ //Если выход
                    break;
                }else{
                    System.out.println("Неверный ввод...");
                }
            }else{
                writeSave(state, input);
            }

        }


    }
}
