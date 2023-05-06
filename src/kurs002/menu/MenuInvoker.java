package kurs002.menu;

import kurs002.Card;
import kurs002.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuInvoker {
    private Map<String, Command> commands = new HashMap<>();
    private boolean shortMenu = false;
    private boolean hasStarted = false;

    private Card currCard;
    public void addCommand(String name, Command command) {
        commands.put(name, command);
    }
    public boolean getTypeMenu(){
        return shortMenu;
    }

    public boolean getIsStarted(){
        return hasStarted;
    }

    public void setCard(Card card){
        this.currCard = card;
    }

    public Card getCard(){
        return currCard;
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            ArrayList <String> menu = new ArrayList<>(); //<String> что бы не было сырого типа данных


            if(shortMenu){
                menu.add("Меню:");
                menu.add("Выйти");
            }else{
                menu.add("Меню:");
                menu.add("Начать игру (начало отрисовки параграфов)");
                menu.add("Загрузить игру");
                if(hasStarted){
                    menu.add("Сохранить игру");
                    this.addCommand(""+(menu.size()-2), new Save());
                    System.out.println("DBG:" + "Add class save. Position:"+(menu.size()-2));
                    menu.add("Выйти");
                }else{
                    menu.add("Выйти");
                }
            }
            for (String s : menu) {
                if(menu.indexOf(s) > 0){
                    System.out.println(menu.indexOf(s) + ". "+ s);
                }else{
                    System.out.println(s);
                }
            }

            System.out.print("Введите номер команды: ");
            String input = scanner.nextLine();
            Command command = commands.get(input);

            if (command != null) {
                if(shortMenu){
                    if(input.equals(""+(menu.size()-1))){
                        shortMenu = false;
                    }
                }else{
                    shortMenu = true;
                    command.execute();
                }

            }else if(input.equals(""+(menu.size()-1))){
                System.out.println("Выход через главное меню");
                break;
            }else{
                System.out.println("Некорректный ввод, повторите попытку");
            }
        }
    }
}