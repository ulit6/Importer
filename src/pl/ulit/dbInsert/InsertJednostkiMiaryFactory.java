package pl.ulit.dbInsert;


import java.sql.Connection;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ulit.prh.JednostkaMiary;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ulit6
 */
public class InsertJednostkiMiaryFactory {
    private static final Logger logger = LoggerFactory.getLogger(InsertJednostkiMiaryFactory.class);
    
    public static DbInsert createInsertJednostkiMiary(String aDatabase,Connection acon,ArrayList<JednostkaMiary> ajmList)
    {
        if("MYSQL".equals(aDatabase.toUpperCase()))
        {
            return new MySqlInsertJednostkiMiary(acon, ajmList);
        }
        logger.error("No such InsertJednostkiMiaryFactory: "+aDatabase);
        throw new IllegalArgumentException("No such InsertJednostkiMiaryFactory");
        
    }
    
}
