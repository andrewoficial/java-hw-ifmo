package hw008.transport;

public class RepairStation {
    Transport[] arrayTransport = new Transport [10];
    String[] colors = {"red", "green", "blue", "white", "black", "orange", "green", "blue", "white", "black", "orange"};

    public void addTransport(Transport transport){

        for (int i = 0; i < arrayTransport.length; i++) {
            if(arrayTransport[i] == null){
                arrayTransport[i] = transport;
                return;
            }
        }
        System.out.println("Array is full");

    }

    public void addTransport(Car car){
        for (int i = 0; i < arrayTransport.length; i++) {
            if(arrayTransport[i] == null){
                arrayTransport[i] = (Transport) car;
                return;
            }
        }
        System.out.println("Array is full");
    }

    public void addTransport(Bus someBus){
        for (int i = 0; i < arrayTransport.length; i++) {
            if(arrayTransport[i] == null){
                arrayTransport[i] = (Transport) someBus;
                return;
            }
        }
        System.out.println("Array is full");
    }

    public void addTransport(Train train){
        for (int i = 0; i < arrayTransport.length; i++) {
            if(arrayTransport[i] == null){
                arrayTransport[i] = (Transport) train;
                return;
            }
        }
        System.out.println("Array is full");
    }
    public void reapirAll(){ //Не смог понять как автоматически перекрашивать всех кто Repaitable
        for (int i = 0; i < arrayTransport.length; i++) {
            if(arrayTransport[i] == null){
                continue;
            }

            if(arrayTransport[i] instanceof Car){
                Car tmp = (Car) arrayTransport[i];
                tmp.repair();
                tmp.setColor(colors[(int) Math.random() * 10]);
                arrayTransport[i] = (Transport) tmp;
                tmp = null;
            }

            if(arrayTransport[i] instanceof Bus){
                Bus tmp = (Bus) arrayTransport[i];
                tmp.repair();
                arrayTransport[i] = (Transport) tmp;
                tmp = null;
            }

            if(arrayTransport[i] instanceof Train){

                Train tmp = (Train) arrayTransport[i];
                tmp.repair();
                arrayTransport[i] = (Transport) tmp;
                tmp = null;
            }
            arrayTransport[i] = null;
        }
    }
}
