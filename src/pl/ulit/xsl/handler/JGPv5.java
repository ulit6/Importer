/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.xml.sax.SAXException;
import pl.ulit.importer.Import;

/**
 *
 * @author pawel
 */
public class JGPv5 extends XlsParser implements Import {
    private HSSFSheet sheet;

    public JGPv5(String afileName) throws FileNotFoundException, IOException {
        super(afileName);
    }

    private void setSheet(HSSFSheet aSheet)
    {
        sheet= aSheet;
    }
    @Override
    public void parse() {
       setSheet(wbp.getSheet("wersja JGP"));
       SheetHandler sh = new WersjaJGPv5(this.sheet);
       /*sh.start();
       setSheet(wbp.getSheet("Zakresy JGP"));
       sh = new ZakresyJGP(sheet,wbp.getSheet("Mechanizm osobodni"));
       sh.start();
       setSheet(wbp.getSheet("wykaz specjalności komórek"));
       sh = new SpecjalnosciKomorek(sheet);
       sh.start();
       setSheet(wbp.getSheet("Listy procedur"));
       sh = new ListyProcedur(sheet);
       sh.start();
       setSheet(wbp.getSheet("Listy rozpoznań"));
       sh = new ListyRozpoznan(sheet);
       sh.start();*/
       setSheet(wbp.getSheet("Ograniczenie wieku"));
       sh = new OgraniczenieWiekuSheet(sheet);
       sh.start();
       setSheet(wbp.getSheet("Ograniczenie pobytu"));
       sh = new OgraniczenieWiekuSheet(sheet);
       sh.start();
       
    }

    @Override
    public void start() throws SAXException, IOException, SQLException {
        parse();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
