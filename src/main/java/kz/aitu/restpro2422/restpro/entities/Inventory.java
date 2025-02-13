package kz.aitu.restpro2422.restpro.entities;


public class Inventory {

    public Inventory() {
    }
    private int id;
    private String name;

    public Inventory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String toString(){
        return this.id + " " + this.name ;
    }
}