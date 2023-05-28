package kurs002.menu;

import kurs002.Card;
import kurs002.FileHandler;
import kurs002.Game;
import kurs002.Utils;

import java.util.HashMap;
import java.util.Scanner;


public class Load extends MenuItem {
    HashMap<String, Card> myHashMap;
    Menu loadMenu = new Menu();
    FileHandler handler = new FileHandler();
    public Load(String name, int position, Game game){
        super(name, position);
    }

    private void makeMenu(Game game){
        loadMenu.clean();
        if(!handler.hasSavedGame()){
            System.out.println("Сохранений не найдено!");
        }else{
            myHashMap = handler.getMap(); //Создание мапы с сохранениями
            for (String s : myHashMap.keySet()) {
                loadMenu.addCommand(new MenuItem(s, loadMenu.getSize() + 1));//Добавление найденных сохранений в массив с пунктами меню
            }
        }
        loadMenu.addCommand(new MenuItem("Выход в главное меню", loadMenu.getSize() + 1));//Последний пункт
        loadMenu.addCommand(new Exit(game, loadMenu));//Последний пункт
        loadMenu.renderMenu("Выбор слотов сохранения:"); //Вывод через println

    }
    private int userChoice(){
        //В цикле только проверка ввода.
        Scanner scanner = new Scanner(System.in);
        while(true){
            String input = scanner.nextLine();          //.....Считывание ответа пользователя
            if(Utils.isDigit(input)){                   //.....Если число, то попытка загрузить сохранение
                int answer = Integer.parseInt(input);
                if((answer > 0 && answer <= loadMenu.getSize() && loadMenu.getByNumber(answer) != null) || answer == loadMenu.getSize() - 1){
                    //Или существующий пункт меню или выход
                    return answer;
                }else{
                        System.out.println("Неверный ввод... (Недопустимая цифра)");
                }
            }else{
                System.out.println("Неверный ввод... (Строку нельзя вводить)");
            }
        }
    }
    @Override
    public void execute(Game game) {
        makeMenu(game);

        int answer = userChoice();     //...............Ответ в число
        if(answer == loadMenu.getSize() - 1){//.........Быстрое определение что это выход
            game.start();
        }else {//.......................................Запуск execute
            if (loadMenu.getByNumber(answer).getClass() != MenuItem.class) {
                loadMenu.getByNumber(answer).execute(game); //Если функциональная часть меню
            } else {
                game.playCards(myHashMap.get(loadMenu.getByNumber(answer).getName())); //Если слот сохранения
                System.out.println("Загружаю игру с файла... Карточка:" + loadMenu.getByNumber(answer).getName());
            }
        }

    }


}
