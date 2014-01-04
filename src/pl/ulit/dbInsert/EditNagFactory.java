/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ulit.dbInsert;

import java.sql.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ulit.prh.Prh;

/**
 *
 * @author ulit6
 */
public class EditNagFactory {
    private static final Logger logger = LoggerFactory.getLogger(EditNagFactory.class);
    
    public static DbInsert createEditNagFactory(String aDatabase,Connection acon,Prh aprh)
    {
        if("MYSQL".equals(aDatabase.toUpperCase()))
        {
            return new MySqlEditNag(acon, aprh);
        }
        logger.error("No such MySqlEditNag: "+aDatabase);
        throw new IllegalArgumentException("No such MySqlEditNag");
        
    }
    
}
