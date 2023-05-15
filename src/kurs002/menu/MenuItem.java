package kurs002.menu;

import kurs002.Game;

public class MenuItem {
    private String name;
    private boolean hidden;
    private int number;

    private MenuItem runnableItem;



    public MenuItem(String name, int num, MenuItem runable) {
        this.name = name;
        this.hidden = false;
        this.number = num;
        this.runnableItem = runable;
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

    public void setName(String name){
        this.name = name;
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

    public boolean isRunnable(){
        return this.runnableItem != null;
    }
    public void execute(Game game) {
        if(runnableItem != null){
            runnableItem.execute(game);
            return;
        }
        game.start();
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", hidden=" + hidden +
                ", number=" + number +
                ", runnableItem=" + runnableItem +
                '}';
    }
}
