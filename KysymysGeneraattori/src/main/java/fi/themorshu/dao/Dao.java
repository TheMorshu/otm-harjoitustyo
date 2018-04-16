//OK!
package fi.themorshu.dao;

import java.sql.*;
import java.util.*;

public interface Dao<Type> {

    Type findOne(String username) throws SQLException;

    List<Type> findAll() throws SQLException;
  
    Type save(Type object) throws SQLException;
    
    Type update(Type object, String oldUserName) throws SQLException;
    
    void delete(String username) throws SQLException;
    
    void setUpTableOnDatabase();
    
    void clearDatabase() throws SQLException;
    
    public void directInsertToDatabase(Type object) throws SQLException;
    

}
