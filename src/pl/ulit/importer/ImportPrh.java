package pl.ulit.importer;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ulit6
 */
public class ImportPrh extends ReadXmlFile  {
    private static final Logger logger = LoggerFactory.getLogger(ImportPrh.class);
    private ParserPrh pp;
    private InsertKomunikatPrh ob;
    public ImportPrh(String afilename,Connection aconn,String aDatabase) throws ParserConfigurationException, SAXException, FileNotFoundException, IOException, SQLException
    {   
       
       
        super(afilename);
        logger.info( "Nowy import PRH:" +afilename);
    //    ob = new InsertKomunikatPrh(aconn, aDatabase);
        pp = new ParserPrh(aconn,aDatabase);
   //     pp.registerObserver(ob);
   //     pp.registerObserverImport(ob);
        handler = pp;
  //      this.wstaw();
      //  pp.wstaw(aconn, aDatabase);

        
  
        

        
        
    }

    @Override
    void wstaw() throws SQLException {
        
      ob.Insert();
    }
    
    
}
