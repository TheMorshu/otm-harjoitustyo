//OK!


package fi.themorshu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private String databaseAddress;
    private Connection connection; //extra

    public Database(String databaseAddress) throws ClassNotFoundException, SQLException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        this.connection = DriverManager.getConnection(this.databaseAddress);
        return this.connection;
    }

    
    public void closeConnection() throws SQLException {
        this.connection.close();
    }

}
