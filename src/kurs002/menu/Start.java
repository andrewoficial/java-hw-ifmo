package kurs002.menu;

import kurs002.Card;
import kurs002.Command;
import kurs002.GameState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Класс начала игры
public class Start implements Command {
    GameState currentState;
    public Start(GameState state){
        this.currentState = state;
    }

    public GameState getState(){
        return currentState;
    }

    private void updateState(Card card){
        currentState.setState(card);
    }
    @Override
    public void execute() {


        System.out.println("Начинаю игру...");
        if(this.currentState.getState() == null){
            currentState.setState(Card.Card01);
        }
        Scanner scanner = new Scanner(System.in);

        while(true){
            Map<String, Command> commands = new HashMap<>();
            ArrayList <String> menu = new ArrayList<>(); //<String> что бы не было сырого типа данных
            if(currentState.getState().getNext() == null || currentState.getState().getPrev() == null){
                System.out.println(currentState.getState().getContent());
                System.out.println("Игра завершена!");
                break;
            }


            menu.add("Меню:");
            menu.add(currentState.getState().getNext().getTitle());
            menu.add(currentState.getState().getPrev().getTitle());
            menu.add("Выйти");

            System.out.println("DBG:" + currentState.getState().getTitle());
            System.out.println(currentState.getState().getContent());
            for (String s : menu) {
                if(menu.indexOf(s) > 0){
                    System.out.println(menu.indexOf(s) + ". "+ s);
                }else{
                    System.out.println(s);
                }
            }

            String input = scanner.nextLine();
            if (input.equals(""+(menu.size()-1))) {
                System.out.println("Back to main menu");
                break;
            }else if(input.equals("1")){
                currentState.setState(currentState.getState().getNext());
            }else{
                currentState.setState(currentState.getState().getPrev());
            }
        }



    }
}
