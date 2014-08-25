/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class SpecjalnosciKomorek extends ReadJGPWorkSheet implements DbInsertMSSQL{
    private static final Logger logger = LoggerFactory.getLogger(SpecjalnosciKomorek.class); 
    private final List<KomorkaOrg> komorki;
    private final Connection conn;
  
    
    public SpecjalnosciKomorek(Connection conn){
        this.conn = conn;
        komorki = new ArrayList<>();
    }
    @Override
    public void read() throws IllegalStateException{
        
        Iterator<Row> rowIterator = sheet.iterator();
        skipRows(rowIterator, 3);
        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell;   
            cell = cellIterator.next();
            String kod=cell.getStringCellValue();
            if(kod.equals("")){
                break;
            }
            cell = cellIterator.next();
            String nazwa = cell.getStringCellValue();
            komorki.add(KomorkaOrg.newInstance(kod, nazwa));
        }
            
    }

    @Override
    public void wstawMSSQL() throws SQLException {
        String kkor = null;
        logger.info("Wstaw MSSQL LKMOR");
        String sql="INSERT INTO IMPORTER.JGP.LKMOR VALUES(?,?)";
        String sqlSelect="SELECT KKOR FROM IMPORTER.JGP.LKMOR WHERE KKOR=?";
        PreparedStatement ps2 = conn.prepareStatement(sqlSelect);
        PreparedStatement ps = conn.prepareStatement(sql);
        for(KomorkaOrg komorka:komorki){
            ps2.setString(1, komorka.getKod());
            ResultSet resultSet = ps2.executeQuery();
            while(resultSet.next()){
                kkor = resultSet.getString(1);
            }
            if(kkor == null){
                ps.setString(1, komorka.getKod());
                 ps.setString(2, komorka.getNazwa());
         //   logger.info(komorka.toString());
                 ps.execute();
            }
        }
        
    }
    
}
