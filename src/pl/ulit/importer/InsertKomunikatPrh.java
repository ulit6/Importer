/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ulit.importer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import org.slf4j.LoggerFactory;
import pl.ulit.dbInsert.DbInsert;
import pl.ulit.dbInsert.EditNagFactory;
import pl.ulit.dbInsert.InsertGrupaSubstancjiFactory;
import pl.ulit.dbInsert.InsertJednostkiMiaryFactory;
import pl.ulit.dbInsert.InsertNagFactory;
import pl.ulit.dbInsert.InsertPozycjaPrhFactory;
import pl.ulit.prh.Element;
import pl.ulit.prh.GrupaSubstancji;
import pl.ulit.prh.JednostkaMiary;
import pl.ulit.prh.PozycjaPrh;
import pl.ulit.prh.Prh;

/**
 *
 * @author ulit6
 */
public class InsertKomunikatPrh  extends InsertKomunikat implements ObserverElement,ObserverImport{
    
    private Connection con;
    private ArrayList<GrupaSubstancji> gsList;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(InsertKomunikatPrh.class);
    private Prh prh;
    private ArrayList<JednostkaMiary> jmList;
  //  private String Db;
    private String wersja;
    private ArrayList<PozycjaPrh> poList;
  //  private ArrayList<Taryfa> taList ;
    
    public InsertKomunikatPrh(Connection acon,Prh aprh,ArrayList<PozycjaPrh> apoList,
            ArrayList<GrupaSubstancji> agsList,ArrayList<JednostkaMiary> ajmLi,String aDB,String awersja)
    {
        super(aDB);
        con = acon ;
        gsList = agsList;
        prh = aprh;
      //  Db = aDB;
        wersja=awersja;
        poList = apoList;
        jmList = ajmLi;
        
    }
    public InsertKomunikatPrh(Connection acon,String aDB)
    {
        super(aDB);
        con = acon ;
        gsList = new ArrayList<GrupaSubstancji>();
        jmList = new ArrayList<JednostkaMiary>();
        poList = new ArrayList<PozycjaPrh>();
       // taList = new ArrayList<Taryfa>();
    }
    @Override
    void Insert() throws SQLException {
        logger.info("Insert komunikat PRH: " + this.prh);
        logger.info("Baza danych: " +this.Db);
        DbInsert NagPrh = InsertNagFactory.createNag(this.Db, this.con, this.prh);
        NagPrh.wstaw();
        DbInsert JedMiary = InsertJednostkiMiaryFactory.createInsertJednostkiMiary(Db,  con, jmList);
        JedMiary.wstaw();
        DbInsert GrSub = InsertGrupaSubstancjiFactory.createInsertGrupaSubstancji(Db,con, gsList, prh);
        GrSub.wstaw();
        DbInsert PozPrh = InsertPozycjaPrhFactory.createInsertPozycjaPrh(Db,con, poList, prh);
        PozPrh.wstaw();
        DbInsert EdtPrh = EditNagFactory.createEditNagFactory(Db, con, prh);
        EdtPrh.wstaw();
    }

   

    @Override
    public void update(Element element) {
        if(element instanceof Prh){
            this.prh =(Prh) element;
            logger.info("Aktualizacja: " + this.prh);     
        } 
        if(element instanceof GrupaSubstancji){
            GrupaSubstancji gs = (GrupaSubstancji)element;
            this.gsList.add(gs);
            logger.info("Aktualizacja: "+gs );
        }
        if(element instanceof JednostkaMiary){
            JednostkaMiary jm = (JednostkaMiary)element;
            this.jmList.add(jm);
            logger.info("Aktualizacja: " + jm);
        }
      /*  if(element instanceof Taryfa){
            Taryfa taryfa = (Taryfa)element;
            this.taList.add(taryfa);
            logger.info("Aktualizacja: " + taryfa);
                    
        }*/
        if(element instanceof PozycjaPrh){
            PozycjaPrh pp = (PozycjaPrh) element;
            this.poList.add(pp);
            logger.info("Aktualizacja: "+pp);
        }    
    }

    @Override
    public void update(Integer astatus) throws SQLException{
        logger.info("Koniec parsowania");
        logger.info("Wywołanie insertu");
        this.Insert();
    }
    
}
