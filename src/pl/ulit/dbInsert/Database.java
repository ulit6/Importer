/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.dbInsert;

/**
 *
 * @author ulit6
 */


import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Database {
    private String driver;
    private String server;
    private Connection con;
    private String host;
    private Integer port;
    private String login;
    private String password;
    private String database;
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    
    public Database(String aserver) {
        this.server=aserver;
        driver=Database.DriverFactory(aserver);
       
    }
    public Database(String aserver,String ahost,Integer aport ,String adatabase, String alogin, String apassword)
    {   
        this.server=aserver;
        this.host=ahost;
        this.port=aport;
        this.database=adatabase;
        this.login=alogin;
        this.password=apassword;
        
        driver=Database.DriverFactory(aserver);
        this.connect(this.host, this.port, this.database, this.login, this.password);
        
    }
    public static String DriverFactory(String aserver)
    {
        if("MySQL".equals(aserver))
        {
            return "com.mysql.jdbc.Driver";
        }
        if("MSSQL".equals(aserver))
        {
            return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        }
        
        throw new IllegalArgumentException("No such Driver");
        
    }
 
    

    public boolean connect(String ahost,Integer aport ,String adatabase, String alogin, String apassword){
        
        try{
            Class.forName(driver);
        } catch(java.lang.ClassNotFoundException e) {
            logger.error(e.getLocalizedMessage());
            return false;
        }
       
        String url="jdbc:"+server+"://"+ahost+":"+aport.toString()+"/"+adatabase;
        logger.info(url);
        try {
           this.con = DriverManager.getConnection(url, alogin, apassword);
            return true;
        } catch (SQLException e) {
            logger.error(e.getLocalizedMessage());
            
            return false;
        }
    }

    public void disconnect(){
        try {
            this.con.close();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    public Connection getConnection(){
        return this.con;
    }

   
}
