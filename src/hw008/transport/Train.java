package hw008.transport;

public class Train extends Transport {
    int carriage = 5;
    public Train(String number, String color, int carriage){
        super(number, color);
        if(carriage < 1){
            throw new IllegalArgumentException("argument carriage must be more than 0");
        }
        this.carriage = carriage;
    }

    public void repair(boolean needMoreCarriage){
        super.repair();
        if(needMoreCarriage){
            carriage++;
        }
    }

    public void addCarriage(int num){
        carriage += num;
    }
}
