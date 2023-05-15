package kurs002.menu;

import kurs002.Card;
import kurs002.FileHandler;
import kurs002.Game;
import kurs002.Utils;

import java.util.HashMap;

import java.util.Scanner;



public class Load extends MenuItem {
    HashMap<String, Card> myHashMap;
    boolean notFound;

    public Load(Game game){
        //Не понял как эффективно использовать этот конструктор MenuItem. А вот было бы через интерфейс Executor было бы проще><
        super("Строка просто потому что IDE попросила", 0, null);
    }

    @Override
    public void execute(Game game) {
        /*
        Ожидаю тут генерацию меню где каждым пунктом будет слот сохранения
         */
        Menu loadMenu = new Menu();
        FileHandler handler = new FileHandler();
        while(true){
            loadMenu.clean();
            if(handler.hasSavedGame() == false){
                notFound = true;
            }

            if(notFound){
                System.out.println("Сохранений не найдено!");
            }else{
                myHashMap = handler.getMap(); //Создание мапы с сохранениями

            for (String s : myHashMap.keySet()) {
                loadMenu.addCommand(new MenuItem(s, loadMenu.getSize() + 1, null));//Добавление найденных сохранений в массив с пунктами меню
            }

            }

            loadMenu.addCommand(new MenuItem("Выход в главное меню", loadMenu.getSize() + 1, null));//Последний пункт
            loadMenu.addCommand(new MenuItem("Покинуть игру (закрыть приложение)", loadMenu.getSize() + 1, new Exit(game)));//Последний пункт
            loadMenu.renderMenu("Выбор слотов сохранения:"); //Вывод через println


            //Очередной велосипед для разбора ввода пользователя
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();          //.....Считывание ответа пользователя
            if(Utils.isDigit(input)){                   //.....Если число, то попытка загрузить сохранение
                int answ = Integer.parseInt(input);     //.....Ответ в число
                if(answ == loadMenu.getSize() - 1){
                    game.start();
                    break;
                }
                if(answ > 0 && answ <= loadMenu.getSize() && loadMenu.getByNumber(answ) != null){              //.....Если существующий пункт меню (а так как оно статическое....)
                    if(loadMenu.getByNumber(answ).isRunnable()){
                        loadMenu.getByNumber(answ).execute(game);
                    }else{
                        game.playCards(myHashMap.get(loadMenu.getByNumber(answ).getName()));
                        System.out.println("Загружаю игру с файла... Карточка:" + loadMenu.getByNumber(answ).getName());
                    }
                        break;
                }else{
                    System.out.println("Неверный ввод... (Недопустимая цифра)");
                }
            }else{
                System.out.println("Неверный ввод... (Строку нельзя вводить)");
            }

        }
    }


}
