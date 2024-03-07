package controller;

import conexion.MySQLConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Product;

/**
 *
 * @author arper
 */
public class ProductController {
    MySQLConexion conexion;
    public ProductController() {
        conexion = new MySQLConexion();
    }
    
    public void insert(Product product) {
        // Establishes the connection to the database
        try (Connection conn = conexion.mySQLConexion()) {
            // Verify if the connection was successful
            if (conn != null) {
                // Prepares the SQL query to insert data
                String insertSQL = "INSERT INTO product (id, name, price, description, id_category, id_supplier ) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setInt(1, product.getId());
                    pstmt.setString(2, product.getName());
                    pstmt.setFloat(3, product.getPrice());
                    pstmt.setString(4, product.getDescription());
                    pstmt.setInt(5, product.getCategory().getId());
                    pstmt.setInt(6, product.getSupplier().getId());
                    // Execute the query
                    int rowsAffected = pstmt.executeUpdate();
                    // Verify if the insertion was successful
                    if (rowsAffected > 0) {
                        System.out.println("Successful integration");
                    } else {
                        System.out.println("Data could not be inserted");
                    }
                }
            } else {
                System.out.println("Could not establish a connection to the database");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while inserting into the database");
            e.printStackTrace();
        }
    }
    
    public void select() {
        // Establishes the connection to the database
        try (Connection conn = conexion.mySQLConexion()) {
            // Verify if the connection was successful
            if (conn != null) {
                // Prepares the SQL query to insert data
                String selectSQL = "SELECT * FROM product";
                try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
                    // Execute the query
                    ResultSet rs = pstmt.executeQuery();
                    // Itera on the results
                    while (rs.next()) {
                        System.out.println("Id: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Price: " + rs.getFloat("price")
                        + ", Description: " + rs.getString("description" ) + ", Category: " + rs.getInt("id_category" ) +", Supplier: " + rs.getInt("id_supplier" ) 
                        );
                    }
                }
            } else {
                System.out.println("Could not establish a connection to the database");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while inserting into the database");
            e.printStackTrace();
        }
    }
}
