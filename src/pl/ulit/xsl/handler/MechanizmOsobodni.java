/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;

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
public class MechanizmOsobodni extends ReadJGPWorkSheet {
    private static final Logger logger = LoggerFactory.getLogger(MechanizmOsobodni.class);
    private final List<GrupaJGP> grupyJGP;
    private  HSSFSheet sheet;
    public MechanizmOsobodni(HSSFSheet sheet,List<GrupaJGP> agrupyJGP) {
        this.sheet = sheet;
        grupyJGP = agrupyJGP;
    }

    @Override
    public void read() {
        Cell cell;
        String kodJGP;
        String kodProduktu;
        String nazwaJGP;
        int dniPobytuFinansGrupa;
        int hospitalizacja1dnia;
        int ryczalt;
        Iterator<Row> rowIterator = sheet.iterator();
        skipRows(rowIterator,3);
        
        while(rowIterator.hasNext()){
            
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            kodJGP = getKodJGP(cellIterator);
            kodProduktu = getKodProduktu(cellIterator);
            nazwaJGP = getNazwaJGP(cellIterator);
            dniPobytuFinansGrupa = getDniPobytuFinansGrupa(cellIterator);
            hospitalizacja1dnia = getHospitalizacja1Dnia(cellIterator);
            ryczalt = getRyczalt(cellIterator);
            setMechanizmOsobodni(kodJGP, kodProduktu, nazwaJGP, dniPobytuFinansGrupa, hospitalizacja1dnia, ryczalt);
        }
        }
    
     private String getKodJGP(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        return cell.getStringCellValue();
     }
     private String getKodProduktu(Iterator<Cell> cellIterator){
         Cell cell = cellIterator.next();
         return cell.getStringCellValue();
     }
     private String getNazwaJGP(Iterator<Cell> cellIterator){
         Cell cell = cellIterator.next();
         return cell.getStringCellValue();
     }
     private int getDniPobytuFinansGrupa(Iterator<Cell> cellIterator){
         Cell cell = cellIterator.next();
         if(isBlankCell(cell))
            return 0;
       return numericCelltoInt(cell);
     }
     
     private int getHospitalizacja1Dnia(Iterator<Cell> cellIterator){
         Cell cell = cellIterator.next();
         if(isBlankCell(cell))
             return 0;
         return numericCelltoInt(cell);   
     }   
     
     private int getRyczalt(Iterator<Cell> cellIterator){
         Cell cell = cellIterator.next();
         if(isBlankCell(cell))
           return 0;
       return numericCelltoInt(cell);  
     }
     private void setMechanizmOsobodni(String kodJGP,String kodProduktu,String nazwaJGP,
             int dniPobytuFinansGrupa,int hospitalizacja1dnia,int ryczalt){
         GrupaJGP grupaJGP = GrupaJGP.newInstance(kodJGP, kodProduktu, nazwaJGP);
         if(grupyJGP.contains(grupaJGP)){
             int index = grupyJGP.indexOf(grupaJGP);
             GrupaJGP grupa = grupyJGP.get(index);
             grupa.setDniPobytuFinansGrupa(dniPobytuFinansGrupa);
             grupa.setHospitalizacja1Dnia(hospitalizacja1dnia);
             grupa.setRyczałt(ryczalt);
             
         }
         
     }
    }
    

