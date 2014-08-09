/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.xml.sax.SAXException;
import pl.ulit.importer.Import;

/**
 *
 * @author pawel
 */
public class JGPv5 extends XlsParser implements Import,DbInsertMSSQL {
    private HSSFSheet sheet;
    private final Connection conn;
    private final String database;
    private final WersjaJGP wersjaJGP;
    private final ReadJGPWorkSheet specjalnosciKomorek;
    private final ReadJGPWorkSheet zakresyJGP;
    private final ReadJGPWorkSheet ograniczeniePobytu;
    private final ReadJGPWorkSheet ograniczenieWieku;
    private final ReadJGPWorkSheet listaRozpoznan;
    private final ReadJGPWorkSheet listaProcedur;
    private final ReadJGPWorkSheet parametryJGP;
    
    
    public JGPv5(String afileName,Connection conn,String database,int wprm) throws FileNotFoundException, IOException {
        super(afileName);
        this.conn = conn;
        this.database = database;
        wersjaJGP = new WersjaJGP(this.conn);
        specjalnosciKomorek = new SpecjalnosciKomorek(this.conn);
        zakresyJGP = new ZakresyJGP(this.conn);
        ograniczeniePobytu = OgraniczeniePobytuFactory(wprm);
        ograniczenieWieku = OgraniczenieWiekuFactory(wprm);
        listaRozpoznan = new ListyRozpoznan(this.conn);
        listaProcedur = new ListyProcedur(this.conn);
        parametryJGP = new ParametryJGPSheet(this.conn);
    }

    private void setSheet(HSSFSheet aSheet)
    {
        sheet= aSheet;
    }
    @Override
    public void parse()throws SQLException {
       SheetHandler sh;
       setSheet(wbp.getSheet("wersja JGP"));
       wersjaJGP.setSheet(this.sheet);
       wersjaJGP.read();
       setSheet(wbp.getSheet("wykaz specjalności komórek"));
       specjalnosciKomorek.setSheet(this.sheet);
       specjalnosciKomorek.read();
       ZakresyJGP zakresy= (ZakresyJGP) zakresyJGP;
       setSheet(wbp.getSheet("Zakresy JGP"));
       zakresy.setSheetZakresy(this.sheet);
       setSheet(wbp.getSheet("Mechanizm osobodni"));
       zakresy.setSheetMechOsob(this.sheet);
       zakresy.setWprm(wersjaJGP.getWprm());
       zakresyJGP.read();
       
       setSheet(wbp.getSheet("Ograniczenie pobytu"));
       ograniczeniePobytu.setSheet(this.sheet);
       ograniczeniePobytu.setWprm(wersjaJGP.getWprm());
       ograniczeniePobytu.read();
       setSheet(wbp.getSheet("Ograniczenie wieku"));
       ograniczenieWieku.setSheet(this.sheet);
       ograniczenieWieku.setWprm(wersjaJGP.getWprm());
       ograniczenieWieku.read();
       setSheet(wbp.getSheet("Listy rozpoznań"));
       listaRozpoznan.setSheet(this.sheet);
       listaRozpoznan.setWprm(wersjaJGP.getWprm());
       listaRozpoznan.read();
       setSheet(wbp.getSheet("Listy procedur"));
       listaProcedur.setSheet(this.sheet);
       ListyProcedur lp = (ListyProcedur)listaProcedur;
       lp.setWrjgp(wersjaJGP.getWrjgp());
       listaProcedur.setWprm(wersjaJGP.getWprm());
       listaProcedur.read();
       setSheet(wbp.getSheet("Parametry JGP"));
       this.parametryJGP.setSheet(this.sheet);
       parametryJGP.setWprm(wersjaJGP.getWprm());
       parametryJGP.read();
       
    }

    @Override
    public void start() throws SAXException, IOException, SQLException {
        parse();
        wstawMSSQL();
    }

    @Override
    public void wstawMSSQL() throws SQLException {
        wersjaJGP.wstawMSSQL();
        DbInsertMSSQL dbSpecjalnosciKomorek = (DbInsertMSSQL) specjalnosciKomorek;
        dbSpecjalnosciKomorek.wstawMSSQL();
        DbInsertMSSQL dbZakresyJGP = (DbInsertMSSQL) zakresyJGP;
        dbZakresyJGP.wstawMSSQL();
        DbInsertMSSQL dbOgraniczeniePobytu = (DbInsertMSSQL) ograniczeniePobytu;
        dbOgraniczeniePobytu.wstawMSSQL();
        DbInsertMSSQL dbOgraniczenieWieku = (DbInsertMSSQL) ograniczenieWieku;
        dbOgraniczenieWieku.wstawMSSQL();
        DbInsertMSSQL dbInsertListaRozpoznan = (DbInsertMSSQL)listaRozpoznan;
        dbInsertListaRozpoznan.wstawMSSQL();
        DbInsertMSSQL dbInsertListaProcedur = (DbInsertMSSQL)listaProcedur;
        dbInsertListaProcedur.wstawMSSQL();
        DbInsertMSSQL dbInsertParametryMSSQL = (DbInsertMSSQL)parametryJGP;
        dbInsertParametryMSSQL.wstawMSSQL();
    }
    
    private  ReadJGPWorkSheet OgraniczenieWiekuFactory(int wprm){
        if( wprm == 5){
            return new OgraniczenieWiekuSheet(this.conn);
        }
        else
            return new OgraniczenieWiekuSheetv6(this.conn);
    }
    
    private  ReadJGPWorkSheet OgraniczeniePobytuFactory(int wprm){
        if( wprm == 5){
            return new OgraniczeniePobytuSheet(this.conn);
        }
        else
            return new OgraniczeniePobytuSheetv6(this.conn);
    }
}
