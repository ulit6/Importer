package pl.ulit.importer;
import java.sql.Connection;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ImporterFactory {
    
    private static final Logger logger = LoggerFactory.getLogger(ImporterFactory.class);
    public static Importer createImporter(String afilename,Connection acon,String aDatabase,ArrayList<Observer> observers)
    {
   
        String extension = ImporterFactory.extractExtension(afilename);
        if(extension.equals("xml"))
        {
            return new ImporterXml(afilename,acon,aDatabase,observers);
        } else if(extension.equals("PRH"))
        {
            return new ImporterXml(afilename,acon,aDatabase,observers);
        }
        if("xls".equalsIgnoreCase(extension))
        {
            return new ImporterXls(afilename, acon, aDatabase,observers);
        }
        throw new IllegalArgumentException("Nieobsługiwany typ importu");
    }
    
    public static String extractExtension(String fileName){
         int  index=fileName.lastIndexOf(".");
         return fileName.substring(index+1, fileName.length());
    }

    
}
