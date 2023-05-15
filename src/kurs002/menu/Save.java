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

    public Save(Game game){
        super("Строка просто потому что IDE попросила", 0, null);
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






    @Override
    public void execute(Game game) {
        Menu saveMenu = new Menu();
        Scanner scanner = new Scanner(System.in);
        while(true){
            saveMenu.clean();  //Очистка массива с пунктами меню

            for (String s : myHashMap.keySet()) {
                saveMenu.addCommand(new MenuItem(s, saveMenu.getSize() + 1, null));//Добавление найденных сохранений в массив с пунктами меню
            }

            saveMenu.addCommand(new MenuItem("Выход в главное меню", saveMenu.getSize() + 1, game));//Последний пункт
            saveMenu.addCommand(new MenuItem("Покинуть игру (закрыть приложение)", saveMenu.getSize() + 1, new Exit(game)));//Последний пункт

            saveMenu.renderMenu("Меню сохранения ^___^ \n Укажите номер или введите новое имя"); //Вывод через println

            String input = scanner.nextLine();

            if(Utils.isDigit(input)){//Если число, то попытка записать сохранение со старым именем
                int answ = Integer.parseInt(input); //Ответ в число
                if(answ == saveMenu.getSize() - 1){
                    System.out.println("Выход в главное меню ");
                    game.start();
                    break;
                }

                if(answ > 0 && answ <= saveMenu.getSize() && saveMenu.getByNumber(answ) != null){ //Если существующий пункт меню
                    if(saveMenu.getByNumber(answ).isRunnable()) { //Если это функциональный пункт меню, запустить
                        saveMenu.getByNumber(answ).execute(game);
                    }else{//Если это списочный пункт меню (просто для вывода текста)
                        writeSave(card, saveMenu.getByNumber(answ).getName());
                        System.out.println("Сохранено! С именем " + saveMenu.getByNumber(answ).getName());
                        System.out.println();
                        game.start();
                        //break;
                    }
                }else{
                    System.out.println("Неверный ввод...answ > 0 && answ <= saveMenu.getSize()");
                }
            }else{
                //Ввод произвольного имени нового сохранения
                if(input.length() < 3){
                    System.out.println("Слишком короткое имя");
                }else {
                    writeSave(card, input);
                    //break;
                }
            }

        }


    }
}
