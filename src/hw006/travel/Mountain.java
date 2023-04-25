package hw006.travel;

public class Mountain {

    private String name;
    private String country;

    private Double height;

    public Mountain(String name, String country, Double height){
        if(name == null || name.length() < 4){
            throw new IllegalArgumentException("name не менее 4 символов");
        }

        if(country == null || country.length() < 4){
            throw new IllegalArgumentException("country не менее 4 символов");
        }

        if(height < 100){
            throw new IllegalArgumentException("height не менее 100");
        }


        this.height = height;
        this.country = country;
        this.name = name;
    }

    public String getName(){
        return name;
    }


}
