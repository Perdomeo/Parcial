package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author arper
 */
public class MySQLConexion {
    // MySQL library
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // Name of the database
    private static final String DATABASE = "Store";

    // Host
    private static final String HOSTNAME = "localhost";

    // port
    private static final String PORT = "3306";

    // Path of our database
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE + "?useSSL=false";

    // User name
    private static final String USERNAME = "root";

    // User key
    private static final String PASSWORD = "root";

    /**
     * This method is responsible for establishing the connection to the database.
     * @return Connection the database.
     */
    public static Connection mySQLConexion () {
        Connection conn = null;

        try {
            // Load the MySql drive
            Class.forName(DRIVER);
            // Establishes the connection to the database
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            // Prints the error in case the connection cannot be established
            e.printStackTrace();
        }
        // We return the connection
        return conn;
    }
}
