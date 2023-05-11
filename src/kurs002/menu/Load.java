package kurs002.menu;

import kurs002.Card2;
import kurs002.Command;
import kurs002.Menu;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

import static kurs002.menu.Save.isDigit;

public class Load implements Command {
    HashMap<String, Card2> myHashMap;
    boolean notFound;
    public HashMap <String, Card2> readSave(){
        HashMap<String, Card2> myHashMap = new HashMap<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("hashmap.ser"))) {
            myHashMap = (HashMap<String, Card2>) inputStream.readObject();
            //System.out.println("HashMap has been read from the file.");
        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
            myHashMap.put("Сохранений не найдено", null);
            notFound = true;
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
        Menu loadMenu = new Menu();
        Scanner scanner = new Scanner(System.in);
        while(true){
            myHashMap = readSave(); //Обновление переменной, что бы не считывать много раз
            loadMenu.clean();  //Очистка массива с пунктами меню
            for (String s : myHashMap.keySet()) {
                loadMenu.addCommand(s, new Start(myHashMap.get(s)), false);//Добавление найденных сохранений в массив с пунктами меню
            }
            loadMenu.addCommand("Выход из сохранений", null, false);//Последний пункт


            loadMenu.renderMenu("Выбор слотов сохранения:", false); //Вывод через println
            String input = scanner.nextLine(); //Считывание ответа пользователя
            if(isDigit(input)){//Если число, то попытка загрузить сохранение
                int answ = Integer.parseInt(input); //Ответ в число
                answ--;

                if(!notFound && answ < loadMenu.size()-1){ //Если существующий пункт меню
                    loadMenu.getCommand(answ).execute();
                }else if(answ == loadMenu.size()-1){ //Если выход
                    break;
                }else{
                    System.out.println("Неверный ввод...");
                }
            }else{
                System.out.println("Неверный ввод...");
            }

        }
    }
}
