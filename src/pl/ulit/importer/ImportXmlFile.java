package pl.ulit.importer;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ulit6
 */
public class ImportXmlFile  extends ReadXmlFile implements Import{
    
     final Logger logger = LoggerFactory.getLogger(ImportXmlFile.class);
     
     public ImportXmlFile(String afilename,DefaultHandler ahandler) throws ParserConfigurationException, SAXException, FileNotFoundException, IOException, SQLException
     {        
        super(afilename,ahandler);
     }
     public String getWersja()
     {
         ParserKomunikat ps = (ParserKomunikat)handler;
         return ps.getWersja();
     }
     public String getTyp()
     {
         ParserKomunikat ps = (ParserKomunikat)handler;
         return ps.getTyp();
     }

     @Override
    void wstaw() throws SQLException {          
              throw new UnsupportedOperationException("Not supported yet.");     
    }

    @Override
    public void start() throws SAXException, IOException, SQLException {
          logger.info("Nowy import xml file" + filename);
    }
     
    
}
