/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;

import java.util.ArrayList;
import java.util.HashMap;
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
public class ListyRozpoznan extends ReadWorkSheet implements SheetHandler {

    private static final Logger logger = LoggerFactory.getLogger(ListyRozpoznan.class); 
    private final ArrayList<ListaRozpoznan> listyRozpoznan;
    
    public ListyRozpoznan(HSSFSheet asheet){
      
        super(asheet);
        listyRozpoznan = new ArrayList<ListaRozpoznan>();
    }
    @Override
    public void start() {
        String newKodListy;
        String oldKodListy = "";
        ICD10 rozpoznanie;
        String kodICD10;
        int ranga;
        String typ;
       
        ArrayList<ICD10> rozpoznania = new ArrayList<ICD10>();
         Cell cell;
        
        Iterator<Row> rowIterator = sheet.iterator();
        skipRows(rowIterator,4);
       
        while(rowIterator.hasNext()){
            
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
          
            newKodListy = getKodListy(cellIterator);
            typ = getTypListy(cellIterator);
            if(isNewListaProcedur(oldKodListy, newKodListy)){
                logger.info("Nowy kod: "+newKodListy+" stary kod:"+oldKodListy);
                ListaRozpoznan lr = ListaRozpoznan.newInstance(oldKodListy, typ, rozpoznania);
                listyRozpoznan.add(lr);
                oldKodListy=newKodListy;
                rozpoznania = new ArrayList<ICD10>();
            }
            cell = cellIterator.next();
             if(isBlankCell(cell)){
                break;
            }
            kodICD10 = getKodICD10(cell);
           
            
            String nazwa = getNazwaICD10(cellIterator);
            rozpoznanie = ICD10.newInstance(kodICD10, nazwa);
            rozpoznania.add(rozpoznanie);
            logger.info("Rozpoznanie: "+rozpoznanie);
            
        }
      
        for(ListaRozpoznan lista:listyRozpoznan){
            logger.info(lista.toString());
        }
              
    }
    
    private String getKodListy(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        return cell.getStringCellValue();
    }
    
    private String getTypListy(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        return cell.getStringCellValue();
    }
    
    private boolean isNewListaProcedur(String oldKodListy,String newKodListy){
        
        return !newKodListy.equalsIgnoreCase(oldKodListy);
    }
    
    private String getKodICD10(Cell cell){
        
        return cell.getStringCellValue();
    }
    
   
    
    private String getNazwaICD10(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        return cell.getStringCellValue();
    }
}
