package hw006.travel;

public class Human {
    private String name;
    private String address;

    public Human(String name, String address){
        if(name == null || name.length() < 3){
            throw new IllegalArgumentException("name не менее 3 символов");
        }

        if(address == null || address.length() < 5){
            throw new IllegalArgumentException("address не менее 5 символов");
        }

        this.address = address;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }
}
