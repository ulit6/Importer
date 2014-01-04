package pl.ulit.importer;


import java.io.IOException;
import java.sql.SQLException;
import org.xml.sax.SAXException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ulit6
 */
public interface Import {
    
    public void start() throws SAXException, IOException,SQLException;
    
}
