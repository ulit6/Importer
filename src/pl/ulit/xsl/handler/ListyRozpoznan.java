/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ListyRozpoznan extends ReadJGPWorkSheet implements DbInsertMSSQL {

    private static final Logger logger = LoggerFactory.getLogger(ListyRozpoznan.class); 
    private final ArrayList<ListaRozpoznan> listyRozpoznan;
    private final Connection conn;
    public ListyRozpoznan(Connection conn){
      
        listyRozpoznan = new ArrayList<>();
        this.conn = conn;
    }
    @Override
    public void read() {
        String newKodListy;
        String oldKodListy = "A01";
        ICD10 rozpoznanie;
        String kodICD10;
        int ranga;
        String oldTyp="H";
        String newTyp;
       
        ArrayList<ICD10> rozpoznania = new ArrayList<>();
         Cell cell;
        
        Iterator<Row> rowIterator = sheet.iterator();
        skipRows(rowIterator,4);
       
        while(rowIterator.hasNext()){
            
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
          
            newKodListy = getKodListy(cellIterator);
            newTyp = getTypListy(cellIterator);
            if(isNewListaProcedur(oldKodListy, newKodListy)){
                ListaRozpoznan lr = ListaRozpoznan.newInstance(oldKodListy, oldTyp, rozpoznania);
                listyRozpoznan.add(lr);
                oldKodListy=newKodListy;
                oldTyp=newTyp;
                rozpoznania = new ArrayList<>();
            }
            cell = cellIterator.next();
             if(isBlankCell(cell)){
                break;
            }
            kodICD10 = getKodICD10(cell);
           
            
            String nazwa = getNazwaICD10(cellIterator);
            rozpoznanie = ICD10.newInstance(kodICD10, nazwa);
            rozpoznania.add(rozpoznanie);       
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

    @Override
    public void wstawMSSQL() throws SQLException {
         logger.info("Wstaw MSSQL Listy rozpoznan");
         String insertLICD1="INSERT INTO IMPORTER.JGP.LICD1(KRPZ,NRPZ) "+
                "VALUES(?,?)";
         PreparedStatement psInsertLICD1 = conn.prepareStatement(insertLICD1);
         String selectLICD1="SELECT IRPZ FROM IMPORTER.JGP.LICD1 WHERE KRPZ=?";
         PreparedStatement psSelectLICD1 = conn.prepareStatement(selectLICD1);
         String insertLLRPZ="INSERT INTO IMPORTER.JGP.LLRPZ(WPRM,KLRP,TLRP) "+
                  " VALUES(?,?,?)";
         PreparedStatement psInsertLLRPZ = conn.prepareStatement(insertLLRPZ);
     
         String insertLDRPZ="INSERT INTO IMPORTER.JGP.LDRPZ(ILRP,IRPZ) VALUES(?,?)";
         PreparedStatement psInsertLDRPZ = conn.prepareStatement(insertLDRPZ);
         String lastIdentity = "SELECT @@IDENTITY";
         PreparedStatement psLastIdentity = conn.prepareStatement(lastIdentity);
         
         for(ListaRozpoznan lista:listyRozpoznan){
             int ilrp;
             insertLLRPZ(psInsertLLRPZ,lista);
             ilrp = lastIdentity(psLastIdentity);
             ArrayList<ICD10> rozpoznania=lista.getRozpoznania();
             for(ICD10 rozpoznanie:rozpoznania){
                 int irpz = selectLICD1(psSelectLICD1,rozpoznanie.getKod());
               
                 if(irpz==0){
                     insertLICD1(psInsertLICD1,rozpoznanie);
                     irpz = lastIdentity(psLastIdentity);
                 }
                 insertLDRPZ(psInsertLDRPZ, ilrp, irpz);
             }
             
        }
      
    }
    
    private void insertLLRPZ(PreparedStatement ps,ListaRozpoznan lista) throws SQLException{
      
          ps.setInt(1, wprm);
          ps.setString(2, lista.getKod());
          ps.setString(3, lista.getTyp());
          ps.execute();
    }
    
    private Integer lastIdentity(PreparedStatement ps) throws SQLException{
        Integer identity = 0;
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            identity = rs.getInt(1);
        }
        return identity;
    }
    private int selectLICD1(PreparedStatement ps,String krpz) throws SQLException{
        int irpz = 0;
        ps.setString(1, krpz);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            irpz = rs.getInt("IRPZ");
        }
        return irpz;
    }
    private void insertLICD1(PreparedStatement ps,ICD10 rozpoznanie) throws SQLException{
        
        ps.setString(1, rozpoznanie.getKod());
        ps.setString(2, rozpoznanie.getNazwa());
        ps.execute();
    }
    private void insertLDRPZ(PreparedStatement ps,int irlp,int irzp) throws SQLException{
        
        ps.setInt(1, irlp);
        ps.setInt(2, irzp);
        ps.execute();       
    }
}
