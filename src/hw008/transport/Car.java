package hw008.transport;

public class Car extends Transport implements Repaintable{
    public Car(String number, String color){
        super(number, color);
    }


    @Override
    public void setColor(String color) {
        if(color == null || color.length() < 1){
            throw new IllegalArgumentException("invalid color name");
        }
        this.color = color;
    }
}
