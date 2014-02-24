/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.MSSQLInsert;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import org.slf4j.LoggerFactory;
import pl.ulit.dbInsert.DbInsert;
import pl.ulit.dbInsert.MySqlInsertJednostkiMiary;
import pl.ulit.prh.JednostkaMiary;

/**
 *
 * @author pawel
 */
public class MSSQLInsertJednostkiMiary implements DbInsert{
    private Connection con;
    private ArrayList<JednostkaMiary> jmList;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MSSQLInsertJednostkiMiary.class);
    
    public MSSQLInsertJednostkiMiary(Connection acon,ArrayList<JednostkaMiary> ajmList)
    {
        con = acon ;
        jmList = ajmList;
    }
    @Override
    public void wstaw() throws SQLException {
      CallableStatement cs ;
      logger.info("JM list rozmiar: " + jmList.size());
      Iterator<JednostkaMiary> ijm = jmList.iterator();
   
      String    sql = "EXEC IMPORTER.PRH.LJDMR_ADD ?,?,?";
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
