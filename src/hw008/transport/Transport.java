package hw008.transport;

abstract public class Transport {
    protected String number = "";
    protected int wearLevel = 0;

    public void repair(){
        if(this.wearLevel < 1){
            this.wearLevel--;
        }
    }
    public String getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }

    protected String color = "";
    public Transport(String number, String color){
        if(number == null || number.length() < 1){
            throw new IllegalArgumentException("Too short number");
        }
        this.number = number;
        this.color = color;
    }
}
