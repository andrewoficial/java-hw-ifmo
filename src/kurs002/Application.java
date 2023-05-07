package kurs002;

import kurs002.menu.*;


public class Application {
    public static void main(String[] args) {
        Card.Card01.setPrev(Card.Card03);
        Card.Card01.setNext(Card.Card02);
        Card.Card02.setPrev(null);
        Card.Card02.setNext(null);
        Card.Card03.setPrev(Card.Card04);
        Card.Card03.setNext(Card.Card04);
        Card.Card04.setPrev(Card.Card05);
        Card.Card04.setNext(Card.Card06);
        Card.Card05.setPrev(Card.Card01);
        Card.Card05.setNext(Card.Card01);
        Card.Card06.setPrev(Card.Card02);
        Card.Card06.setNext(Card.Card01);

        GameState gameState = new GameState(null);
        MenuInvoker invoker = new MenuInvoker();
        invoker.addCommand("1", new Start(gameState));
        invoker.addCommand("2", new Load());
        invoker.addCommand("3", new Exit());
        invoker.setState(gameState);
        invoker.run();
    }
}
