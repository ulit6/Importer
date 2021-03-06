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
import java.util.logging.Level;
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
    public Database(String aserver,String ahost,Integer aport ,String adatabase, String alogin, String apassword) throws DatabaseException
    {   
        this.server=aserver;
        this.host=ahost;
        this.port=aport;
        this.database=adatabase;
        this.login=alogin;
        this.password=apassword;
        
        driver=Database.DriverFactory(aserver);
        try {
            this.connect(this.host, this.port, this.database, this.login, this.password);
        } catch (DatabaseException ex) {
            throw new DatabaseException(ex);
        }
        
    }
    public static String DriverFactory(String aserver)
    {
        if("MySQL".equals(aserver))
        {
            return "com.mysql.jdbc.Driver";
        }
        if("sqlserver".equals(aserver))
        {
            return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        }
        if("MSSQL".equals(aserver))
        {
            return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        }
        throw new IllegalArgumentException("No such Driver");
        
    }
 
    

    public void connect(String ahost,Integer aport ,String adatabase, String alogin, String apassword) throws DatabaseException{
        String url="";
        try{
            Class.forName(driver);
        } catch(java.lang.ClassNotFoundException e) {
            logger.error(e.getLocalizedMessage());
            throw new DatabaseException(e);
          
        }
        if("MySQL".equals(this.server))
        {
             url="jdbc:"+server+"://"+ahost+":"+aport.toString()+"/"+adatabase;
        }
        if("sqlserver".equals(this.server))
        {
             url="jdbc:"+server+"://"+ahost+":"+aport.toString()+";database="+adatabase;
        }
        if("MSSQL".equals(this.server)) {
             url="jdbc:sqlserver://"+ahost+":"+aport.toString()+";database="+adatabase;
        }
        logger.debug(url);
        try {
           this.con = DriverManager.getConnection(url, alogin, apassword);
           
        } catch (SQLException e) {
            logger.error(e.getLocalizedMessage());
            throw new DatabaseException(e);
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
