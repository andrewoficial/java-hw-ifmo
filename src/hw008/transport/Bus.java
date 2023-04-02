package hw008.transport;

public class Bus extends Transport{
    protected boolean flagWifi = false;
    protected int wearLevel = 0;
    public Bus(String number, String color){
        super(number, color);
    }

    public void addWifi(){
        this.flagWifi = true;
    }

    public boolean getWifi(){
        return this.flagWifi;
    }


    public void repair(){
        super.repair();
        if(!flagWifi){
            flagWifi = true;
        }
    }
}
