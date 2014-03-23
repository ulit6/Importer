package pl.ulit.importer;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
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
public  abstract class ReadXmlFile implements Import{
    
    
   private static final Logger logger = LoggerFactory.getLogger(ReadXmlFile.class); 
   protected DefaultHandler handler;
   protected SAXParserFactory spfac;
   protected SAXParser sp;
   private static final String OUTPUT_FOLDER = System.getProperty("user.home");
   
   protected String filename;
   
   public ReadXmlFile(String afilename) throws ParserConfigurationException, SAXException, FileNotFoundException, IOException, SQLException
   {
           //Create a "parser factory" for creating SAX parsers
           spfac = SAXParserFactory.newInstance();

           //Now use the parser factory to create a SAXParser object
           sp = spfac.newSAXParser();
     
           filename=afilename;       
           unzip();
   }
    @Override
   public void start() throws SAXException, IOException,SQLException
   {
       logger.info("Start parsownia: " + filename);
       sp.parse(filename,handler);
  
      
   }
   
   public void unzip() throws FileNotFoundException, IOException
   {
       byte[] buffer = new byte[1024];
       
            ZipInputStream zis = new ZipInputStream(new FileInputStream(filename));
            ZipEntry ze = zis.getNextEntry();
            while(ze!=null)
            {
                String fileName = ze.getName();
                File newFile = new File(this.OUTPUT_FOLDER+File.separator+fileName);
                filename = newFile.getAbsolutePath();
                logger.info(newFile.getAbsolutePath());
                FileOutputStream fos = new FileOutputStream(newFile);   
                int len;
                

                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
 
                fos.close();   
                
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
    	    zis.close();
        
   }
   abstract void wstaw() throws SQLException; 
}
