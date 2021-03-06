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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author pawel
 */
public class OgraniczeniePobytuSheetv6 extends ReadJGPWorkSheet implements DbInsertMSSQL{
    
    private static final Logger logger = LoggerFactory.getLogger(MechanizmOsobodni.class);
    private final  List<OgraniczenieWiekuV6> listaOgraniczeniaWieku;
    private final Connection conn;

   
    public OgraniczeniePobytuSheetv6(Connection conn) {
       // super(asheet);
        listaOgraniczeniaWieku = new ArrayList<>();
        this.conn = conn;
    }
    @Override
    public void read() {
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
           jednostka = getJednostka(cellIterator);

           listaOgraniczeniaWieku.add(OgraniczenieWiekuV6.newInstance(kodOgraniczeniaWieku, 
                   jednostka, ponizejGornaGranica,  powyzejDolnaGranica));
       }
      /* for(OgraniczenieWiekuV6 ograniczenie:listaOgraniczeniaWieku){
           System.out.println(ograniczenie);
       }*/
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
    
    private String getJednostka(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        if(isBlankCell(cell)){
             return "";
        }
        return cell.getStringCellValue();      
    }

    @Override
    public void wstawMSSQL() throws SQLException {
        logger.info("Wstaw MSSQL OGRPB v6");
        String sql="INSERT INTO IMPORTER.JGP.OGRPB(WPRM,KOGR,GRGR,DLGR,JDGR) "+
                "VALUES(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        for(OgraniczenieWiekuV6 ograniczenie:listaOgraniczeniaWieku){
            ps.setInt(1, wprm);
            ps.setInt(2, ograniczenie.getKod());
            ps.setInt(3, ograniczenie.getPonizej());
            ps.setInt(4, ograniczenie.getPowyzej());
            ps.setString(5,ograniczenie.getJednostka());  
            ps.execute();
        }
    }
}
