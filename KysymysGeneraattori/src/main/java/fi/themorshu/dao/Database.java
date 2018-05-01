package fi.themorshu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Luokkassa luodaan yhteus tietokantaan, ja kaikki tietokantaan tehtävät muutokset
 * tehdään tämän luokan Connectonin avulla
 * 
 */

public class Database {

    private String databaseAddress;
    private Connection connection;
 
    /**
     * Konstruktorissa määritellään tietokantatiedoston osoite
     * @param databaseAddress Tietokantatiedoston osoite ja nimi
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Database(String databaseAddress) throws ClassNotFoundException, SQLException {
        this.databaseAddress = databaseAddress;
    }

    /**
     * Metodi luo yhteyden kontruktorissa määriteltyyn tietokantaan
     * @return Connection olio databaseen
     * @throws SQLException 
     */
    public Connection getConnection() throws SQLException {
        this.connection = DriverManager.getConnection(this.databaseAddress);
        return this.connection;
    }

    /**
     * Metodi sulkee yhteyden (Connection) konstruktorissa määriteltyyn tietokantaan
     * @throws SQLException 
     */
    public void closeConnection() throws SQLException {
        this.connection.close();
    }
}
