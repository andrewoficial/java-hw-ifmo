package kurs002.menu;

import kurs002.Game;

public class ReturnToGame extends MenuItem{

    public ReturnToGame(Game game) {
        super("-", 0, null);
    }
    @Override
    public void execute(Game game) {
        game.playCards(game.getCurrentCard());
    }

}
