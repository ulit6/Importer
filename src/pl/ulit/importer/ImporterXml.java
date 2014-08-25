package pl.ulit.importer;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import pl.ulit.importerView.Observer;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ulit6
 */
public class ImporterXml extends Importer {
    
    private static final Logger logger = LoggerFactory.getLogger(ImporterXml.class);
    private String typ="";
    private String wersja="";
    public ImporterXml(String afilename,Connection acon,String aDatabase,ArrayList<Observer> observers)
    {
        super(afilename,acon,aDatabase,observers);
    }
   

    @Override
    public Import orderImport()  throws IOException,ParserConfigurationException,
           SAXException,UnsupportedOperationException, FileNotFoundException,SQLException{
      
        checkTypAndWersja();
        notifyObservers("Import pliku XML:" + filename);
        logger.info("Typ i wersja xml: "+ this.typ +" "+ this.wersja);
        notifyObservers("Typ i wersja xml: "+ this.typ +" "+ this.wersja);
        DefaultHandler handler = ParserFactory.newInstance(this.typ, con, database);         
        return new ImportPrh(filename, handler);
    }
    private void checkTypAndWersja() throws ParserConfigurationException, SAXException, IOException, FileNotFoundException, SQLException{
        
        DefaultHandler handler = ParserFactory.newInstance("", con, database);
        ImportXmlFile ixf = new ImportXmlFile(filename,handler);
        ixf.start();
        this.typ=ixf.getTyp();
        this.wersja=ixf.getWersja();
    }

    @Override
    public void registerObserver(Observer observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeObserver(Observer observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers(String message) {
         for(Observer ob: observers){
            ob.update(message);
        }
    }

    @Override
    public void notifyObserversGUI(Throwable ex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
