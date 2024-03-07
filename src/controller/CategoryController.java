package controller;

import conexion.MySQLConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Category;

/**
 *
 * @author arper
 */
public class CategoryController {
    MySQLConexion conexion;
    public CategoryController() {
        conexion = new MySQLConexion();
    }
        
    public void insert(Category category) {
        // Establishes the connection to the database
        try (Connection conn = conexion.mySQLConexion()) {
            // Verify if the connection was successful
            if (conn != null) {
                // Prepares the SQL query to insert data
                String insertSQL = "INSERT INTO category (id, name, description ) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setInt(1, category.getId());
                    pstmt.setString(2, category.getName());
                    pstmt.setString(3, category.getDescription());
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
                String selectSQL = "SELECT * FROM category";
                try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
                    // Execute the query
                    ResultSet rs = pstmt.executeQuery();
                    // Itera on the results
                    while (rs.next()) {
                        System.out.println("Id: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Description: " + rs.getString("description" )
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
