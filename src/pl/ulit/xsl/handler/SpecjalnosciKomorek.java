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
public class SpecjalnosciKomorek implements SheetHandler{
    private final HSSFSheet sheet;
    private static final Logger logger = LoggerFactory.getLogger(SpecjalnosciKomorek.class); 
    private final List<KomorkaOrg> komorki;
    
    public SpecjalnosciKomorek(HSSFSheet asheet){
        sheet = asheet;
        komorki = new ArrayList<>();
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
            
            cell = cellIterator.next();
            String kod = cell.getStringCellValue();
            cell = cellIterator.next();
            String nazwa = cell.getStringCellValue();
            komorki.add(KomorkaOrg.newInstance(kod, nazwa));
        }
        
        for(KomorkaOrg komorka:komorki){
            logger.info(komorka.toString());
        }
            
    }
    
}
