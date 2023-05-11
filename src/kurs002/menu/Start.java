package kurs002.menu;


import kurs002.Card2;
import kurs002.Command;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Класс начала игры
public class Start implements Command {
    Card2 currentCard;
    public Start(Card2 currentCard){
        this.currentCard = currentCard;
    }

    @Override
    public void execute() {


        System.out.println("Начинаю игру...");
        Scanner scanner = new Scanner(System.in);

        while(true){
            Map<String, Command> commands = new HashMap<>();
            ArrayList <String> menu = new ArrayList<>(); //<String> что бы не было сырого типа данных
            if(currentCard == null || currentCard.getNext() == null || currentCard.getPrev() == null){
                System.out.println(currentCard.getContent());
                System.out.println("Игра завершена!");
                break;
            }


            menu.add("Меню:");
            menu.add(currentCard.getNext().getTitle());
            menu.add(currentCard.getPrev().getTitle());
            menu.add("Выйти");

            //System.out.println("DBG:" + currentState.getState().getTitle());
            System.out.println(currentCard.getContent());
            for (String s : menu) {
                if(menu.indexOf(s) > 0){
                    System.out.println(menu.indexOf(s) + ". "+ s);
                }else{
                    System.out.println(s);
                }
            }

            String input = scanner.nextLine();
            if (input.equals(""+(menu.size()-1))) {
                //System.out.println("Back to main menu");
                break;
            }else if(input.equals("1")){
                currentCard = currentCard.getNext();
            }else{
                currentCard = currentCard.getPrev();
            }
        }



    }
}
