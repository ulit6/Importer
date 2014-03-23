/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;

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
public class OgraniczenieWiekuSheet extends ReadWorkSheet implements SheetHandler{
    
    private static final Logger logger = LoggerFactory.getLogger(MechanizmOsobodni.class);
    private final  List<OgraniczenieWieku> listaOgraniczeniaWieku;
    public OgraniczenieWiekuSheet(HSSFSheet asheet) {
        super(asheet);
        listaOgraniczeniaWieku = new ArrayList<OgraniczenieWieku>();
    }

    @Override
    public void start() {
       int kodOgraniczeniaWieku;
       int ponizejGornaGranica;
       int pomiedzyDolnaGranica;
       int pomiedzyGornaGranica;
       int powyzejDolnaGranica;
       Iterator<Row> rowIterator = sheet.iterator();
       skipRows(rowIterator,4);
       
       while(rowIterator.hasNext()){
           
           Row row = rowIterator.next();
           Iterator<Cell> cellIterator = row.cellIterator();
           kodOgraniczeniaWieku = getKodOgraniczeniaWieku(cellIterator);
           ponizejGornaGranica = getPonizejGornaGranica(cellIterator);
           pomiedzyDolnaGranica = getPomiedzyDolnaGranica(cellIterator);
           pomiedzyGornaGranica = getPomiedzyGornaGranica(cellIterator);
           powyzejDolnaGranica = getPomiedzyDolnaGranica(cellIterator);
           listaOgraniczeniaWieku.add(OgraniczenieWieku.newInstance(kodOgraniczeniaWieku, 
                   ponizejGornaGranica, pomiedzyGornaGranica, pomiedzyDolnaGranica, 
                   powyzejDolnaGranica));
       }
       for(OgraniczenieWieku ogr: listaOgraniczeniaWieku){
           logger.info(ogr.toString());
       }
    }
    public int getKodOgraniczeniaWieku(Iterator<Cell> cellIterator){
         Cell cell = cellIterator.next();
         if(isBlankCell(cell))
             return 0;
         return NumericCelltoInt(cell);
        
    }  
    public int getPonizejGornaGranica(Iterator<Cell> cellIterator){
         Cell cell = cellIterator.next();
         if(isBlankCell(cell))
             return 0;
         return NumericCelltoInt(cell); 
    }    
    public int getPomiedzyDolnaGranica(Iterator<Cell> cellIterator){
         Cell cell = cellIterator.next();
         if(isBlankCell(cell))
             return 0;
         return NumericCelltoInt(cell); 
    }
    public int getPomiedzyGornaGranica(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
         if(isBlankCell(cell))
             return 0;
         return NumericCelltoInt(cell);
    }
    public int getPowyzejDolnaGranica(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
         if(isBlankCell(cell))
             return 0;
         return NumericCelltoInt(cell);
    }
}
