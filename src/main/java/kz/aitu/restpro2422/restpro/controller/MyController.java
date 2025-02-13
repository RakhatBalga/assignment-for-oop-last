package kz.aitu.restpro2422.restpro.controller;

import kz.aitu.restpro2422.restpro.dbconnection.PostgresDB;
import kz.aitu.restpro2422.restpro.entities.Supplier;
import kz.aitu.restpro2422.restpro.entities.Product;
import kz.aitu.restpro2422.restpro.entities.Inventory;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api")
public class MyController {

    private final PostgresDB db;

    @Autowired
    public MyController(PostgresDB db) {
        this.db = db;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestParam String name) {
        Product product = db.createProduct(name);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/suppliers")
    public ResponseEntity<Supplier> createSupplier(@RequestParam String name, @RequestParam int age, @RequestParam int id) {
        Supplier supplier = db.createSupplier(name, age, id);
        if (supplier != null) {
            return ResponseEntity.ok(supplier);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/inventories")
    public ResponseEntity<Inventory> createInventory(@RequestParam String name) {
        Inventory inventory = db.createInventory(name);
        if (inventory != null) {
            return ResponseEntity.ok(inventory);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return db.getAllProducts();
    }

    @GetMapping("/suppliers")
    public List<Supplier> getAllSuppliers() {
        return db.getAllSuppliers();
    }

    @GetMapping("/inventories")
    public List<Inventory> getAllInventories() {
        return db.getAllInventories();
    }

    @PatchMapping("/suppliers/{id}/age")
    public ResponseEntity<String> updateSupplierAge(@PathVariable int id, @RequestParam int newAge) {
        db.updateSupplierAge(id, newAge);
        return ResponseEntity.ok("Supplier age updated: ID=" + id + ", New Age=" + newAge);
    }
    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable int id) {
        db.deleteSupplier(id);
        return ResponseEntity.ok().body("Supplier deleted: ID=" + id);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        db.deleteProduct(id);
        return ResponseEntity.ok().body("Product deleted: ID=" + id);
    }


}
