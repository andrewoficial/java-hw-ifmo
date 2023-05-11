package kurs002;
import kurs002.menu.Exit;
import kurs002.menu.Save;

import java.util.*;

public class Game {
    private Card2 currentCard;

    private Scanner sc = new Scanner(System.in);

    boolean showHidden;

    private Menu menu;

    public Game(Card2 card, Menu menu){
        this.currentCard = card;
        this.menu = menu;
    }

    public void start(){


        while (true) {
            menu.renderMenu("Главное меню", showHidden);
            String userInp = sc.nextLine();
            int userAns = -1;
            try{
                userAns = Integer.parseInt(userInp);
                userAns--;
            }catch (Exception e){
                //System.out.println("DBG: Not number......"+userInp);
            }
            /*
            System.out.println("Have command: "+ menu.get(userAns).getCommand() != null);
            System.out.println("Correct number: "+ (userAns > 0 && userAns < menu.size()));
            System.out.println("    userAns > 0: "+ (userAns > 0));
            System.out.println("    userAns < menu.size(): "+ (userAns < menu.size()));
            System.out.println("    input: "+ (userAns));
            */


            if (userAns > -1 && userAns < menu.size() && menu.getCommand(userAns) != null) {
                if(menu.getName(userAns).equals("Начать игру")){
                    menu.setCommand(2, "Сохранить игру", new Save(currentCard), true);
                    menu.addCommand("Покинуть игру", new Exit(), false);
                    showHidden = true;
                    System.out.println("DBG: Run Game..."+userInp);
                }
                menu.getCommand(userAns).execute();
            }else if(userAns == menu.size()-1){
                System.out.println("Выход через главное меню");
                new Exit().execute();
            }else{
                System.out.println("Некорректный ввод, повторите попытку");
            }
        }


    }

    public Card2 getCurrentCard() {
        return currentCard;
    }

}

