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
public class ParametryJGPSheet extends ReadJGPWorkSheet implements DbInsertMSSQL {

    private static final Logger logger = LoggerFactory.getLogger(ParametryJGPSheet.class);
    private final List<ParametryJGP> parametry;
    private final Connection conn;
    
    public ParametryJGPSheet(Connection conn) {

        parametry = new ArrayList<>();
        this.conn = conn;
    }
  /*  public void setWprm(int wprm){
        this.wprm = wprm;
    }
    public void setSheet(HSSFSheet sheet){
        this.sheet = sheet;
    }  */
    @Override
    public void read() {
        
        Iterator<Row> rowIterator = sheet.iterator();
        skipRows(rowIterator,5);
           while(rowIterator.hasNext()){
           
              Row row = rowIterator.next();
              Iterator<Cell> cellIterator = row.cellIterator();
              Cell cell = cellIterator.next();
              if(isBlankCell(cell)){
                break;
              }
              String typListy = getTypListy(cell);
              String kodListy = getKodListy(cellIterator);
              String kodJGP = getKodJGP(cellIterator);
              String kodAlgorytmu = getKodAlgorytmu(cellIterator);
              int kodPobytu = getKodPobytu(cellIterator);
              int kodWieku = getKodWieku(cellIterator);
              String kodICD9 = getKodICD9(cellIterator);
              int liczbaWystapienICD9 = getLiczbaWystapienICD9(cellIterator);
              String drugiKodICD9 = getDrugiKodICD9(cellIterator);
              int drugaLiczbaWystapienICD9 = getDrugaLiczbaWystapienICD9(cellIterator);
              String rozpoznanie =getRozpoznanie( cellIterator);
              String kodICD10 = getKodICD10(cellIterator);
              String drugiKodICD10 = getDrugiKodICD10(cellIterator);
              String plec = getPlec(cellIterator);
              int kodPrzyjecia = getKodPrzyjecia(cellIterator);
              int kodWypisu = getKodWypisu(cellIterator);
              String negatywnaListaProcedur =  getNegatywnaListaProcedur(cellIterator);
             // i++;
              String negatywnaListaRozpoznan = getNegatywnaListaRozpoznan(cellIterator);
              parametry.add(ParametryJGP.newInstance(typListy, kodListy, kodJGP, 
                      kodAlgorytmu, kodPobytu, kodWieku, kodICD9, liczbaWystapienICD9,
                      drugiKodICD9, drugaLiczbaWystapienICD9, rozpoznanie, kodICD10,
                      drugiKodICD10, plec, kodPrzyjecia, kodWypisu, negatywnaListaProcedur, 
                      negatywnaListaRozpoznan));
           }
           
    }
    public String getTypListy(Cell cell){

        return cell.getStringCellValue();
    }
    public String getKodListy(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        return cell.getStringCellValue();
    }
    public String getKodJGP(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        return cell.getStringCellValue();
    }
    public String getKodAlgorytmu(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        return cell.getStringCellValue();
    }
    public int getKodPobytu(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        if(isBlankCell(cell))
             return 0;
         return numericCelltoInt(cell); 
    }
    public int getKodWieku(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        if(isBlankCell(cell))
             return 0;
         return numericCelltoInt(cell); 
    }
    public String getKodICD9(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        if(isBlankCell(cell))
             return "";
         return cell.getStringCellValue(); 
    }
    public int getLiczbaWystapienICD9(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        if(isBlankCell(cell))
             return 0;
         return numericCelltoInt(cell); 
    }
    public String getDrugiKodICD9(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        if(isBlankCell(cell))
             return "";
         return cell.getStringCellValue(); 
    }
    public int getDrugaLiczbaWystapienICD9(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        if(isBlankCell(cell))
             return 0;
         return numericCelltoInt(cell); 
    }
    public String getRozpoznanie(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        if(isBlankCell(cell))
             return "";
         return cell.getStringCellValue(); 
    }
    public String getKodICD10(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        if(isBlankCell(cell))
             return "";
         return cell.getStringCellValue(); 
    }
    public String getDrugiKodICD10(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        if(isBlankCell(cell))
             return "";
         return cell.getStringCellValue(); 
    }
    public String getPlec(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        if(isBlankCell(cell))
             return "";
         return cell.getStringCellValue(); 
    }
    public int getKodPrzyjecia(Iterator<Cell> cellIterator){
        Cell cell = cellIterator.next();
        if(isBlankCell(cell))
             return 0;
         return numericCelltoInt(cell); 
    }
    public int getKodWypisu(Iterator<Cell> cellIterator){
        
        Cell cell = cellIterator.next();
        if(isBlankCell(cell))
             return 0;
         return numericCelltoInt(cell); 
    }
    public String getNegatywnaListaProcedur(Iterator<Cell> cellIterator){
         if(cellIterator.hasNext()){
            Cell cell = cellIterator.next();
            if(isBlankCell(cell))
                return "";
            return cell.getStringCellValue(); 
             }
             else{
            return "";
        }
    }
    public String getNegatywnaListaRozpoznan(Iterator<Cell> cellIterator){
        if(cellIterator.hasNext()){
            Cell cell = cellIterator.next();
            if(isBlankCell(cell))
                return "";
            return cell.getStringCellValue(); 
            }
            else{
            return "";
        }
    }

    @Override
    public void wstawMSSQL() throws SQLException {
        logger.info("Wstaw MSSQL parametry JGP;");
        String insertPGJGP="INSERT INTO IMPORTER.JGP.PGJGP(IJGP,TLST,KLST,KALG) "+
                "VALUES(?,?,?,?)";
        
        PreparedStatement psInsertJGP = conn.prepareStatement(insertPGJGP);
        String selectLGJGP = "SELECT IJGP FROM IMPORTER.JGP.LGJGP WHERE KJGP=? AND WPRM=?";
        PreparedStatement psSelectLGJGP = conn.prepareStatement(selectLGJGP);
        String lastIdentity = "SELECT @@IDENTITY";
        PreparedStatement psLastIdentity = conn.prepareStatement(lastIdentity);
        String insertPGJOP = "INSERT INTO IMPORTER.JGP.PGJOP(IPGJ,IOGR) VALUES(?,?)";
        PreparedStatement psInsertPGJOP = conn.prepareStatement(insertPGJOP);
        String selectKOGRP = "SELECT IOGR FROM IMPORTER.JGP.OGRPB WHERE WPRM=? AND KOGR=?";
        PreparedStatement psSelectKOGRP = conn.prepareStatement(selectKOGRP);
        String insertPGJOW = "INSERT INTO IMPORTER.JGP.PGJOW(IPGJ,IOGR) VALUES(?,?)";
        PreparedStatement psInsertPGJOW = conn.prepareStatement(insertPGJOW);
        String selectKOGRW = "SELECT IOGR FROM IMPORTER.JGP.OGRWK WHERE WPRM=? AND KOGR=?";
        PreparedStatement psSelectKOGRW = conn.prepareStatement(selectKOGRW);
        String insertPGPPD = "INSERT INTO IMPORTER.JGP.PGPPD(IPGJ,ILPR,LWPR) VALUES(?,?,?)";
        PreparedStatement psInsertPGPPD = conn.prepareStatement(insertPGPPD);
        String selectLLPRC = "SELECT ILPR FROM IMPORTER.JGP.LLPRC WHERE WPRM = ? AND KLST = ?";
        PreparedStatement psSelectLLPRC = conn.prepareStatement(selectLLPRC);
        String insertPGDPD = "INSERT INTO IMPORTER.JGP.PGDPD(IPGJ,ILPR,LWPR) VALUES(?,?,?)";
        PreparedStatement psInsertPGDPD = conn.prepareStatement(insertPGDPD);
        String selectLLRPZ = "SELECT ILRP FROM IMPORTER.JGP.LLRPZ WHERE WPRM = ? AND KLRP = ?";
        PreparedStatement psSelectLLRPZ = conn.prepareStatement(selectLLRPZ);
        String insertPGWRZ = "INSERT INTO IMPORTER.JGP.PGWRZ(IPGJ,ILRP) VALUES(?,?)";
        PreparedStatement psInsertPGWRZ = conn.prepareStatement(insertPGWRZ);
        String insertPGPRW = "INSERT INTO IMPORTER.JGP.PGPRW(IPGJ,ILRP) VALUES(?,?)";
        PreparedStatement psInsertPGPRW = conn.prepareStatement(insertPGPRW);
        String insertPGDRW = "INSERT INTO IMPORTER.JGP.PGDRW(IPGJ,ILRP) VALUES(?,?)";
        PreparedStatement psInsertPGDRW = conn.prepareStatement(insertPGDRW);
        String insertPGLNP = "INSERT INTO IMPORTER.JGP.PGLNP(IPGJ,ILPR) VALUES(?,?)";
        PreparedStatement psInsertPGLNP = conn.prepareStatement(insertPGLNP);
        String insertPGLNR = "INSERT INTO IMPORTER.JGP.PGLNR(IPGJ,ILRP) VALUES(?,?)";
        PreparedStatement psInsertPGLNR = conn.prepareStatement(insertPGLNR);
        for(ParametryJGP parametr:parametry){
               String kodJGP = parametr.getKodJGP();
               int ijgp = selectLGJGP(psSelectLGJGP, kodJGP);
               insertPGJGP(psInsertJGP, ijgp, parametr.getTypListy(), parametr.getKodListy(), parametr.getKodAlgorytmu());
               int identity = lastIdentity(psLastIdentity);
               if(parametr.getKodPobytu()!=0){
                   int iogr = selectKOGRP(psSelectKOGRP, parametr.getKodPobytu());
                   insertPGJOP(psInsertPGJOP, identity, iogr);
               }
               if(parametr.getKodWieku()!=0){
                   int iogr = selectKOGRW(psSelectKOGRW, parametr.getKodWieku());
                   insertPGJOW(psInsertPGJOW,identity,iogr);
               }
               if(!parametr.getKodICD9().equals("")){
                   
                   int ilpr = selectLLPRC(psSelectLLPRC, parametr.getKodICD9());
                   int liczbaWystapien = parametr.getLiczbaWystapienICD9();
                   if(liczbaWystapien==0){
                       liczbaWystapien = 1;
                   }
                   insertPGPPD(psInsertPGPPD, identity, ilpr, liczbaWystapien);
               }
               if(!parametr.getDrugiKodICD9().equals("")){
                  
                   int ilpr = selectLLPRC(psSelectLLPRC, parametr.getDrugiKodICD9());
                   int liczbaWystapien = parametr.getDrugaLiczbaWystapienICD9();
                   if(liczbaWystapien==0){
                       liczbaWystapien = 1;
                   }
                   insertPGDPD(psInsertPGDPD, identity, ilpr, liczbaWystapien);
               }
               if(!parametr.getRozpoznanie().equals("")){
                   int ilpr = selectLLRPZ(psSelectLLRPZ, parametr.getRozpoznanie());
                   insertPGWRZ(psInsertPGWRZ, identity, ilpr);
               }
               if(!parametr.getKodICD10().equals("")){
                   int ilpr = selectLLRPZ(psSelectLLRPZ, parametr.getKodICD10());
                   insertPGPRW(psInsertPGPRW, identity, ilpr);
               }
               if(!parametr.getDrugiKodICD10().equals("")){
                   int ilpr = selectLLRPZ(psSelectLLRPZ, parametr.getDrugiKodICD10());
                   insertPGDRW(psInsertPGDRW, identity, ilpr);
               }
               if(!parametr.getListaNegatywnychProcedur().equals("")){
                   int ilpr = selectLLPRC(psSelectLLPRC, parametr.getListaNegatywnychProcedur());
                   insertPGLNP(psInsertPGLNP, identity, ilpr);
               }
               if(!parametr.getListaNegatywnychRozpoznan().equals("")){
                   int ilpr = selectLLRPZ(psSelectLLRPZ, parametr.getListaNegatywnychRozpoznan());
                   insertPGLNR(psInsertPGLNR,identity,ilpr);
               }
           }
    }
    
    private int lastIdentity(PreparedStatement ps) throws SQLException{
        int identity = 0;
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            identity = rs.getInt(1);
        }
        return identity;
    }
    
    private int selectLGJGP(PreparedStatement ps,String kodJGP) throws SQLException{
        int ijgp = 0;
        ps.setString(1, kodJGP);
        ps.setInt(2, this.wprm);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            ijgp = rs.getInt(1);
        }
        return ijgp;
    } 
    private void  insertPGJGP(PreparedStatement ps, int ijgp,String typListy,String kodListy,
            String kodAlgorytmu) throws SQLException{
        ps.setInt(1, ijgp);
        ps.setString(2, typListy);
        ps.setString(3, kodListy);
        ps.setString(4, kodAlgorytmu);
        ps.execute();
        
    }
    
    private int selectKOGRP(PreparedStatement ps,int kogr) throws SQLException{
        int iogr =0;
        ps.setInt(1, this.wprm);
        ps.setInt(2, kogr);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
           iogr = rs.getInt(1) ;
        }
        return iogr;
    }
    private void insertPGJOW(PreparedStatement ps, int ijgp,int iogr) throws SQLException{
      ps.setInt(1, ijgp);
      ps.setInt(2, iogr);
      ps.execute();
    };
    private int selectKOGRW(PreparedStatement ps,int kogr) throws SQLException{
        int iogr =0;
        ps.setInt(1, this.wprm);
        ps.setInt(2, kogr);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
           iogr = rs.getInt(1) ;
        }
        return iogr;
    }
    private void insertPGJOP(PreparedStatement ps, int ijgp,int iogr) throws SQLException{
      ps.setInt(1, ijgp);
      ps.setInt(2, iogr);
      ps.execute();
    };
    
    private int selectLLPRC(PreparedStatement ps,String klst) throws SQLException{
        int ilpr =0;
        ps.setInt(1, this.wprm);
        ps.setString(2, klst);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
           ilpr = rs.getInt(1) ;
        }
        return ilpr;
    }
    
    private void insertPGPPD(PreparedStatement ps,int ipgj,int ilpr,int lwpr) throws SQLException{
        ps.setInt(1, ipgj);
        ps.setInt(2, ilpr);
        ps.setInt(3, lwpr);
        ps.execute();
    }
    
    private void insertPGDPD(PreparedStatement ps,int ipgj,int ilpr,int lwpr) throws SQLException{
        ps.setInt(1, ipgj);
        ps.setInt(2, ilpr);
        ps.setInt(3, lwpr);
        ps.execute();
    }
    private int selectLLRPZ(PreparedStatement ps,String klst) throws SQLException{
        int ilrp =0;
        ps.setInt(1, this.wprm);
        ps.setString(2, klst);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
           ilrp = rs.getInt(1) ;
        }
        return ilrp;
    }
    private void insertPGWRZ(PreparedStatement ps,int ipgj,int ilrp) throws SQLException{
        ps.setInt(1, ipgj);
        ps.setInt(2, ilrp);
        ps.execute();
    }
    private void insertPGPRW(PreparedStatement ps,int ipgj,int ilrp) throws SQLException{
        ps.setInt(1, ipgj);
        ps.setInt(2, ilrp);
        ps.execute();
    }
    private void insertPGDRW(PreparedStatement ps,int ipgj,int ilrp) throws SQLException{
        ps.setInt(1, ipgj);
        ps.setInt(2, ilrp);
        ps.execute();
    }
    private void insertPGLNP(PreparedStatement ps,int ipgj,int ilpr) throws SQLException{
        ps.setInt(1, ipgj);
        ps.setInt(2, ilpr);
        ps.execute();
    }
    private void insertPGLNR(PreparedStatement ps,int ipgj,int ilrp) throws SQLException{
        ps.setInt(1, ipgj);
        ps.setInt(2, ilrp);
        ps.execute();
    }
}
