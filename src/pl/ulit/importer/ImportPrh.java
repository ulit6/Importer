package pl.ulit.importer;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
public class ImportPrh extends ReadXmlFile implements Import{
    private static final Logger logger = LoggerFactory.getLogger(ImportPrh.class);
    private ParserPrh pp;
    private Connection conn;
    private String database;
    public ImportPrh(String afilename,Connection aconn,String aDatabase) throws ParserConfigurationException, SAXException, FileNotFoundException, IOException, SQLException
    {     
        super(afilename,new ParserPrh(aconn,aDatabase));
        conn = aconn;
        database = aDatabase;
    }
    
    public ImportPrh(String afilename,DefaultHandler ahandler) throws
            ParserConfigurationException, SAXException, FileNotFoundException, 
            IOException, SQLException{
        
        super(afilename,ahandler);
        pp=(ParserPrh)ahandler;
    }
    @Override
    void wstaw() throws SQLException {
       
        pp.wstaw();
    }

    @Override
    public void start() throws SAXException, IOException, SQLException {
       this.wstaw();
    }
    
    
}
