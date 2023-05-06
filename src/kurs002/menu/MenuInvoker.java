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
                    addCommand(""+(menu.size()-2), new Save());
                    menu.add("Выйти");
                }else{
                    menu.add("Выйти");
                }
            }
            for (String s : menu) {

                if(menu.indexOf(s) > 0){
                    System.out.println(menu.indexOf(s) + ". "+ s);
                }else{
                    //System.out.println(menu.size());
                    System.out.println(s);
                }

            }

            System.out.print("Введите номер команды: ");
            String input = scanner.nextLine();
            if (input.equals(""+(menu.size()-1))) {
                if(shortMenu){
                    System.out.println("Back to main menu");
                    shortMenu = false;
                }else {
                    shortMenu = false;
                    hasStarted = false;
                    break;
                }
            }

            Command command = commands.get(input);
            if (command != null) {
                if(!shortMenu && ! input.equals(""+(menu.size()-1))){
                    addCommand(""+(menu.size()-1), null);

                    if(!hasStarted && input.equals(""+1)){
                        hasStarted = true;
                    }
                    shortMenu = true;
                }
                command.execute();
            } else {
                System.out.println("Некорректный ввод, повторите попытку");
            }
        }
    }
}