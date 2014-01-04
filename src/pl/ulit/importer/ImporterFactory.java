package pl.ulit.importer;
import java.sql.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ulit6
 */
public class ImporterFactory {
    
    private static final Logger logger = LoggerFactory.getLogger(ImporterFactory.class);
    public static Importer createImporter(String afilename,Connection acon,String aDatabase)
    {
        String extension;
        Integer w=afilename.lastIndexOf(".");
       
        extension = afilename.substring(w+1, afilename.length());
       logger.info("Extension: " + extension);
        if(extension.equals("xml"))
        {
            return new ImporterXml(afilename,acon,aDatabase);
        } else if(extension.equals("PRH"))
        {
            return new ImporterXml(afilename,acon,aDatabase);
        }
        
        throw new IllegalArgumentException("No such Importer");
    }
    
}
