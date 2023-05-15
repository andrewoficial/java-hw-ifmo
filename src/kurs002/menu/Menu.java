package kurs002.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List <MenuItem> menu = new ArrayList<>();
    public void addCommand(MenuItem item){
        menu.add(item);
    }
    public MenuItem getByName(String name){
        for (MenuItem menuItem : menu) {
            if(menuItem.getName().equalsIgnoreCase(name))
                return menuItem;
        }
        return null;
    }

    public MenuItem getByNumber(int num){
        for (MenuItem menuItem : menu) {
            if(menuItem.getNumber() == num)
                return menuItem;
        }
        return null;
    }

    public void clean(){
        menu.clear();
    }

    public int renderMenu(String menuName){
        int numberOfItems = 0;
        System.out.println(menuName);
        for (MenuItem menuItem : menu) {
            if(!menuItem.isHidden()) {
                menuItem.setNumber(numberOfItems + 1);
                numberOfItems++;
                System.out.println(menuItem.getItemText());
            }
        }
        return numberOfItems;
    }
}
