/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author pawel
 */
public class WersjaJGP extends ReadJGPWorkSheet implements DbInsertMSSQL {

    private static final Logger logger = LoggerFactory.getLogger(WersjaJGP.class); 
    private  Wrjgp wrjgp;
    private final Connection conn;

    
    public WersjaJGP(Connection conn)
    {
        this.conn=conn;
    }
   
    @Override
    public void read() {
            
        int i=1;
        int walg = 0;
        int wicd = 0;
        int roko = 0;
        int msod = 0;
        int msdo = 0;
          Cell cell;
        Iterator<Row> rowIterator = sheet.iterator();
        skipRows(rowIterator,1);
        
        while(rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            cellIterator.next();
            while(cellIterator.hasNext())
            {       
                switch(i){
                    case 1:    cell = cellIterator.next();
                               wicd = numericCelltoInt(cell);
                               break;
                    case 2:    cell = cellIterator.next();
                               walg = numericCelltoInt(cell);
                               break;
                    case 3:    cell = cellIterator.next();
                               wprm = numericCelltoInt(cell);
                               break;    
                    case 4:    cell = cellIterator.next();
                               roko = numericCelltoInt(cell);
                               break;   
                    case 5:    cell = cellIterator.next();
                               msod = numericCelltoInt(cell);
                               break;      
                    case 6:    cell = cellIterator.next();
                               msdo = numericCelltoInt(cell);
                               break; 
                    default:  
                                break;
                    
                }
                
                
            }
            i++;
        }
        
        wrjgp = new Wrjgp(wprm, walg, wicd, roko, msod, msdo);
        
    }
    public int getWprm(){
        return wrjgp.getWprm();
    }
    public Wrjgp getWrjgp(){
        return this.wrjgp;
    }
    @Override
    public void wstawMSSQL() throws SQLException {
        logger.info("Wstaw MSSQL wrjgp");
        String sql="INSERT INTO IMPORTER.JGP.WRJGP VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, wrjgp.getWprm());
        ps.setInt(2, wrjgp.getWalg());
        ps.setInt(3, wrjgp.getWicd());
        ps.setInt(4, wrjgp.getRoko());
        ps.setInt(5, wrjgp.getMsod());
        ps.setInt(6, wrjgp.getMsdo());
        ps.execute();
      
    }
    
}
