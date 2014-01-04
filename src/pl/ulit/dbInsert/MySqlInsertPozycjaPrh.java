/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ulit.dbInsert;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import org.slf4j.LoggerFactory;
import pl.ulit.prh.Katalog;
import pl.ulit.prh.PozycjaPrh;
import pl.ulit.prh.Prh;
import pl.ulit.prh.Taryfa;

/**
 *
 * @author ulit6
 */

public class MySqlInsertPozycjaPrh implements DbInsert  {
    private Connection con;
    private ArrayList<PozycjaPrh> poList;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MySqlInsertPozycjaPrh.class);
    Prh prh;
    
    public MySqlInsertPozycjaPrh(Connection acon,ArrayList<PozycjaPrh> apoList,Prh aprh)
    {
        con = acon ;
        poList = apoList;
        prh = aprh;
    }

    @Override
    public void wstaw() throws SQLException {
        
    CallableStatement cs ;
    Iterator<PozycjaPrh> poit = poList.iterator();
    String    sql = "CALL LPRHA_ADD(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    PreparedStatement ps;
    Integer idpr = 0;
    CallableStatement cs2;
    
    cs=con.prepareCall(sql);
    while(poit.hasNext())
         {
             PozycjaPrh po = poit.next();
             
              cs.setInt(1, prh.getWerTech());
              cs.setString(2, po.getEan());
              cs.setString(3,po.getSymbol() );
              cs.setInt(4, po.getGsl());
              cs.setString(5, po.getNazwa());
              cs.setString(6, po.getPostac());
              cs.setString(7, po.getDawka());
              cs.setString(8, po.getOpak());
              cs.setString(9, po.getPodmOdp());
              cs.setDouble(10, po.getIlosc());
              cs.setDouble(11, po.getSztuki());
              cs.setString(12,po.getStatus());
              cs.setString(13, po.getKod().toString());
              System.out.println();
              logger.info("LPRHA_ADD: " + po.getKod()+"|"+po.getIlosc());
              cs.executeQuery();
              Katalog ka = po.getKatalog();
              
              if(ka!=null)
              {
                  ps = con.prepareStatement("SELECT LAST_INSERT_ID()");
                  ResultSet rs = ps.executeQuery();
                  while(rs.next())
                  {
                         idpr=rs.getInt(1);                     
                  }
                   
                  String sql2 = "CALL TRPRH_ADD(?,?,?,?,?,?)";
                  cs2=con.prepareCall(sql2);
                  ArrayList<Taryfa> t = ka.getTaryfa();
                  Iterator<Taryfa> tait=t.iterator();
                  while(tait.hasNext())
                  {
                         Taryfa ta=tait.next();
                         cs2.setInt(1, idpr);
                         cs2.setString(2, ka.getKodKatalogu());
                         cs2.setString(3, ta.getDataOd());
                         cs2.setString(4, ta.getDataDo());
                         cs2.setDouble(5, ta.getTaryfaBazowa())   ;
                         cs2.setDouble(6, ta.getLbRoz());
                         cs2.executeQuery();         
                         
                 }
              }
              
             
         }       
    }
    
}
