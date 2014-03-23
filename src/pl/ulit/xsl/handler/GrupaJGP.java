/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;

import java.util.Objects;

/**
 *
 * @author pawel
 */
public class GrupaJGP {
    private final String kod;
    private final String kodProduktu;
    private final String nazwa;
    private int hospitalizacja;
    private int hospitalizacjaPlanowa;
    private int leczenie1dnia;
    private int dniPobytuFinansGrupa;
    private int hospitalizacja1dnia;
    private int ryczałt;
    
    public GrupaJGP(String akod,String akodProduktu,String anazwa,
            int ahospitalizacja,int ahospitalizacjaPlanowa,int aleczenie1dnia){
        kod=akod;
        kodProduktu=akodProduktu;
        nazwa=anazwa;
        hospitalizacja=ahospitalizacja;
        hospitalizacjaPlanowa=ahospitalizacjaPlanowa;
        leczenie1dnia=aleczenie1dnia;
    }
    public GrupaJGP(String akod,String akodProduktu,String anazwa){
        kod = akod;
        kodProduktu=akodProduktu;
        nazwa=anazwa;
    }
    @Override
    public String toString(){
         StringBuilder sb = new StringBuilder();
         sb.append("Kod: ").append(kod).append("|");
         sb.append("Kod produktu: ").append(kodProduktu).append("|");
         sb.append("Nazwa: ").append(nazwa).append("|");
         sb.append("Hospitalizacja: ").append(hospitalizacja).append("|");
         sb.append("Hospitalizacja planowa: ").append(hospitalizacjaPlanowa).append("|");
         sb.append("Leczenia jednego dnia: ").append(leczenie1dnia).append("|");
         sb.append("Dni pobytu finansowego grupą: ").append(dniPobytuFinansGrupa).append("|");
         sb.append("Hospitalizacja jedniego dnia: ").append(hospitalizacja1dnia).append("|");
         sb.append("Ryczałt ").append(ryczałt);
         return sb.toString();
        
    }
    public static GrupaJGP newInstance(String akod,String akodProduktu,String anazwa,
            int ahospitalizacja,int ahospitalizacjaPlanowa,int aleczenie1dnia){
        return new GrupaJGP(akod, akodProduktu, anazwa, ahospitalizacja, ahospitalizacjaPlanowa, aleczenie1dnia);
        
    }
    public static GrupaJGP newInstance(String akod,String akodProduktu,String anazwa){
        return new GrupaJGP(akod, akodProduktu, anazwa);
        
    }
    @Override
    public boolean equals(Object o){
           
         if(o == this)
            return true;
        if(o == null)
            return false;
        if(!(o instanceof GrupaJGP))
            return false;
        
         GrupaJGP grupaJGP = (GrupaJGP)o;
         return kod.equals(grupaJGP.kod);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.kod);
        hash = 97 * hash + Objects.hashCode(this.kodProduktu);
        hash = 97 * hash + Objects.hashCode(this.nazwa);
        hash = 97 * hash + this.hospitalizacja;
        hash = 97 * hash + this.hospitalizacjaPlanowa;
        hash = 97 * hash + this.leczenie1dnia;
        return hash;
    }
    public void setDniPobytuFinansGrupa(int adniPobytuFinansGrupa){
        dniPobytuFinansGrupa = adniPobytuFinansGrupa;
    }
    
    public void setHospitalizacja1Dnia(int ahospitalizacja1dnia){
        hospitalizacja1dnia = ahospitalizacja1dnia;    
    }
    
    public void setRyczałt(int aryczałt){
        ryczałt = aryczałt;
    }
    private void setMechanizmOsobodni(String kodJGP,String kodProduktu,String nazwaJGP){
        
    }

}
