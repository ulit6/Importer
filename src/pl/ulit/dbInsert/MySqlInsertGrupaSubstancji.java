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
import pl.ulit.prh.GrupaSubstancji;
import pl.ulit.prh.Prh;

/**
 *
 * @author pawel
 */
public class MySqlInsertGrupaSubstancji implements DbInsert  {
    private Connection con;
    private ArrayList<GrupaSubstancji> gsList;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MySqlInsertGrupaSubstancji.class);
    private Prh prh;
    
    public MySqlInsertGrupaSubstancji(Connection acon,ArrayList<GrupaSubstancji> agsList,Prh aprh)
    {
        con = acon ;
        gsList = agsList;
        prh = aprh;
    }

    @Override
    public void wstaw() throws SQLException {
    CallableStatement cs ;
    Iterator<GrupaSubstancji> igs = gsList.iterator();
    String    sql = "CALL LGRSU_ADD(?,?,?,?,?)";
    cs=con.prepareCall(sql);
    while(igs.hasNext())
         {
             GrupaSubstancji gs = igs.next();
             
              cs.setInt(1, prh.getWerTech());
              cs.setInt(2, gs.getKod());
              cs.setString(3,gs.getNazwa().trim() );
              cs.setInt(4, gs.getJednostka());
              cs.setString(5, gs.getStatus());
              logger.info("LJDMR_ADD: " + gs.toString());
              cs.executeQuery();
         }       
    }
    
}

