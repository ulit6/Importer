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
public class ImporterXml extends Importer {
    
    private static final Logger logger = LoggerFactory.getLogger(ImporterXml.class);
    
    
    public ImporterXml(String afilename,Connection acon,String aDatabase)
    {
        super(afilename,acon,aDatabase);
    }
   

    @Override
    public Import orderImport()  throws IOException,ParserConfigurationException,SAXException,UnsupportedOperationException, FileNotFoundException,SQLException
    
    {
        String extension;
        Integer w=filename.lastIndexOf(".");
        extension = filename.substring(w+1, filename.length());
       //Ustalenie typu i wersji nagłowka xml
        ImportXmlFile ixf = null;
      
        ixf = new ImportXmlFile(filename);
        ixf.start();
        
        String typ=ixf.getTyp();
        String wersja=ixf.getWersja();
        logger.info("Typ i wersja xml: "+ typ +" "+ wersja);
        if("prh".equalsIgnoreCase(typ))
        {
           
                return new ImportPrh(filename,con,database);
           
        }
       throw new UnsupportedOperationException("Not supported yet.");
    }
}
