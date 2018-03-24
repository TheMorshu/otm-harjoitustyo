//OK!
package Dao;

import java.sql.*;
import java.util.*;

public interface Dao<Type> {

    Type findOne(String username) throws SQLException;

    List<Type> findAll() throws SQLException;

    Type saveOrUpdate(Type object, String oldUserName) throws SQLException;

    void delete(String username) throws SQLException;

}
