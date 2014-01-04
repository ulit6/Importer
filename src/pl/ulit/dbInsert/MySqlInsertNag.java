/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ulit.dbInsert;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.LoggerFactory;
import pl.ulit.prh.Prh;

/**
 *
 * @author ulit6
 */
public class MySqlInsertNag implements DbInsert  {
    private Connection con;
    private Prh prh;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MySqlInsertNag.class);
    
    public MySqlInsertNag(Connection acon,Prh aprh)
    {
        con = acon ;
        prh = aprh;
        
    }
    
    @Override
    public void wstaw() throws SQLException {
    CallableStatement cs ;
    String    sql = "CALL LNPRH_ADD(?,?,?,?)";
    cs=con.prepareCall(sql);
    cs.setInt(1,prh.getWerTech());
    cs.setDouble(2, Double.parseDouble(prh.getWersja()));
    cs.setInt(3, prh.getOddzNfz());
    cs.setString(4,prh.getCzasGen());
    logger.info("LNPRH_ADD");
    cs.executeQuery();  
    }
    
  /*  
    public void MysqlEdtNag() throws SQLException
    {
          CallableStatement cs;
          String    sql = "CALL LNPRH_EDT(?)";
          cs=con.prepareCall(sql);
          cs.setInt(1,prh.getWerTech());
          cs.executeQuery();
        
    }
    */
    
    
}
