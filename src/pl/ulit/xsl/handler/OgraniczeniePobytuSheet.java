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
public class OgraniczeniePobytuSheet extends ReadJGPWorkSheet implements DbInsertMSSQL{
    
    private static final Logger logger = LoggerFactory.getLogger(OgraniczeniePobytuSheet.class);
    private final  List<OgraniczeniePobytuV5> listaOgraniczeniaPobytu;
    private Connection conn;
    public OgraniczeniePobytuSheet(Connection conn) {
       // super(asheet);
        this.conn = conn;
        listaOgraniczeniaPobytu = new ArrayList<OgraniczeniePobytuV5>();
    }
    
    @Override
    public void read() {
       int kodOgraniczeniaPobytu;
       int ponizejGornaGranica;
       int pomiedzyDolnaGranica;
       int pomiedzyGornaGranica;
       int powyzejDolnaGranica;
       Iterator<Row> rowIterator = sheet.iterator();
       skipRows(rowIterator,4);
       
       while(rowIterator.hasNext()){
           
           Row row = rowIterator.next();
           Iterator<Cell> cellIterator = row.cellIterator();
           kodOgraniczeniaPobytu = getKodOgraniczeniaWieku(cellIterator);
           ponizejGornaGranica = getPonizejGornaGranica(cellIterator);
           pomiedzyDolnaGranica = getPomiedzyDolnaGranica(cellIterator);
           pomiedzyGornaGranica = getPomiedzyGornaGranica(cellIterator);
           powyzejDolnaGranica = getPomiedzyDolnaGranica(cellIterator);
           listaOgraniczeniaPobytu.add(OgraniczeniePobytuV5.newInstance(kodOgraniczeniaPobytu, 
                   ponizejGornaGranica, pomiedzyGornaGranica, pomiedzyDolnaGranica, 
                   powyzejDolnaGranica));
       }
     /*  for(OgraniczeniePobytu ogr: listaOgraniczeniaPobytu){
           logger.info(ogr.toString());
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
    public int getPomiedzyDolnaGranica(Iterator<Cell> cellIterator){
         Cell cell = cellIterator.next();
         if(isBlankCell(cell))
             return 0;
         return numericCelltoInt(cell); 
    }
    public int getPomiedzyGornaGranica(Iterator<Cell> cellIterator){
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
        logger.info("Wstaw MSSQL OGRPB");
        String sql="INSERT INTO IMPORTER.JGP.OGRPB(WPRM,KOGR,GRGR,DLGR,JDGR) "+
                "VALUES(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        for(OgraniczeniePobytuV5 ograniczenie:listaOgraniczeniaPobytu){
            ps.setInt(1, wprm);
            ps.setInt(2, ograniczenie.getKod());
            if(ograniczenie.getPonizejGornaGranica()!=0){
                ps.setInt(3, ograniczenie.getPonizejGornaGranica());
                ps.setInt(4,-1 );
            }
            else{
                ps.setInt(3, 255);
                ps.setInt(4, ograniczenie.getPowyzejDolnaGranica());
            }
            ps.setString(5, "D");
            ps.execute();
        }
    }
}
