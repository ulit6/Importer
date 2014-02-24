/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;

import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ulit.importer.ReadXmlFile;

/**
 *
 * @author pawel
 */
public class WersjaJGPv5 implements SheetHandler {
  //  private final XSSFWorkbook workBook;
    private final HSSFSheet sheet;
    private static final Logger logger = LoggerFactory.getLogger(WersjaJGPv5.class); 
    private  Wrjgp wrjgp;
    public WersjaJGPv5(HSSFSheet aSheet)
    {
       sheet=aSheet;
    }

    @Override
    public void start() {
            
        int i=1;
        int wprm = 0;
        int walg = 0;
        int wicd = 0;
        int roko = 0;
        int msod = 0;
        int msdo = 0;
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        
          
        while(rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            
            while(cellIterator.hasNext())
            {
                Cell cell;
                Double d;
                cellIterator.next();
                switch(i){
                    case 1:    cell = cellIterator.next();
                               d = new Double( cell.getNumericCellValue());
                               wprm = d.intValue();
                               logger.info("Wprm:"+wprm);
                               break;
                    case 2:    cell = cellIterator.next();
                               d = new Double( cell.getNumericCellValue());
                               walg = d.intValue();
                               logger.info("Walg:"+walg);
                               break;
                    case 3:    cell = cellIterator.next();
                               d = new Double( cell.getNumericCellValue());
                               wicd = d.intValue();
                               logger.info("Wicd:"+wicd);
                               break;    
                    case 4:    cell = cellIterator.next();
                               d = new Double( cell.getNumericCellValue());
                               roko = d.intValue();
                               logger.info("Roko:"+roko);
                               break;   
                    case 5:    cell = cellIterator.next();
                               d = new Double( cell.getNumericCellValue());
                               msod = d.intValue();
                               logger.info("Msod:"+msod);
                               break;      
                    case 6:    cell = cellIterator.next();
                               d = new Double( cell.getNumericCellValue());
                               msdo = d.intValue();
                               logger.info("Msdo:"+msdo);
                               break; 
                    default:  
                                break;
                    
                }
                
                
            }
            i++;
        }
        
        wrjgp = new Wrjgp(wprm, walg, wicd, roko, msod, msdo);
        logger.info(wrjgp.toString());
    }
    
}
