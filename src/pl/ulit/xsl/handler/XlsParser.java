/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;
import java.io.File;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import pl.ulit.importer.Import;

/**
 *
 * @author pawel
 */
public abstract class XlsParser  {
   private final String fileName;
   protected final HSSFWorkbook wbp;
   private final FileInputStream file;
   public  XlsParser(String afileName) throws FileNotFoundException, IOException
   {
       fileName = afileName;
       file = new FileInputStream(new File(fileName));
       wbp = new HSSFWorkbook(file);
      // file.close();
   }
   
   public abstract void parse() throws SQLException;
   
/*   public void setWorkBookParser(WorkBookParser awbp)
   {
       wbp=awbp;
   }
  */ 
}

