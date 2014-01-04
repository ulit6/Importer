package pl.ulit.importer;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ulit6
 */
public abstract class Importer {
    protected String filename;
    protected Connection con;
    protected String database;
    public Importer(String afilename,Connection acon,String aDatabase)
    {
        filename=afilename;
        database= aDatabase;
        con = acon;
        
    }
    
    abstract  Import  orderImport() throws IOException,ParserConfigurationException,SAXException,UnsupportedOperationException, FileNotFoundException,SQLException;
    
}
