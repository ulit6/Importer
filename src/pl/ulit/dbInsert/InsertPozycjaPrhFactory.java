/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ulit.dbInsert;

import java.sql.Connection;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ulit.MSSQLInsert.MSSQLInsertPozycjaPrh;
import pl.ulit.prh.PozycjaPrh;
import pl.ulit.prh.Prh;

/**
 *
 * @author ulit6
 */
public class InsertPozycjaPrhFactory {
     private static final Logger logger = LoggerFactory.getLogger(InsertPozycjaPrhFactory.class);
     
    public static DbInsert createInsertPozycjaPrh(String aDatabase,Connection acon,ArrayList<PozycjaPrh> apoList,Prh aprh)
    {
        if("MYSQL".equals(aDatabase.toUpperCase()))
        {
            return new MySqlInsertPozycjaPrh(acon, apoList, aprh);
        }
        if("MSSQL".equalsIgnoreCase(aDatabase.toUpperCase()))
        {
            return new MSSQLInsertPozycjaPrh(acon, apoList, aprh);
        }
        logger.error("No such InsertPozycjaPrh: "+aDatabase);
        throw new IllegalArgumentException("No such InsertPozycjaPrh");
        
    }
    
}
