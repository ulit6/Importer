package pl.ulit.importer;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.LoggerFactory;
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
public class Factory implements Subject,Runnable{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Factory.class); 
    private final Connection connection;
    private final String fileName;
    private final String rdbms;
    private final ArrayList<Observer> observers;
    
    private  Factory(Connection connection,String fileName,String rdbms,Observer observer){
        this.observers = new ArrayList<>();
        this.connection = connection;
        this.fileName = fileName;
        this.rdbms = rdbms;
        registerObserver(observer);
    }
    
    private void  go() throws IllegalArgumentException,IOException, ParserConfigurationException, SAXException, UnsupportedOperationException, FileNotFoundException, 
            SQLException,IllegalStateException{
        notifyObservers(Factory.getTime()+" Info: Początek importu: ");
        Importer importer = ImporterFactory.createImporter(this.fileName,this.connection,rdbms,observers);
        Import imp = importer.orderImport();
        imp.start();
        
    }
    
    private static String getTime(){
        long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(time);
        return sdf.format(date);
    }
    public static Factory newInstance(Connection connection,String fileName,String rdbms,Observer observer){
        return new Factory(connection, fileName,rdbms,observer);
    }
  
    @Override
    public final void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for(Observer ob: observers){
            ob.update(message);
        }          
    }

    @Override
    public void run() {
        try {
            go();
        } catch (IllegalArgumentException | IOException | ParserConfigurationException | SAXException | UnsupportedOperationException | IllegalStateException | SQLException ex ) {
             notifyObservers(Factory.getTime()+" Error: "+ ex.getLocalizedMessage());
             notifyObserversGUI(ex);    
        }
    }

    @Override
    public void notifyObserversGUI(Throwable ex) {
        for(Observer ob: observers){
            ob.updateGUI(ex);
        }
    }
   
    
}
