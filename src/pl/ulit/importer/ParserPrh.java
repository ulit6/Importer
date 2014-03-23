package pl.ulit.importer;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import pl.ulit.dbInsert.DbInsert;
import pl.ulit.dbInsert.InsertNagFactory;
import pl.ulit.prh.Element;
import pl.ulit.prh.GrupaSubstancji;
import pl.ulit.prh.JednostkaMiary;
import pl.ulit.prh.Katalog;
import pl.ulit.prh.PozycjaPrh;
import pl.ulit.prh.Prh;
import pl.ulit.prh.Taryfa;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ulit6
 */
public class ParserPrh extends DefaultHandler implements SubjectElement,SubjectImport{
    
    private Prh prh;
    private String temp;
    private JednostkaMiary jm;
    private ArrayList<JednostkaMiary> jmList = new ArrayList<>();
    private GrupaSubstancji gs;
    private ArrayList<GrupaSubstancji> gsList = new ArrayList<GrupaSubstancji>();
    private Taryfa taryfa;
    private ArrayList<Taryfa> taList = new ArrayList<Taryfa>();
    private Katalog katalog;
    private ArrayList<Katalog> kaList = new ArrayList<Katalog>();
    private PozycjaPrh pozycjaprh;
    private ArrayList<PozycjaPrh> poList = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(ParserPrh.class);
    private ArrayList<ObserverElement> observers = new ArrayList<ObserverElement>();
    private ArrayList<ObserverImport> observersImport = new ArrayList<ObserverImport>();
    private Connection connection;
    private String database;
    
    public ParserPrh(Connection aconnection,String adatabase)
    {
        this.connection=aconnection;
        this.database=adatabase;
    }
    @Override
    public void characters(char[] buffer, int start, int length) {
              temp = new String(buffer, start, length);
       }
    @Override
    public void startElement(String uri, String localName,
                     String qName, Attributes attributes) throws SAXException {
              temp = "";
           
              if (qName.equalsIgnoreCase("komunikat")) {
                     prh = new Prh(attributes.getValue("typ"),attributes.getValue("wersja"),
                             attributes.getValue("czas-gen"),Integer.parseInt(attributes.getValue("wer-tech")),
                             Integer.parseInt(attributes.getValue("oddz-nfz")));
                     logger.info(prh.toString());
                   //  this.notifyObservers(prh);
              }
              if (qName.equalsIgnoreCase("jednostka-miary")) {
                  jm = new JednostkaMiary(Integer.parseInt(attributes.getValue("kod")),attributes.getValue("nazwa"),attributes.getValue("status"));
                  jmList.add(jm);
                  logger.info(jm.toString());
                 // this.notifyObservers(jm);
              }
              if (qName.equalsIgnoreCase("grupa-substancji")) {
                  gs = new GrupaSubstancji(Integer.parseInt(attributes.getValue("kod")),attributes.getValue("nazwa"),
                          attributes.getValue("status"),Integer.parseInt(attributes.getValue("jednostka")));
                  gsList.add(gs);
                  logger.info(gs.toString());
                 // this.notifyObservers(gs);
              }
              if (qName.equalsIgnoreCase("taryfa")) {
                  taryfa = new Taryfa(attributes.getValue("data-od"),attributes.getValue("data-do"),Double.parseDouble(attributes.getValue("taryfa-bazowa")),
                          Double.parseDouble(attributes.getValue("lb-roz-jednostek")));
                  
                  taList.add(taryfa);
                  katalog.addTaryfa(taryfa);
              }
              if (qName.equalsIgnoreCase("katalog")) {
                  katalog = new Katalog(attributes.getValue("kod-katalogu"));
                  
              }
              if (qName.equalsIgnoreCase("pozycja")) {
                  pozycjaprh = new PozycjaPrh(Integer.parseInt(attributes.getValue("kod")),attributes.getValue("ean"),attributes.getValue("symbol"),
                          Integer.parseInt(attributes.getValue("grupa-substancji-kod")),
                          attributes.getValue("nazwa"),attributes.getValue("postac"),attributes.getValue("dawka"),attributes.getValue("opak"),
                          attributes.getValue("podm-odp"),
                          Double.parseDouble(attributes.getValue("ilosc-subst")),Double.parseDouble(attributes.getValue("lb-sztuk")),
                          attributes.getValue("status"));
                  
       }

       }
    
   // @Override

    @Override
      public void endElement(String uri, String localName, String qName) 
                     throws SAXException {
       if(qName.equalsIgnoreCase("komunikat")){
          
          try{
              logger.info("End element komunikat");
            // this.notifyObserversImport(1);
            //  this.wstaw(this.connection, this.database);
              this.wstaw();
          } 
          catch(SQLException e)
          {
              logger.error(e.getLocalizedMessage());
          }
                            
       }
       if (qName.equalsIgnoreCase("jednostka-miary")) {

           jm = null;
       }
       if (qName.equalsIgnoreCase("grupa-substancji")) {

           gs = null;
       }
       if (qName.equalsIgnoreCase("taryfa")) {

           taryfa = null;
       }
       if (qName.equalsIgnoreCase("katalog")) {
           kaList.add(katalog);
           pozycjaprh.setKatalog(katalog);
           
       }
       if (qName.equalsIgnoreCase("pozycja")) {
           poList.add(pozycjaprh);
           logger.info(pozycjaprh.toString());
          // this.notifyObservers(pozycjaprh);
           pozycjaprh = null;
           katalog = null;
       }
    }
    
    public void wstaw(Connection acon,String aDB) throws SQLException
    {
      String wersja = prh.getWersja();
      logger.info("Wersja: " + wersja);
      InsertKomunikatPrh  InsPrh = new InsertKomunikatPrh(acon, prh, poList, gsList, jmList,aDB,wersja);
      InsPrh.Insert();
    }
    public void wstaw() throws SQLException
    {
      logger.info("Start procedury wsta" + System.nanoTime());
      String wersja = prh.getWersja();
      logger.info("Wersja: " + wersja);
      logger.info("Rozmiar jmlist: "+ jmList.size());
      InsertKomunikatPrh  InsPrh = new InsertKomunikatPrh(this.connection, this.prh, this.poList, this.gsList, this.jmList,this.database,wersja);
      InsPrh.Insert();
    }
    @Override
    public void registerObserver(ObserverElement observer) {
        logger.info("Observer register");
        observers.add(observer);
    }

    @Override
    public void removeObserver(ObserverElement observer) {
        logger.info("Observer remove");
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Element element) {
        
        for(ObserverElement ob: observers){
            logger.info("Notify observers");
            ob.update(element);
        }
    }

   

    @Override
    public void registerObserverImport(ObserverImport observer) {
        observersImport.add(observer);
    }

    @Override
    public void removeObserverImport(ObserverImport observer) {
        observersImport.remove(observer);
    }

    @Override
    public void notifyObserversImport(Integer astatus) throws SQLException {
        for(ObserverImport ob: observersImport){
            logger.info("Notify observers import");
            ob.update(astatus);
        }
    }
    
}
