package kurs002.menu;

import kurs002.Command;

public class Load implements Command {
    @Override
    public void execute() {
        System.out.println("Loading game...");
    }
}
