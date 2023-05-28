package kurs002.menu;

import kurs002.Card;
import kurs002.FileHandler;
import kurs002.Game;
import kurs002.Utils;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Save extends MenuItem {
    Card card = null;
    FileHandler handler = new FileHandler();
    HashMap<String, Card> myHashMap = handler.getMap(); ;
    Menu saveMenu = new Menu();
    Scanner scanner = new Scanner(System.in);
    public Save(Game game){
        super("Сохранить", game.getMenu().getSize()+1);
        this.card = game.getCurrentCard();
    }

   private void writeSave(Card card, String position){
       myHashMap.put(position, card);
       try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("hashmap.ser"))) {
           outputStream.writeObject(myHashMap);
           //System.out.println("HashMap has been written to the file.");
       } catch (IOException e) {
           //e.printStackTrace();
           System.out.println("Возникла ошибка при сохранении :(");
       }
    }

    private void makeMenu(Game game){
        saveMenu.clean();  //Очистка массива с пунктами меню
        for (String s : myHashMap.keySet()) {
            saveMenu.addCommand(new MenuItem(s, saveMenu.getSize() + 1));//Добавление найденных сохранений в массив с пунктами меню
        }
        saveMenu.addCommand(new MenuItem("Выход в главное меню", saveMenu.getSize() + 1));//Последний пункт
        saveMenu.addCommand(new Exit(game, saveMenu));//Последний пункт
        saveMenu.renderMenu("Меню сохранения ^___^ \n Укажите номер или введите новое имя"); //Вывод через println
    }




    @Override
    public void execute(Game game) {

        //Заметка: StringBuffer потокобезопасен, и все его методы синхронизированы, а StringBuilder — нет.
        StringBuilder userInput = new StringBuilder();
        //Тут и так не создается новых объектов (только сканер вынес как поле класса). Остальное из цикла не убрать,
        //  потому что я хочу что бы после ввода нового сохранения оно сразу появлялось в меню
        while(true){
            makeMenu(game);
            userInput.setLength(0);
            userInput.append( scanner.nextLine()); //Попытка заменить пересоздание объекта строки на стрингбилдер.
            //Но в скобках все равно создается строка. Правда она анонимная, возможно от этого лучше будет

            if(Utils.isDigit(String.valueOf(userInput))){//Если число, то попытка записать сохранение со старым именем
                int answ = Integer.parseInt(String.valueOf(userInput)); //Ответ в число
                if(answ == saveMenu.getSize() - 1){
                    System.out.println("Выход в главное меню ");
                    game.start();
                    break;
                }

                if(answ > 0 && answ <= saveMenu.getSize() && saveMenu.getByNumber(answ) != null){ //Если существующий пункт меню
                    if(saveMenu.getByNumber(answ).getClass() != MenuItem.class) { //Если это функциональный пункт меню, запустить
                        saveMenu.getByNumber(answ).execute(game);
                    }else{//Если это списочный пункт меню (просто для вывода текста)
                        writeSave(card, saveMenu.getByNumber(answ).getName());
                        System.out.println("Сохранено! С именем " + saveMenu.getByNumber(answ).getName());
                        System.out.println();
                        game.start();
                        //break;
                    }
                }else{
                    System.out.println("Неверный ввод номера существующего имени...");
                }
            }else{
                //Ввод произвольного имени нового сохранения
                if(userInput.length() < 3){
                    System.out.println("Слишком короткое имя");
                }else {
                    writeSave(card, String.valueOf(userInput));
                }
            }
        }
    }
}
