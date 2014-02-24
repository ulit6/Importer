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
        String extension;
        String[] split = filename.split("\\.");
        logger.info("Split length: "+ split.length);
        Integer wersja = Integer.parseInt(split[split.length-2]);
        Integer w=filename.lastIndexOf(".");
        extension = filename.substring(w+1, filename.length());
        if(wersja==5)
        {
            return new JGPv5(filename);
        }
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
