package kurs002.menu;
/*
import kurs002.Card;
import kurs002.Command;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
/*
public class Save extends MenuItem {
    Card card = null;
    public Save(Card card){
        this.card = card;
    }
    HashMap<String, Card> myHashMap;
       private void writeSave(Card card, String position){
           HashMap<String, Card> myHashMap = new HashMap<>();
           try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("hashmap.ser"))) {
               myHashMap = (HashMap<String, Card>) inputStream.readObject();
               //System.out.println("HashMap has been read from the file.");
           } catch (IOException | ClassNotFoundException e) {

           }
           myHashMap.put(position, card);
           try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("hashmap.ser"))) {
               outputStream.writeObject(myHashMap);
               //System.out.println("HashMap has been written to the file.");
           } catch (IOException e) {
               //e.printStackTrace();
           }
    }

    public HashMap <String, Card> readSave(){
        HashMap<String, Card> myHashMap = new HashMap<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("hashmap.ser"))) {
            myHashMap = (HashMap<String, Card>) inputStream.readObject();
            //System.out.println("HashMap has been read from the file.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            myHashMap.put("Сохранений не найдено", null);
        }

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
        Menu saveMenu = new Menu();
        Scanner scanner = new Scanner(System.in);
        while(true){
            HashMap <String, Card> myHashMap = readSave(); //Обновление переменной, что бы не считывать много раз
            saveMenu.clean();  //Очистка массива с пунктами меню

            for (String s : myHashMap.keySet()) {
                saveMenu.addCommand(s, null, true);//Добавление найденных сохранений в массив с пунктами меню
            }
            saveMenu.addCommand("Выход из сохранений",null, true);//Последний пункт
            saveMenu.renderMenu("Меню сохранения ^___^ \n Укажите номер или введите новое имя", true); //Вывод через println

            String input = scanner.nextLine();

            if(isDigit(input)){//Если число, то попытка загрузить сохранение
                int answ = Integer.parseInt(input); //Ответ в число
                answ--;
                if(answ > 0 && answ <= saveMenu.size()-2){ //Если существующий пункт меню
                    if(myHashMap.get(saveMenu.getName(answ)) != null) { //Если сохранений не, то ничего не делать
                        writeSave(card, saveMenu.getName(answ));
                    }
                }else if(answ == saveMenu.size()-1){ //Если выход
                    break;
                }else{
                    System.out.println("Неверный ввод...");
                }
            }else{
                writeSave(card, input);
            }

        }


    }
}
*/