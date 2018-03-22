
package Main;

import Dao.Database;
import Dao.UsersDao;
import UI.Login;
import java.sql.SQLException;


public class KysymysGeneraattori {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Database users = new Database("jdbc:sqlite:users.db");
        Login kirjautuminen = new Login(users);
        kirjautuminen.aloita();
        
        
        
        
        
        
        
        
    }
    
}
