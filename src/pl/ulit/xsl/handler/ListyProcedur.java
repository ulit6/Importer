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
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author pawel
 */
public class ListyProcedur extends ReadJGPWorkSheet implements DbInsertMSSQL {

    private static final Logger logger = LoggerFactory.getLogger(ListyProcedur.class); 
    private final ArrayList<ListaProcedur> listyProcedur;
    private final Connection conn;
    private Wrjgp wrjgp;
    public ListyProcedur(Connection conn){
        listyProcedur = new ArrayList<>();
        this.conn = conn;
    }
     
    public void setWrjgp(Wrjgp wrjgp){
        this.wrjgp = wrjgp;
    }
    @Override
    public void read() {
        String newKodListy;
        String oldKodListy = "A02";
        ICD9 procedura;
        String kodICD9;
        int ranga;
        String newTyp;
        String oldTyp="H";
        HashMap<ICD9,Integer> procedury = new HashMap<ICD9,Integer>() ;
         Cell cell;
        
        Iterator<Row> rowIterator = sheet.iterator();
        skipRows(rowIterator,4);
       
        while(rowIterator.hasNext()){
            
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
          
            newKodListy = getKodListy(cellIterator);
            newTyp = getTypListy(cellIterator);
            if(isNewListaProcedur(oldKodListy, newKodListy)){
                ListaProcedur lp = ListaProcedur.newInstance(oldKodListy, oldTyp, procedury);
                listyProcedur.add(lp);
                oldKodListy=newKodListy;
                oldTyp = newTyp;
                procedury = new HashMap<>();
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
             ranga = numericCelltoInt(cell);     
        }
        if(isStringCell(cell)){
             ranga = stringCelltoInt(cell);
       }
       return ranga; 
    }
    
    private String getNazwaICD9(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        return cell.getStringCellValue();
    }

    @Override
    public void wstawMSSQL() throws SQLException {
        logger.info("Wstaw MSSQL Listy procedur");
         String insertLICD9="INSERT INTO IMPORTER.JGP.LICD9(WICD,KPRC,NPRC) "+
                "VALUES(?,?,?)";
         PreparedStatement psInsertLICD9 = conn.prepareStatement(insertLICD9);
         String selectLICD9="SELECT IPRC FROM IMPORTER.JGP.LICD9 WHERE KPRC=? AND WICD=?";
         PreparedStatement psSelectLICD9 = conn.prepareStatement(selectLICD9);
         String insertLLRPC="INSERT INTO IMPORTER.JGP.LLPRC(WPRM,KLST,TLST) "+
                  " VALUES(?,?,?)";
         PreparedStatement psInsertLLRPC = conn.prepareStatement(insertLLRPC);
         String insertLDLPR="INSERT INTO IMPORTER.JGP.LDLPR(ILPR,IPRC,RPRC) VALUES(?,?,?)";
         PreparedStatement psInsertLDLPR = conn.prepareStatement(insertLDLPR);
         String lastIdentity = "SELECT @@IDENTITY";
         PreparedStatement psLastIdentity = conn.prepareStatement(lastIdentity);
         
         int ilpr=0;
         for(ListaProcedur lista:listyProcedur){
             insertLLRPC(psInsertLLRPC,lista);
             ilpr = lastIdentity(psLastIdentity);
             Map<ICD9,Integer> procedury = lista.getProcedury();
             for (Map.Entry<ICD9, Integer> entry : procedury.entrySet()) {
                 ICD9 icd9 = entry.getKey();
                 Integer ranga = entry.getValue();
                 int iprc = selectLICD9(psSelectLICD9, icd9.getKod(),wrjgp.getWicd());
                 if( iprc == 0){
                     insertLICD9(psInsertLICD9,wrjgp.getWicd(), icd9);
                     iprc = lastIdentity(psLastIdentity);
                 }
                 insertLDLPR(psInsertLDLPR, ilpr, iprc, ranga);
             }
        }
    }
    
    private void insertLLRPC(PreparedStatement ps,ListaProcedur lista) throws SQLException{
         ps.setInt(1, this.wprm);
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
    
    private int selectLICD9(PreparedStatement ps,String kprc,int wicd) throws SQLException{
        int iprc = 0;
        ps.setString(1, kprc);
        ps.setInt(2, wicd);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            iprc = rs.getInt("IPRC");
        }
        return iprc;
    }
    
    private void insertLICD9(PreparedStatement ps,int wicd,ICD9 procedura) throws SQLException{
        ps.setInt(1, wicd);
        ps.setString(2, procedura.getKod());
        ps.setString(3, procedura.getNazwa());
        ps.execute();
    }
    
    private void insertLDLPR(PreparedStatement ps,int ilpr,int iprc,int ranga) throws SQLException{   
        ps.setInt(1, ilpr);
        ps.setInt(2, iprc);
        ps.setInt(3, ranga);
        ps.execute();       
    }
}
