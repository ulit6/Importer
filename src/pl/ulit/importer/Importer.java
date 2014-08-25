package pl.ulit.importer;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import pl.ulit.importerView.Observer;
import pl.ulit.importerView.Subject;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ulit6
 */
public abstract class Importer implements Subject{
    protected String filename;
    protected Connection con;
    protected String database;
    protected ArrayList<Observer> observers;
    
    public Importer(String filename,Connection con,String aDatabase,ArrayList<Observer> observers)
    {
        this.filename=filename;
        this.database= aDatabase;
        this.con = con;
        this.observers = observers;
        
    }
    protected static String getTime(){
        long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(time);
        return sdf.format(date);
    }
    abstract  Import  orderImport() throws IOException,ParserConfigurationException,SAXException,UnsupportedOperationException, FileNotFoundException,SQLException;
    
}
