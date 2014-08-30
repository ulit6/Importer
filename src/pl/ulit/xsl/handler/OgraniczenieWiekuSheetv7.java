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
import java.util.NoSuchElementException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author pawel
 */
public class OgraniczenieWiekuSheetv7 extends ReadJGPWorkSheet implements DbInsertMSSQL{
    
    private static final Logger logger = LoggerFactory.getLogger(OgraniczenieWiekuSheetv7.class);
    private final  List<OgraniczenieWiekuV7> listaOgraniczeniaWieku;
    private final Connection conn;

   
    public OgraniczenieWiekuSheetv7(Connection conn) {
       // super(asheet);
        listaOgraniczeniaWieku = new ArrayList<>();
        this.conn = conn;
    }
    @Override
    public void read() throws NoSuchElementException {
       int kodOgraniczeniaWieku;
       int ponizejGornaGranica;
       int powyzejDolnaGranica;
       String jednostka;
       Iterator<Row> rowIterator = sheet.iterator();
       skipRows(rowIterator,4);
       
       while(rowIterator.hasNext()){
           
           Row row = rowIterator.next();
           Iterator<Cell> cellIterator = row.cellIterator();
           kodOgraniczeniaWieku = getKodOgraniczeniaWieku(cellIterator);
           ponizejGornaGranica = getPonizejGornaGranica(cellIterator);
           powyzejDolnaGranica = getPowyzejDolnaGranica(cellIterator);
          // jednostka = getJednostka(cellIterator);
           
           listaOgraniczeniaWieku.add(OgraniczenieWiekuV7.newInstance(kodOgraniczeniaWieku, 
                    ponizejGornaGranica,  powyzejDolnaGranica));
       }
     
    }
    public int getKodOgraniczeniaWieku(Iterator<Cell> cellIterator){
         Cell cell = cellIterator.next();
         if(isBlankCell(cell))
             return 0;
         return numericCelltoInt(cell);
        
    }  
    public int getPonizejGornaGranica(Iterator<Cell> cellIterator){
         Cell cell = cellIterator.next();
         if(isBlankCell(cell))
             return 0;
         return numericCelltoInt(cell); 
    }    
    public int getPowyzejDolnaGranica(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
         if(isBlankCell(cell))
             return 0;
         return numericCelltoInt(cell);
    }
    
    

    @Override
    public void wstawMSSQL() throws SQLException {
        logger.info("Wstaw MSSQL OGRWK v7");
        String sql="INSERT INTO IMPORTER.JGP.OGRWK(WPRM,KOGR,GRGR,DLGR,JDGR) "+
                "VALUES(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        for(OgraniczenieWiekuV7 ograniczenie:listaOgraniczeniaWieku){
            ps.setInt(1, wprm);
            ps.setInt(2, ograniczenie.getKod());
            ps.setInt(3, ograniczenie.getPonizej());
            ps.setInt(4, ograniczenie.getPowyzej());
            ps.setString(5,ograniczenie.getJednostka());  
            ps.execute();
        }
    }
}
