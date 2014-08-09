/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;

import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author pawel
 */
public  abstract class ReadWorkSheet {
    
    protected  HSSFSheet sheet;
    
  /*  
    public ReadWorkSheet(HSSFSheet asheet){
        
        sheet = asheet;
    }*/
    
    public void setSheet(HSSFSheet sheet){
        this.sheet = sheet;
    }  
    
    public boolean isNumericCell(Cell cell){
        
        return cell.getCellType()==0;
    }
    
    public boolean isStringCell(Cell cell){
        
        return cell.getCellType()==1;
    }
    
    public boolean isBlankCell(Cell cell){

        return cell.getCellType()==3;
    }
    
    public int numericCelltoInt(Cell cell){
        if(!isNumericCell(cell)){
            throw new IllegalArgumentException("Bad cell type");
        }
        Double cellValue = new Double(cell.getNumericCellValue());
        return cellValue.intValue();
    }
    
    public int stringCelltoInt(Cell cell){
        if(!isStringCell(cell)){
            throw new IllegalArgumentException("Bad cell type");
        }
        String cellValue = cell.getStringCellValue();
        boolean onlyDigits = cellValue.matches("^\\d{1,}$");
        if(onlyDigits){
            return stringDigitsValuetoInt(cellValue);
        }
        else{
            return charAndIntCellValuetoInt(cellValue);
        }
        
    }
    private int charAndIntCellValuetoInt(String stringValue){
        if(">2".equals(stringValue)){
            return 3;
        }
        throw new UnsupportedOperationException();
    }
    private int stringDigitsValuetoInt(String stringValue){
        return Integer.parseInt(stringValue);
    }
    
    public void skipRows(Iterator<Row> rowIterator,int number){
        for(int i=0;i<number;i++){
            rowIterator.next();
        }
    }
    
    public void skipCells(Iterator<Cell> cellIterator,int number){
        for(int i=0;i<number;i++){
            cellIterator.next();
        }
    }
    public abstract void read();
}