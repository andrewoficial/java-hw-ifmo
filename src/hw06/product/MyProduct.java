package hw06.product;

import hw06.travel.Human;

public class MyProduct {
    private double proteinLimit;
    private double caloriesLimit;
    private double fatsLimit;
    private double carbsLimit;

    private Product [] products;

    public MyProduct(double caloriesLimit ){
        if(caloriesLimit < 5){
            throw new IllegalArgumentException("calories не менее 5 ");
        }
        this.caloriesLimit = caloriesLimit;
    }

    public MyProduct(double caloriesLimit, double proteinLimit, double fatsLimit, double carbsLimit ){
        this(caloriesLimit);
        if(proteinLimit < 5){
            throw new IllegalArgumentException("proteinLimit не менее 5 ");
        }
        if(fatsLimit < 5){
            throw new IllegalArgumentException("fatsLimit не менее 5 ");
        }
        if(carbsLimit < 5){
            throw new IllegalArgumentException("carbs не менее 5 ");
        }
        this.carbsLimit = carbsLimit;
        this.fatsLimit = fatsLimit;
        this.proteinLimit = proteinLimit;

    }

    public void addProduct(Product product){
            if(product == null){
                throw new IllegalArgumentException("product не null in " + product.getName() + " allowed " +
                        proteinLimit + " received " + product.getProtein());
            }
            if(product.getCalories() > caloriesLimit ){
                throw new IllegalArgumentException("too many Calories in " + product.getName() + " allowed " +
                        proteinLimit + " received " + product.getProtein());
            }
            if(product.getFats() > fatsLimit ){
                throw new IllegalArgumentException("too many Fats in " + product.getName() + " allowed " +
                        proteinLimit + " received " + product.getProtein());
            }
            if(product.getCarbs() > carbsLimit ){
                throw new IllegalArgumentException("too many Carbs in " + product.getName() + " allowed " +
                        proteinLimit + " received " + product.getProtein());
            }
            if(product.getProtein() > proteinLimit ){
                throw new IllegalArgumentException("too many Protein in " + product.getName() + " allowed " +
                        proteinLimit + " received " + product.getProtein());
            }

            Product[] newArray;
            if(this.products != null){
                newArray = new Product[this.products.length + 1];
                for (int i = 0; i < this.products.length; i++) {
                    newArray [i] = this.products[i];
                }
                newArray [this.products.length] = product;
            }else{
                newArray = new Product[1];
                newArray [0] = product;
            }
            this.products = newArray;
        }

        public void printProducts(){
            if(this.products == null || this.products.length < 1){
                System.out.println("Не найдено продуктов");
            }else{
                System.out.print("Список продуктов:");
                for (int i = 0; i < this.products.length - 1; i++) {
                    System.out.print(this.products[i].getName() + ", ");
                }
                System.out.print(this.products[this.products.length - 1].getName() + "!");
                System.out.println();
            }
        }
}
