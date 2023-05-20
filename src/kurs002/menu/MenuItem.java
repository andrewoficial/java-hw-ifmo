package kurs002.menu;

import kurs002.Game;

public class MenuItem {
    private String name;
    private int number;




    public MenuItem(String name, int num) {
        this.name = name;
        this.number = num;
    }

    public String getName() {
        return name;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber (int num){
        this.number = num;
    }


    public void setName(String name){
        this.name = name;
    }

    public String getItemText (){
        return number + ". " + name;
    }

    public void execute(Game game) {
        game.start();
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
