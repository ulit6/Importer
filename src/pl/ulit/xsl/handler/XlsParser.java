/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author pawel
 */
public abstract class XlsParser  {
   private final String fileName;
   protected final HSSFWorkbook wbp;
   private final FileInputStream file;
   public  XlsParser(String afileName) throws FileNotFoundException, IOException{
       fileName = afileName;
       file = new FileInputStream(new File(fileName));
       wbp = new HSSFWorkbook(file);
   }
   protected static String getTime(){
        long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(time);
        return sdf.format(date);
    }
   
   public abstract void parse() throws SQLException;
   

}

