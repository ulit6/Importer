/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;

/**
 *
 * @author pawel
 */
public class ParametryJGP {
    private final String typListy;
    private final String kodListy;
    private final String kodJGP;
    private final String kodAlgorytmu;
    private final int kodPobytu;
    private final int kodWieku;
    private final String kodICD9;
    private final int liczbaWystapienICD9;
    private final String drugiKodICD9;
    private final int drugaLiczbaWystapienICD9;
    private final String rozpoznanie;
    private final String kodICD10;
    private final String drugiKodICD10;
    private final String plec;
    private final int kodPrzyjecia;
    private final int kodWypisu;
    private final String negatywnaListaProcedur;
    private final String negatywnaListaRozpoznan;
    
    private ParametryJGP(String typListy,String kodListy,String kodJGP,String kodAlgorytmu,
            int kodPobytu,int kodWieku,String kodICD9,int liczbaWystapienICD9,
            String drugiKodICD9,int drugaLiczbaWystapienICD9,String rozpoznanie,
            String kodICD10,String drugiKodICD10,String plec,int kodPrzyjecia,
            int kodWypisu,String negatywnaListaProcedur,String negatywnaListaRozpoznan){
        this.typListy = typListy;
        this.kodListy = kodListy;
        this.kodJGP = kodJGP;
        this.kodAlgorytmu = kodAlgorytmu;
        this.kodPobytu = kodPobytu;
        this.kodWieku = kodWieku;
        this.kodICD9 = kodICD9;
        this.liczbaWystapienICD9 = liczbaWystapienICD9;
        this.drugiKodICD9 = drugiKodICD9;
        this.drugaLiczbaWystapienICD9 = drugaLiczbaWystapienICD9;
        this.rozpoznanie = rozpoznanie;
        this.kodICD10 = kodICD10;
        this.drugiKodICD10 = drugiKodICD10;
        this.plec = plec;
        this.kodPrzyjecia = kodPrzyjecia;
        this.kodWypisu = kodWypisu;
        this.negatywnaListaProcedur = negatywnaListaProcedur;
        this.negatywnaListaRozpoznan = negatywnaListaRozpoznan;   
    }
    
    public static ParametryJGP newInstance(String typListy,String kodListy,String kodJGP,String kodAlgorytmu,
            int kodPobytu,int kodWieku,String kodICD9,int liczbaWystapienICD9,
            String drugiKodICD9,int drugaLiczbaWystapienICD9,String rozpoznanie,
            String kodICD10,String drugiKodICD10,String plec,int kodPrzyjecia,
            int kodWypisu,String negatywnaListaProcedur,String negatywnaListaRozpoznan){
        
        return new ParametryJGP(typListy, kodListy, kodJGP, kodAlgorytmu, kodPobytu,
                kodWieku, kodICD9, liczbaWystapienICD9, drugiKodICD9, drugaLiczbaWystapienICD9, 
                rozpoznanie, kodICD10, drugiKodICD10, plec, kodPrzyjecia, kodWypisu, 
                negatywnaListaProcedur, negatywnaListaRozpoznan);   
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Typ listy: ").append(this.typListy).append("|");
        sb.append("Kod listy: ").append(this.kodListy).append("|");
        sb.append("Kod JGP: ").append(this.kodJGP).append("|");
        sb.append("Kod algorytmu: ").append(this.kodAlgorytmu).append("|");
        sb.append("Kod wieku: ").append(this.kodWieku).append("|");
        sb.append("Kod ICD9: ").append(this.kodICD9).append("|");
        sb.append("Liczba wystapien ICD9: ").append(this.liczbaWystapienICD9).append("|");
        sb.append("Drugi kod ICD9: ").append(this.drugiKodICD9).append("|");
        sb.append("Druga liczba wystapien ICD9: ").append(this.drugaLiczbaWystapienICD9).append("|");
        sb.append("Rozpoznanie: ").append(this.rozpoznanie).append("|");
        sb.append("Kod ICD10: ").append(this.kodICD10).append("|");
        sb.append("Drugi kod ICD10: ").append(this.drugiKodICD10).append("|");
        sb.append("Plec: ").append(this.plec).append("|");
        sb.append("Kod przyjecia: ").append(this.kodPrzyjecia).append("|");
        sb.append("Kod wypisu: ").append(this.kodWypisu).append("|");
        sb.append("Negatywna lista procedur: ").append(this.negatywnaListaProcedur).append("|");
        sb.append("Negatywna lista rozpoznań: ").append(this.negatywnaListaRozpoznan);
                
                
        return sb.toString();
        
    }
    public String getKodJGP(){
        return this.kodJGP;
    }
    public String getTypListy(){
        return this.typListy;
    }
    public String getKodListy(){
        return this.kodListy;
    }
    public String getKodAlgorytmu(){
        return this.kodAlgorytmu;
    }
    public int getKodPobytu(){
        return this.kodPobytu;
    }
    public int getKodWieku(){
        return this.kodWieku;
    }
    public String getKodICD9(){
        return this.kodICD9;
    }
    public int getLiczbaWystapienICD9(){
        return this.liczbaWystapienICD9;
    }
    public String getDrugiKodICD9(){
        return this.drugiKodICD9;
    }
    public int getDrugaLiczbaWystapienICD9(){
        return this.drugaLiczbaWystapienICD9;
    }
    public String getRozpoznanie(){
        return this.rozpoznanie;
    }
    public String getKodICD10(){
        return this.kodICD10;
    }
    public String getDrugiKodICD10(){
        return this.drugiKodICD10;
    }
    public String getListaNegatywnychProcedur(){
        return this.negatywnaListaProcedur;
    }
    public String getListaNegatywnychRozpoznan(){
        return this.negatywnaListaRozpoznan;
    }
}
