/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author pawel
 */
public class ZakresyJGP extends ReadJGPWorkSheet implements DbInsertMSSQL{
    private static final Logger logger = LoggerFactory.getLogger(ZakresyJGP.class); 
    private final List<GrupaJGP> grupyJGP;
    private  ReadJGPWorkSheet sh ;
    private  HSSFSheet sheetMechOsob;
    private final Connection conn;
    
    public ZakresyJGP(Connection conn){
       this.grupyJGP = new ArrayList();
       this.conn = conn;  
    }
    public void setSheetZakresy(HSSFSheet sheet){
        this.sheet = sheet;
    } 
    public void setSheetMechOsob(HSSFSheet sheet){
        this.sheetMechOsob = sheet;
    }
    @Override
    public void read()  {
        
        
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        rowIterator.next();
        rowIterator.next();
        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
          
            Cell cell;
            Double d;
            String kodProduktu;
                
            cell = cellIterator.next();
            kodProduktu = cell.getStringCellValue();
            cell = cellIterator.next();
            String kod = cell.getStringCellValue();
            cell = cellIterator.next();
            String nazwa = cell.getStringCellValue();
            cell = cellIterator.next();
            d = new Double(cell.getNumericCellValue());
            int wph = d.intValue();
            cell = cellIterator.next();
            d = new Double(cell.getNumericCellValue());
            int wphp = d.intValue();
            cell = cellIterator.next();
            d = new Double(cell.getNumericCellValue());
            int l1d = d.intValue();
            grupyJGP.add(GrupaJGP.newInstance(kod, kodProduktu, nazwa, wph, wphp, l1d));
           
            
        }
       
        //mechanizm osobodni
        
        sh = new MechanizmOsobodni(this.sheetMechOsob, grupyJGP);
      //  sh.start();
       /* for(GrupaJGP grupa:grupyJGP){
            logger.info(grupa.toString());
            
        }*/
    }

    @Override
    public void wstawMSSQL() throws SQLException {
        logger.info("Wstaw MSSQL LGJGP");
        String sql="INSERT INTO IMPORTER.JGP.LGJGP(WPRM,KJGP,KPRD,NJGP,WHSP,WHPP,WLJD,LDFG,WPDD,WPRY) "+
                "VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        for(GrupaJGP grupa:grupyJGP){
            ps.setInt(1, this.wprm);
            ps.setString(2,grupa.getKodJGP());
            ps.setString(3, grupa.getKodProduktu());
            ps.setString(4,grupa.getNazwa());
            ps.setInt(5, grupa.getHospitalizacja());
            ps.setInt(6,grupa.getHospitalizacjaPlanowa());
            ps.setInt(7,grupa.getLeczenie1dnia());
            ps.setInt(8,grupa.getLeczenie1dnia());
            ps.setInt(9,grupa.getHospitalizacja1dnia());
            ps.setInt(10, grupa.getRyczałt());
            ps.execute();
        }
    }
    
}
