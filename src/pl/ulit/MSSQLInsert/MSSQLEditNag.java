/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.MSSQLInsert;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.LoggerFactory;
import pl.ulit.dbInsert.DbInsert;
import pl.ulit.prh.Prh;

/**
 *
 * @author pawel
 */
public class MSSQLEditNag implements DbInsert {

    private Connection con;
    private Prh prh;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MSSQLEditNag.class);
    
    public MSSQLEditNag(Connection acon,Prh aprh)
    {
         con = acon ;
         prh = aprh;
    }
    
    @Override
    public void wstaw() throws SQLException {
        CallableStatement cs;
        String    sql = "EXEC IMPORTER.PRH.LNPRH_EDT ?";
        cs=con.prepareCall(sql);
        cs.setInt(1,prh.getWerTech());
        cs.execute();
    }
    
}
