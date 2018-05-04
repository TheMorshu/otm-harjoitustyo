package fi.themorshu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * UsersDao luokka sisältää erilaisia metodeja Usereiden (käyttäjien) käsittelyyn tietokannassa.
 * UsersDao on siis eräänlainen data access object. UsersDao käyttää Database oliota vuorovaikutukseen
 * tietokantatiedostoon.
 */

public class UsersDao implements Dao<User> {
    Database database;
    String feedback;

    /**
     * Kontruktorissa määritellään käytettävä tietokantaolio, sekä alustetaan tyhjäksi tietokantatiedoston
     * käytöstä palautetta antava feedback String.
     * @param database Database olio, jonka kautta muutokset tehdään tietokantaan.
     */
    public UsersDao(Database database) {
        this.database = database;
        this.feedback = "";
    }
    
    /**
     * Metodia käytetään yksittäisen User olion etsimiseen tietokannasta. Uniikkina id:nä toimii käyttäjänimi.
     * @param username Etsittävän käyttäjän käyttäjänimi
     * @return Palauttaa etsityn käyttäjän User oliona
     * @throws SQLException Heittää SQL virheen, jos SQL yhteydessö on jotain vikaa
     */
    @Override
    public User findOne(String username) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT name, password, questions, right FROM Users WHERE name = ?");
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            int questions = resultSet.getInt("questions");
            int right = resultSet.getInt("right");
            statement.close();
            this.database.closeConnection();
            return new User(name, password, questions, right);
        } else {
            statement.close();
            this.database.closeConnection();
            return null;
        }
    }
    
    /**
     * Metodin avulla tarkistetaan, onko käyttäjä jo olemassa
     * @param name etsittävän käyttäjän käyttäjänimi
     * @return Palauttaa totuusarvon siitä, onko käyttäjää listattu tietokantaan (onko sitä olemassa)
     * @throws SQLException Heittää SQL virheen, jos SQL yhteydessö on jotain vikaa
     */
    public Boolean checkContainsName(String name) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT name FROM Users WHERE name = ?");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            statement.close();
            this.database.closeConnection();
            return true;
        }
        statement.close();
        this.database.closeConnection();
        return false;
    }

    /**
     * Metodin avulla "napataan" kaikki käyttäjät User oliona listassa.
     * @return Palauttaa listan kaikista tietokannan User oliosta listana
     * @throws SQLException Heittää SQL virheen, jos SQL yhteydessö on jotain vikaa
     */
    @Override
    public List<User> findAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT name, password, questions, right FROM Users;");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            int questions = resultSet.getInt("questions");
            int right = resultSet.getInt("right");
            userList.add(new User(name, password, questions, right));
        }
        statement.close();
        this.database.closeConnection();
        return userList;
    }
    
    /**
     * Metodi luo tietokantataulun käyttäjille tietokantaan. Voidaan suorittaa sen jälkeen,
     * kun tietokanta on luotu tai tyhjennetty. On siis osa alustusprosessia.
     * @see fi.themorshu.dao.UsersDao#clearDatabase() 
     */
    @Override
    public void setUpTableOnDatabase() {
        PreparedStatement statement;
        try {
            statement = database.getConnection().prepareStatement("CREATE TABLE users (\n" +
                    "id integer PRIMARY KEY,\n" +
                    "name varchar(200),\n" +
                    "password varchar (200),\n" +
                    "questions integer,\n" +
                    "right integer\n" +
                    ");");
            int changes = statement.executeUpdate();
            statement.close();
            this.database.closeConnection();
        } catch (SQLException ex) {
        }
    }
    
    /**
     * Metodi tyhjentää tietokannan, ja luo tietokantaan uudelleen siinä käytetyn tietokantataulun
     * @throws SQLException Heittää SQL virheen, jos SQL yhteydessö on jotain vikaa
     */
    @Override
    public void clearDatabase() throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("DROP TABLE Users;");
        int changes = statement.executeUpdate();
        statement.close();
        this.database.closeConnection();
        setUpTableOnDatabase();
    }
    
    /**
     * metodi tulostaa kaikkien tietokannassa olevien käyttäjien nimet. Metodia käytetään lähinnä debuggaukseen, eikä sillä ole juuri merkitystä tavalliselle käyttäjälle.
     * @throws SQLException Heittää SQL virheen, jos SQL yhteydessö on jotain vikaa
     */
    public void printUsers() throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT name FROM Users");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            System.out.println(name);
        }
        statement.close();
        this.database.closeConnection();
    }
    
    /**
     * Metodin avulla saadaan käyttäjät pisteineen merkkijonona. Voidaan hyödyntää mm. HiScoreissa.
     * @return Käyttäjän tehtävineen ja pisteineen merkkijonona (HiScoreja varten)
     * @throws SQLException Heittää SQL virheen, jos SQL yhteydessö on jotain vikaa
     */
    public String printAllUsersScores() throws SQLException {
        String string = "";
        ArrayList list = (ArrayList) this.findAll();
        Collections.sort(list); //new
        for (int i = 0; i < list.size(); i++) {
            User user = (User) list.get(i);
            string += "#" + (i + 1) + ": " + user.toString() + "\n";
        }
        return string;
    }
    
    /**
     * Tätä metodia käytetään sisäänkirjautumisen vahvistamiseen. Mikäli parametrina annetun käyttäjänimen salasana
     * on sama kuin parametrina annettu salasana, on kirjautuminen onnistunut ja metodi palauttaa kyseisen käyttäjän
     * User oliona. Muussa tapauksessa metodi palauttaa null
     * @param username Kirjautumista yrttävän käyttäjän käyttäjänimi
     * @param password Kirjausta yrittävän käytäjän salasana
     * @return Kirjautumisonnistumisesta riippuen joko käyttäjä User oliona (onnistunut) tai null (kirjautuminen epäonnistunut)
     * @throws SQLException Heittää SQL virheen, jos SQL yhteydessö on jotain vikaa
     */
    public User verifyLogin(String username, String password) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("SELECT name, password, questions, right FROM Users WHERE name = ? AND password = ?;");
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int questions = resultSet.getInt("questions");
            int right = resultSet.getInt("right");
            statement.close();
            this.database.closeConnection();
            return new User(username, password, questions, right);
        }
        statement.close();
        this.database.closeConnection();
        return null;
    }
    
    /**
     * Lisää vastattuihin kysymyksiin yhden kerran lisää halutulle käyttäjälle
     * @param name Halutun käyttäjän käyttäjänimi
     * @throws SQLException Heittää SQL virheen, jos SQL yhteydessö on jotain vikaa
     */
    public void addQuestionsForUser(String name) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("UPDATE Users SET questions = questions + 1 WHERE name = ?;");
        statement.setString(1, name);
        int changes = statement.executeUpdate();
        statement.close();
        this.database.closeConnection();
    }
    
    /**
     * Lisää pisteen halutulle käyttäjälle
     * @param name Halutun käyttäjän käyttäjänimi
     * @throws SQLException Heittää SQL virheen, jos SQL yhteydessö on jotain vikaa
     */
    public void addRightForUser(String name) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("UPDATE Users SET right = right + 1 WHERE name = ?;");
        statement.setString(1, name);
        int changes = statement.executeUpdate();
        statement.close();
        this.database.closeConnection();
    }
    
    /**
     * Nollaa halutun käyttäjän pistetulokset ja vastatut kysymysket
     * @param name Halutun käyttäjän käyttäjänimi
     * @throws SQLException Heittää SQL virheen, jos SQL yhteydessö on jotain vikaa
     */
    public void resetScore(String name) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("UPDATE Users SET right = 0, questions = 0 WHERE name = ?;");
        statement.setString(1, name);
        int changes = statement.executeUpdate();
        statement.close();
        this.database.closeConnection();
    }
    
    /**
     * Vaihtaa halutun käyttäjän salasanan uuteen salasanaan
     * @param name Käyttäjänimi, jolle salasana vaihdetaan
     * @param newpass Uusi salasana
     * @throws SQLException Heittää SQL virheen, jos SQL yhteydessö on jotain vikaa
     */
    public void changePassword(String name, String newpass) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("UPDATE Users SET password = ? WHERE name = ?;");
        statement.setString(1, newpass);
        statement.setString(2, name);
        int changes = statement.executeUpdate();
        statement.close();
        this.database.closeConnection();
    }

    /**
     * Lisää uuden käyttäjän tietokantaan, mikäli syötetyt tiedot ovat kunnolliset ja kyseisen nimistä käyttäjää ei vielä ole
     * @param user Uusi tallennettava käyttäjä User oliona
     * @return Mikäli tallennus onnistuu, palauttaa metodi parametrina annetun User olion takaisin, muussa tapauksessa palauttaa null.
     * @throws SQLException Heittää SQL virheen, jos SQL yhteydessö on jotain vikaa
     */
    @Override
    public User save(User user) throws SQLException {
        if (user.getUsername().equals("") || user.getPassword().equals("")) {
            this.feedback = "Kirjoita kunnolliset tiedot!";
            return null;
        } else if (!checkContainsName(user.getUsername())) { //Jos ei sisällä haluttua nimeä, luo tilin
            this.feedback = "Käyttäjä lisätty! Voit nyt kirjautua sisään tiedoilla.";
            directInsertToDatabase(user);
            return user;
        } else { //jos nimi on jo varattu ei tee mitään ja palauuttaa null
            this.feedback = "Käyttäjänimi on jo käytössä!";
            return null;
        }
    }
    
    /**
     * Metodi palauttaa palaute Stringin (liittyy kirjautumisen onnistumiseen)
     * @return UserDao:n palaute String, määritellään kirjautumisvaiheessa
     */
    public String getSaveFeedBack() {
        return this.feedback;
    }

    /**
     * Tämän metodin avulla voidaan yksittäisen käyttäjän mitä tahansa tietoja muokata.
     * @param user Käyttäjän uudet tiedot User oliona
     * @param usernameOfPreviousVersion käyttäjän vanha nimi (toimii id:nä). Tämän avulla käyttäjä etsitään tietokannasta ja uudet tiedot tallennetaa päälle.
     * @return Mikäli tallennus onnistuu, niin palauttaa user parametrin, jos tallennus ei onnistu, palauttaa null
     * @throws SQLException Heittää SQL virheen, jos SQL yhteydessö on jotain vikaa
     */
    @Override
    public User update(User user, String usernameOfPreviousVersion) throws SQLException {
        if (checkContainsName(usernameOfPreviousVersion) && !checkContainsName(user.getUsername())) {             
            delete(usernameOfPreviousVersion);
            directInsertToDatabase(user);
            return user;
        } else { //jos ei löydy usernameOfPreviousVersion nimistä käyttäjää databasesta palauttaa null
            return null;
        }
    }

    /**
     * Poistaa yksittäisen käyttäjän tietoineen kokonaan tietokannasta
     * @param username Poistettavan käyttäjän nimi (id)
     * @throws SQLException Heittää SQL virheen, jos SQL yhteydessö on jotain vikaa
     */
    @Override
    public void delete(String username) throws SQLException {
        PreparedStatement statement = database.getConnection().prepareStatement("DELETE FROM Users WHERE name = ?;");
        statement.setString(1, username);
        int changes = statement.executeUpdate();
        statement.close();
        this.database.closeConnection();
    }

    /**
     * Tallentaa käyttäjän suoraan tietokantaan tutkimatta, onko kyseisen nimistä oliota jo tallennettu. Käytetään vain, jos etukäteen on jo varmistettu, että samannimistä äyttäjää ei ole.
     * @param user Tallennettava käyttäjä User oliona
     * @throws SQLException Heittää SQL virheen, jos SQL yhteydessö on jotain vikaa
     */
    @Override
    public void directInsertToDatabase(User user) throws SQLException {
        PreparedStatement statement;
        int changes;
        statement = database.getConnection().prepareStatement("INSERT INTO Users (name, password, questions, right) VALUES (?, ?, ?, ?);");
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setInt(3, user.getQuestions());
        statement.setInt(4, user.getRight());
        changes = statement.executeUpdate();
        statement.close();
        this.database.closeConnection();
    }
    
    
    
}
