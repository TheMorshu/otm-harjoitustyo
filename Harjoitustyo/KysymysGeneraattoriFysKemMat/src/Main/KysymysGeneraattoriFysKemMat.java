
package Main;

import Dao.Database;
import UI.Login;
import java.sql.SQLException;


public class KysymysGeneraattoriFysKemMat {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Database users = new Database("jdbc:sqlite:users.db");
        Login kirjautuminen = new Login(users);
        kirjautuminen.aloita();
        
        
        
        
        
        
        
        
    }
    
}
