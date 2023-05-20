package kurs002.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List <MenuItem> menu = new ArrayList<>();
    public void addCommand(MenuItem item){
        menu.add(item);
    }

    public void insertCommand(MenuItem item, int pos){
        //System.out.println("    Start insert "+pos + " of " + menu.size());
        int menuSize =  menu.size();
        for(int i = pos; i <= menuSize; i++){
            if(i == menu.size()){
                menu.get(i-1).setNumber(menu.get(i-1).getNumber()+1);
                menu.add(menu.get(i-1));
            }else{
                //System.out.print("        Move "+menu.get(i).getName());
                //System.out.print("  for  "+i + " from " + menu.get(i).getNumber());
                //System.out.println("  to  "+(menu.get(i).getNumber()+1));
                menu.get(i).setNumber(menu.get(i).getNumber()+1);
            }
        }
        item.setNumber(pos);
        menu.set(pos, item);
    }
    public void removeCommand(MenuItem item){
        menu.remove(item);
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
        System.out.println(menuName);
        //Сложность алгоритма степенная (потому что каждый раз идет вложенный перебор для поиска по List`y (для сортировки)
        //возможно лучше все же отказаться от хранения номера меню как поля, и подумать как сдлеать обращение
        //по номеру меню как к ячейке в массиве (константное время доступа)
        for (int i = 0; i <= menu.size(); i++) {
            if(getByNumber(i) != null){
                System.out.println(getByNumber(i).getItemText());
            }
        }
        return menu.size();
    }

    public int getSize(){
        return menu.size();
    }


}
