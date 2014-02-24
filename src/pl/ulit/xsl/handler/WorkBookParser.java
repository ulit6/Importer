/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author pawel
 */
public abstract class WorkBookParser {
    private XSSFWorkbook workBook;
  //  private WorkBookHandler workBookHandler;
    public WorkBookParser(XSSFWorkbook aWorkBook)
    {
        workBook=aWorkBook;
      //  workBookHandler=aWorkBookHandler;
    }
    public abstract void parse();
    public void setWorkBook(XSSFWorkbook aWorkBook)
    {
        workBook=aWorkBook; 
    }
}
