package kurs002.menu;

import kurs002.Card;
import kurs002.Command;
import kurs002.GameState;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

import static kurs002.menu.Save.isDigit;

public class Load implements Command {
    HashMap<String, Card> myHashMap;
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
    @Override
    public void execute() {
        MenuInvoker loadMenu = new MenuInvoker();
        Scanner scanner = new Scanner(System.in);
        while(true){
            myHashMap = readSave(); //Обновление переменной, что бы не считывать много раз
            loadMenu.menu.clear();  //Очистка массива с пунктами меню
            loadMenu.menu.add("Выбор слотов сохранения:"); //Заголовок меню
            for (String s : myHashMap.keySet()) {
                loadMenu.menu.add(s);//Добавление найденных сохранений в массив с пунктами меню
            }
            loadMenu.menu.add("Выход из сохранений");//Последний пункт
            loadMenu.renderMenu(); //Вывод через println


            String input = scanner.nextLine(); //Считывание ответа пользователя
            if(isDigit(input)){//Если число, то попытка загрузить сохранение
                int answ = Integer.parseInt(input); //Ответ в число
                if(answ > 0 && answ <= loadMenu.menu.size()-2){ //Если существующий пункт меню
                    if(myHashMap.get(loadMenu.getItemName(answ)) != null) { //Если сохранений не, то ничего не делать
                        new Start(new GameState(myHashMap.get(loadMenu.getItemName(answ)))).execute();
                    }
                }else if(input.equals(loadMenu.menu.size()-1+"")){ //Если выход
                    break;
                }else{
                    System.out.println("Неверный ввод...");
                }
            }else if(input.equals(loadMenu.menu.size()-1+"")){
                System.out.println("Неверный ввод...");
            }

        }
    }
}
