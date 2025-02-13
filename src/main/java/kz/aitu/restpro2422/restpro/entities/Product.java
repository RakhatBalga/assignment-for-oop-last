package kz.aitu.restpro2422.restpro.entities;

public class Product {
    public Product() {
    }
    private int id;
    private String name;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String toString(){
        return this.id + " " + this.name;
    }
}
