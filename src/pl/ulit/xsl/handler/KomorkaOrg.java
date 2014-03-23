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
public class KomorkaOrg {
    private final String kod;
    private final String nazwa;
    
    public  KomorkaOrg(String akod,String anazwa){
        kod = akod;
        nazwa = anazwa;
                
    }
    
    public String toString(){
         StringBuilder sb = new StringBuilder();
         sb.append("Kod: ").append(kod).append("|");
         sb.append("Nazwa: ").append(nazwa);
         return sb.toString();
    }
    
    public static KomorkaOrg newInstance(String akod,String anazwa){
        return new KomorkaOrg(akod, anazwa);
    }
}
