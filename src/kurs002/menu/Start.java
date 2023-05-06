package kurs002.menu;

import kurs002.Card;
import kurs002.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Класс начала игры
public class Start implements Command {
    Card currCard = null;
    public void setCard(Card card){
        currCard = card;
    }

    public Card etCard(){
        return currCard;
    }
    @Override
    public void execute() {
        Card card01 = new Card("Лисенок", "Каждое утро Лисёнок просыпался, завтракал и шёл увидеться с " +
                "Бельчонком. Это утро не было исключением. Лисёнок пришёл на их обычное место встречи, но Бельчонка " +
                "там не было. Лисёнок ждал, ждал, но так и не смог дождаться своего друга. \" Бельчонок не пропустил " +
                "еще ни одной встречи, вдруг он попал в беду.\" - подумал Лисёнок. Как поступить Лисенку?");

        Card card02 = new Card("Вернуться домой", "Вернувшись домой, Лисёнок нашёл там Бельчонка. " +
                "Оказалось, что Бельчонок пришёл на место встречи раньше и увидел там рой злобных пчел. Он поспешил " +
                "предупредить об этом Лисёнка, но они разминулись. Наконец-то друзья нашли друг друга! Игра завершилась " +
                "успехом");
        Card card03 = new Card("Отправиться на поиски", "Все лесные жители были заняты своими делами и не " +
                "обращали внимания на Лисёнка и его проблему. " +
                "Но вдруг кто нибудь видел Бельчонка... Лисёнок не знал, что ему делать. Помогите ему.");

        card01.setPrev(card03);
        card01.setNext(card02);
        System.out.println("Начинаю игру...");
        if(this.currCard == null){
            this.currCard = card01;
        }
        Scanner scanner = new Scanner(System.in);
        Map<String, Command> commands = new HashMap<>();
        ArrayList <String> menu = new ArrayList<>(); //<String> что бы не было сырого типа данных
        while(true){
            if(currCard.getNext() == null || currCard.getPrev() == null){
                System.out.println("Игра завершена!");
                break;
            }


            menu.add("Меню:");
            menu.add(currCard.getNext().getTitle());
            menu.add(currCard.getPrev().getTitle());
            menu.add("Выйти");

            System.out.println("DBG:" + currCard.getTitle());
            System.out.println(currCard.getContent());
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
            }else if(input.equals("1")){
                currCard = currCard.getNext();
            }else{
                currCard = currCard.getPrev();
            }
        }



    }
}
