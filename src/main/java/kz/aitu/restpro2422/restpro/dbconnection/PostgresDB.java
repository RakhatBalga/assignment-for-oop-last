package kz.aitu.restpro2422.restpro.dbconnection;

import kz.aitu.restpro2422.restpro.entities.Supplier;
import kz.aitu.restpro2422.restpro.entities.Product;
import kz.aitu.restpro2422.restpro.entities.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.sql.*; // Подключение JDBC API для работы с базой данных
import java.util.ArrayList;
import java.util.List;

@Repository  // Аннотация Spring
public class PostgresDB {

    private static final Logger logger = LoggerFactory.getLogger(PostgresDB.class);
    // Параметры подключения к базе данных PostgreSQL
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String username = "postgres";
    private final String password = "195203";

    /**  BD connect */
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Создание нового продукта
    public Product createProduct(String name) {
        String query = "INSERT INTO public.products (name) VALUES (?) RETURNING id";
        try (Connection con = connect();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                logger.info("Product created successfully: {} (ID={})", name, id);
                return new Product(id, name);
            }
        } catch (SQLException e) {
            logger.error("Error creating product: ", e);
        }
        return null;
    }

    /** create new supplier */
    public Supplier createSupplier(String name, int age, int id) {
        String query = "INSERT INTO public.suppliers (name, age, id) VALUES (?, ?, ?)";
        try (Connection con = connect();
             PreparedStatement st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, name);
            st.setInt(2, age);
            //st.setInt(3, id);
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                logger.error(" Supplier creation failed, no rows affected.");
                return null;
            }

             // Получаем ID нового поставщика


        } catch (SQLException e) {
            logger.error(" Error creating supplier: ", e);
        }
        return null;
    }

    /** create new inventory */
    public Inventory createInventory(String name) {
        String query = "INSERT INTO public.inventories (name) VALUES (?) RETURNING id";
        try (Connection con = connect();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                logger.info(" Inventory created: {} (ID={})", name, id);
                return new Inventory(id, name);
            }
        } catch (SQLException e) {
            logger.error(" Error creating inventory: ", e);
        }
        return null;
    }

    // Получение всех продуктов
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM public.products";
        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            logger.error(" Error fetching products: ", e);
        }
        return products;
    }

    // Получение всех поставщиков
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String query = "SELECT * FROM public.suppliers";
        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                suppliers.add(new Supplier(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("product_name")));
            }
        } catch (SQLException e) {
            logger.error(" Error fetching suppliers: ", e);
        }
        return suppliers;
    }

    // Получение всех инвентарей
    public List<Inventory> getAllInventories() {
        List<Inventory> inventories = new ArrayList<>();
        String query = "SELECT * FROM public.inventories";
        try (Connection con = connect();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                inventories.add(new Inventory(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            logger.error(" Error fetching inventories: ", e);
        }
        return inventories;
    }

    // Обновление возраста поставщика
    public void updateSupplierAge(int id, int newAge) {
        String query = "UPDATE public.suppliers SET age = ? WHERE id = ?";
        try (Connection con = connect();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, newAge);
            st.setInt(2, id);
            st.executeUpdate();
            logger.info(" Supplier age updated: ID={}, New Age={}", id, newAge);
        } catch (SQLException e) {
            logger.error(" Error updating supplier age: ", e);
        }
    }

    public void deleteProduct(int id) {
        String query = "DELETE FROM public.products WHERE id = ?";
        try (Connection con = connect();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, id);
            st.executeUpdate();
            logger.info(" Product deleted: ID={}", id);
        } catch (SQLException e) {
            logger.error(" Error deleting product: ", e);
        }
    }

    public void deleteSupplier(int id) {
        String query = "DELETE FROM public.suppliers WHERE id = ?";
        try (Connection con = connect();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, id);
            st.executeUpdate();
            logger.info(" Supplier deleted: ID={}", id);
        } catch (SQLException e) {
            logger.error(" Error deleting supplier: ", e);
        }
    }
}
