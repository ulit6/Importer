/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author pawel
 */
public class ListyProcedur extends ReadWorkSheet implements SheetHandler {

    private static final Logger logger = LoggerFactory.getLogger(ListyProcedur.class); 
    private final ArrayList<ListaProcedur> listyProcedur;
    
    public ListyProcedur(HSSFSheet asheet){
      
        super(asheet);
        listyProcedur = new ArrayList<ListaProcedur>();
    }
    @Override
    public void start() {
        String newKodListy;
        String oldKodListy = "";
        ICD9 procedura;
        String kodICD9;
        int ranga;
        String typ;
        HashMap<ICD9,Integer> procedury = new HashMap<ICD9,Integer>() ;
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
                ListaProcedur lp = ListaProcedur.newInstance(oldKodListy, typ, procedury);
                listyProcedur.add(lp);
                oldKodListy=newKodListy;
                procedury = new HashMap<ICD9,Integer>();
            }
            cell = cellIterator.next();
             if(isBlankCell(cell)){
                break;
            }
            kodICD9 = getKodICD9(cell);
            ranga = getRanga(cellIterator);
            
            String nazwa = getNazwaICD9(cellIterator);
            procedura = ICD9.newInstance(kodICD9, nazwa);
            procedury.put(procedura, ranga);
            logger.info("Procedura: "+procedura);
        }
      
        for(ListaProcedur lista:listyProcedur){
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
    
    private String getKodICD9(Cell cell){
        
        return cell.getStringCellValue();
    }
    
    private int getRanga(Iterator<Cell> cellIterator){
        int ranga = 0 ;
        Cell cell = cellIterator.next();
        if(isNumericCell(cell)){         
             ranga = NumericCelltoInt(cell);     
        }
        if(isStringCell(cell)){
             ranga = StringCelltoInt(cell);
       }
       return ranga; 
    }
    
    private String getNazwaICD9(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        return cell.getStringCellValue();
    }
}
