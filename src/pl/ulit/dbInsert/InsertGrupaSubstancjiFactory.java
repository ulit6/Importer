/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ulit.dbInsert;

import java.sql.Connection;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ulit.MSSQLInsert.MSSQLInsertGrupaSubstancji;
import pl.ulit.prh.GrupaSubstancji;
import pl.ulit.prh.Prh;

/**
 *
 * @author ulit6
 */
public class InsertGrupaSubstancjiFactory {
    private static final Logger logger = LoggerFactory.getLogger(InsertJednostkiMiaryFactory.class);
    
    public static DbInsert createInsertGrupaSubstancji(String aDatabase,Connection acon,ArrayList<GrupaSubstancji> agsList,Prh aprh)
    {
        if("MYSQL".equals(aDatabase.toUpperCase()))
        {
            return new MySqlInsertGrupaSubstancji(acon, agsList,aprh);
        }
        if("MSSQL".equals(aDatabase.toUpperCase()))
        {
            return new MSSQLInsertGrupaSubstancji(acon, agsList, aprh);
        }
        logger.error("No such InsertGrupaSubstancjiFactory: "+aDatabase);
        throw new IllegalArgumentException("No such InsertGrupaSubstancjiFactory");
        
    }
    
}
