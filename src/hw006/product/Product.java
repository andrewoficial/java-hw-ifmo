package hw006.product;

public class Product {
    int varName = 5;


    private String name;
    private  double calories;
    private double protein;
    private double fats;
    private double carbs;



    public Product(String name){
        if(name == null || name.length() < 1){
            throw new IllegalArgumentException("name не менее 1 символов");
        }
        this.name = name;
    }

    public Product(String name, double calories){
        this(name);

        if(calories < 0.01){
            throw new IllegalArgumentException("calories не менее 0.01 ");
        }
        this.calories = calories;
    }

    public Product(String name, double calories, double protein){
        this(name, calories);
        if(protein < 0.01){
            throw new IllegalArgumentException("protein не менее 0.01 ");
        }
        this.protein = protein;
    }

    public Product(String name, double calories, double protein, double fats){
        this(name, calories, protein);
        if(fats < 0.01){
            throw new IllegalArgumentException("fats не менее 0.01 ");
        }
        this.fats = fats;
    }

    public Product(String name, double calories, double protein, double fats, double carbs){
        this(name, calories, protein, fats);
        if(carbs < 0.01){
            throw new IllegalArgumentException("carbs не менее 0.01 ");
        }
        this.carbs = carbs;
    }

    public String getName(){
        return name;
    }

    public double getCalories(){
        return calories;
    }

    public double getProtein(){
        return protein;
    }

    public double getFats(){
        return fats;
    }

    public double getCarbs(){
        return carbs;
    }

}
