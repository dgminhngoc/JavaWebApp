package util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import org.ex.fh.model.Service;

/**
 *
 * @author dgmin
 */
@Named(value = "jDBCBean")
@Dependent
public class JDBCBean {

    private final   String      driver      = "org.mariadb.jdbc.Driver";
    private final   String      dbURL       = "jdbc:mariadb://localhost:3306/praktikum";
    private         Connection  connection  = null;
    
    private List<Service> listService = new ArrayList<>();
    
    private Logger logger = Logger.getLogger(JDBCBean.class.getName());
    /**
     * Creates a new instance of JDBCBean
     */
    public JDBCBean() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(dbURL, "dba", "dba");
        } 
        catch (ClassNotFoundException ex) {
            logger.info("JDBC Connection is created successfully");
        } 
        catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    public void getListServiceFromJDBC() {
        String sql = "SELECT * FROM service ORDER BY SERVICE_NAME";
        
        try {
            ResultSet result = connection.createStatement().executeQuery(sql);
            while(result.next()) {
                //listService.add(new Service());
            }
        } 
        catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Service> getListService() {
        return listService;
    }

    public void setListService(List<Service> listService) {
        this.listService = listService;
    }
}
