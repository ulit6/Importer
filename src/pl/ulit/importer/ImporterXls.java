package pl.ulit.importer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import pl.ulit.xsl.handler.JGPv5;

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

    public ImporterXls(String afilename, Connection acon, String aDatabase) {
        super(afilename, acon, aDatabase);
    }

    @Override
    Import orderImport() throws IOException, ParserConfigurationException, SAXException, UnsupportedOperationException, FileNotFoundException, SQLException {
        logger.info("New importer XLS");
        logger.info("Filename: "+filename);
        String ver = filename.replaceAll("\\D+","");
        Integer wersja = Integer.parseInt(ver);
        /*if(wersja==5)
        {*/
                          return new JGPv5(filename,this.con,this.database,wersja);
       // }
        
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
