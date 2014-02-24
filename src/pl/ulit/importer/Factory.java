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
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ReadXmlFile.class); 
    public static void main(String args[]) {
       
        Database db = new Database("sqlserver");
        
      //  Database db = new Database("MySQL");
        db.connect("localhost",3306, "IMPORTER", "importer", "tD9lPrw5yzmL");
        Importer importer = ImporterFactory.createImporter("G:\\CWL_PRH_198_20130911_SW.PRH",db.getConnection(),"MSSQL");
        // Importer importer = ImporterFactory.createImporter("G:\\plik_parametryzujacy_v.5.xls",db.getConnection(),"MSSQL");
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
        
       }

    
}
