package pl.ulit.importer;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import pl.ulit.MSSQLInsert.MSSQLInsertNag;
import pl.ulit.dbInsert.Database;
import pl.ulit.dbInsert.DatabaseException;
import pl.ulit.prh.Prh;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ulit6
 */
public class Factory {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Factory.class); 
    private final Connection connection;
    private final String fileName;
    private final String rdbms;
    
    private Factory(Connection connection,String fileName,String rdbms){
        this.connection = connection;
        this.fileName = fileName;
        this.rdbms = rdbms;
    }
    public final void  go() throws IOException, ParserConfigurationException, SAXException, UnsupportedOperationException, FileNotFoundException, SQLException{
        Importer importer = ImporterFactory.createImporter(this.fileName,this.connection,"MSSQL");
        Import imp = importer.orderImport();
        imp.start();
    }
    
    public static Factory newInstance(Connection connection,String fileName,String rdbms){
        return new Factory(connection, fileName,rdbms);
    }
    /*
    public static void main(String args[]) {
       
        Database db = new Database("sqlserver");
        try {
            //  Database db = new Database("MySQL");
            db.connect("localhost",1433, "AdventureWorks2012", "ulit", "56fd#23*6!mju");
        } catch (DatabaseException ex) {
            logger.error(ex.getLocalizedMessage());
        }
        //Importer importer = ImporterFactory.createImporter("G:\\CWL_PRH_198_20130911_SW.PRH",db.getConnection(),"MSSQL");
       //  Importer importer = ImporterFactory.createImporter("G:\\plik_parametryzujacy_v.5.xls",db.getConnection(),"MSSQL");
         Importer importer = ImporterFactory.createImporter("G:\\plik_parametryzujacy_v6.xls",db.getConnection(),"MSSQL");
        try {
        Import imp=importer.orderImport();
        imp.start();
        //   imp.wstaw();
        } catch (FileNotFoundException ex) {
        Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
        Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
        Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedOperationException ex) {
        Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
        Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();     
        
       }*/

    
}
