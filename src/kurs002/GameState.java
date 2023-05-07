package kurs002;

import java.io.*;

public class GameState {
    private Card state;

    public GameState(Card state){
        this.state = state;
    }

    public boolean haveState(){
        if(this.state == null){
            return false;
        }
        return true;
    }

    public Card getState(){
        return state;
    }

    public void setState(Card state){
        this.state = state;
    }


}


