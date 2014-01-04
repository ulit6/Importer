package pl.ulit.importer;


import java.io.FileNotFoundException;
import java.io.IOException;
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
public class ImportXmlFile  extends ReadXmlFile{
    
     final Logger logger = LoggerFactory.getLogger(ImportXmlFile.class);
     
     public ImportXmlFile(String afilename) throws ParserConfigurationException, SAXException, FileNotFoundException, IOException
     {
        
        super(afilename);
        handler = new ParserKomunikat();
        logger.info("Nowy import xml file" + afilename);
         
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
     
    
}
