package kurs002;

import java.util.LinkedList;
import java.util.List;

public class Menu {
    private List <MenuItem> menu = new LinkedList<>();

    public void addCommand(String name, Command command, boolean hidden){
        menu.add(new MenuItem(name, command, hidden));
    }
    public String getName(int of) {
        return menu.get(of).getName();
    }

    public void setName(int of, String name) {
        menu.get(of).setName(name);
    }

    public Command getCommand(int of) {
        return menu.get(of).getCommand();
    }

    public void setCommand(int of,String name, Command command, boolean hidden) {
        menu.get(of).setCommand(name, command, hidden);
    }

    public boolean isHidden(int of) {
        return menu.get(of).isHidden();
    }

    public void setHidden(int of, boolean hidden) {
        menu.get(of).setHidden(hidden);
    }

    public void clean(){
        menu.clear();
    }

    public void renderMenu(String menuName, boolean showAll){
        int i = 1;
        System.out.println(menuName);
        for (MenuItem o : menu) {
            if(!o.isHidden() || showAll) {
                System.out.println(i + ". " + o.getName());
                i++;
            }
        }
    }

    private class MenuItem{
        String name;
        Command command;
        boolean hidden;

        public MenuItem(String name, Command command, boolean hidden) {
            this.name = name;
            this.command = command;
            this.hidden = hidden;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Command getCommand() {
            return command;
        }

        public void setCommand(String name, Command command, boolean hidden) {
            this.command = command;
            this.name = name;
            this.hidden = hidden;
        }

        public boolean isHidden() {
            return hidden;
        }

        public void setHidden(boolean hidden) {
            this.hidden = hidden;
        }
    }


    public int size(){
        return menu.size();
    }
}
