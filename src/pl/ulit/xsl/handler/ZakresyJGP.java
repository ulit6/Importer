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
public class ZakresyJGP implements SheetHandler {
    private final HSSFSheet sheet;
    private static final Logger logger = LoggerFactory.getLogger(ZakresyJGP.class); 
    private final List<GrupaJGP> grupyJGP;
    private  SheetHandler sh ;
    private final HSSFSheet sheetMechOsob;
    public ZakresyJGP(HSSFSheet aSheet,HSSFSheet sheetMechOsob)
    {
       this.grupyJGP = new ArrayList();
       sheet=aSheet;
       this.sheetMechOsob = sheetMechOsob;
    }

    @Override
    public void start() {
        
        
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
        sh.start();
        for(GrupaJGP grupa:grupyJGP){
            logger.info(grupa.toString());
            
        }
    }
    
}
