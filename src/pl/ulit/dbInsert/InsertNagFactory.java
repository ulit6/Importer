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
public class InsertNagFactory {
    private static final Logger logger = LoggerFactory.getLogger(InsertNagFactory.class);
    
    public static DbInsert createNag(String aDatabase,Connection acon,Prh aprh)
    {
        System.err.println("InsertNagFactory:" +aprh);
        if("MYSQL".equals(aDatabase.toUpperCase()))
        {
            return new MySqlInsertNag(acon, aprh);
        }
        logger.error("No such InsertNagFactory: "+aDatabase);
        throw new IllegalArgumentException("No such InsertNagFactory");
        
    }
    
}
