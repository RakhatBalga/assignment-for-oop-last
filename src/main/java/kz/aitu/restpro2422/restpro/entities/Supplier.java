package kz.aitu.restpro2422.restpro.entities;

public class Supplier {
    public Supplier() {
    }
    private int id;
    private String name;
    private int age;
    private String product_name;

    public Supplier(int id, String name, int age, String product_name) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.product_name = product_name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getProduct_name() { return product_name; }
    public String toString(){
        return this.id + " " + this.name + " " + this.age + " " + this.product_name ;
    }
}
