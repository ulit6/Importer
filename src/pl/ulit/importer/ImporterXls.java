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
import pl.ulit.importerView.Observer;
import pl.ulit.xsl.handler.JGP;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ulit6
 */
public class ImporterXls extends Importer {

    private static final Logger logger = LoggerFactory.getLogger(ImporterXls.class);

    public ImporterXls(String afilename, Connection acon, String aDatabase,ArrayList<Observer> observers) {
        super(afilename, acon, aDatabase, observers);
    }

    @Override
    Import orderImport() throws IOException, ParserConfigurationException, SAXException, UnsupportedOperationException, FileNotFoundException, SQLException {
        
        logger.info("New importer XLS");
        logger.info("Filename: "+filename);
        notifyObservers(Importer.getTime()+" Info: Import pliku XLS: " + filename);
        String ver = filename.replaceAll("\\D+","");
        Integer wersja = Integer.parseInt(ver);
        
        return new JGP(filename,this.con,this.database,wersja,observers);
       
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
