package kurs002.menu;

import kurs002.Game;

public class MenuItem {
    private final String name;
    private boolean hidden;
    private int number;

    private MenuItem runnableItem;

    public MenuItem(String name, boolean hidden) {
        this.name = name;
        this.hidden = hidden;
    }

    public MenuItem(String name, boolean hidden, int num) {
        this.name = name;
        this.hidden = hidden;
        this.number = number;
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

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isHidden() {
        return hidden;
    }

    public String getItemText (){
        return number + ". " + name;
    }

    public void setRunnable (MenuItem item){
        this.runnableItem = item;
    }

    public void execute(Game game) {
        game.playCards(null);
    }
}
