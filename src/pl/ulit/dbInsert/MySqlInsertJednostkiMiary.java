/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ulit.dbInsert;

/**
 *
 * @author ulit6
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import org.slf4j.LoggerFactory;
import pl.ulit.prh.JednostkaMiary;

/**
 *
 * @author pawel
 */
public class MySqlInsertJednostkiMiary implements DbInsert  {
    private Connection con;
    private ArrayList<JednostkaMiary> jmList;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MySqlInsertJednostkiMiary.class);
    
    public MySqlInsertJednostkiMiary(Connection acon,ArrayList<JednostkaMiary> ajmList)
    {
        con = acon ;
        jmList = ajmList;
        
    }

    @Override
    public void wstaw() throws SQLException {
    CallableStatement cs ;
     logger.info("JM list rozmiar: " + jmList.size());
    Iterator<JednostkaMiary> ijm = jmList.iterator();
   
    String    sql = "CALL LJDMR_ADD(?,?,?)";
    cs=con.prepareCall(sql);
    while(ijm.hasNext())
         {
             JednostkaMiary jm = ijm.next();
             
                cs.setInt(1, jm.getKod());
                cs.setString(2,jm.getNazwa() );
                cs.setString(3, jm.getStatus());
                logger.info("LJDMR_ADD: " + jm.toString());
                cs.execute() ;   
         }       
    }
    
}

