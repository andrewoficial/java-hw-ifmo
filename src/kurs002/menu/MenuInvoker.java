package kurs002.menu;

import kurs002.Card;
import kurs002.Command;
import kurs002.GameState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuInvoker {
    private Map<String, Command> commands = new HashMap<>();
    private boolean shortMenu = false;
    private boolean hasStarted = false;

    private GameState gameState;
    public void addCommand(String name, Command command) {
        commands.put(name, command);
    }
    public boolean getIsStarted(){
        return hasStarted;
    }

    public void setState(GameState state){
        this.gameState = state;
    }

    public String getItemName(int num){
        if(num > menu.size()-1){
            return "";
        }
        return menu.get(num);
    }
    public ArrayList <String> menu = new ArrayList<>();
    public void renderMenu(){


        for (String s : menu) {
            if(menu.indexOf(s) > 0){
                System.out.println(menu.indexOf(s) + ". "+ s);
            }else{
                System.out.println(s);
            }
        }
        System.out.print("Введите номер команды: ");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            menu.clear();
            menu.add("Меню:");
            if(gameState.haveState()){
                menu.add("Продолжить игру");
            }else{
                menu.add("Начать игру (начало отрисовки параграфов)");
            }
            menu.add("Загрузить игру");
            if(gameState.haveState()){
                menu.add("Сохранить игру");
                //System.out.println("DBG:" + "Add menu item save. Position:"+(menu.size()-2));
                menu.add("Выйти");
            }else{
                menu.add("Выйти");
            }
            if(gameState.haveState()){
                this.addCommand(""+(menu.size()-2), new Save(this.gameState));
                //System.out.println("DBG:" + "Add menu command save. Position:"+(menu.size()-2));
                this.addCommand(""+(menu.size()-1), new Exit());
                //System.out.println("DBG:" + "Add menu command save. Position:"+(menu.size()-1));
            }
            renderMenu();

            String input = scanner.nextLine();
            Command command = commands.get(input);

            if (command != null) {
                command.execute();
            }else if(input.equals(""+(menu.size()-1))){
                System.out.println("Выход через главное меню");
                new Exit();
            }else{
                System.out.println("Некорректный ввод, повторите попытку");
            }
        }
    }
}