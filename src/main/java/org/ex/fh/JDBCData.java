/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;

/**
 *
 * @author dgmin
 */
@Dependent
public class JDBCData {
    
    private final String jdbcDriver = "org.mariadb.jdbc.Driver";
    private final String dbURL = "jdbc:mariadb://localhost:3306/praktikum";
    private Connection connection = null;
    
    public JDBCData() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(jdbcDriver);
            
            connection = DriverManager.getConnection(dbURL, "dba", "dba");
           
        }
        catch (SQLException ex) {
            Logger.getLogger(JDBCData.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("\nMariaDB-Treiber nicht verfügbar!\n");
        }
    }
    
    private void requestInsertNewAccount(){
    
    }
}
