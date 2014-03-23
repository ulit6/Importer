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
public class ICD10 {
    private final String kod;
    private final String nazwa;
 //   private final String ranga;
    
    public ICD10(String akod,String anazwa){
        kod = akod;
        nazwa = anazwa;  
    //    ranga = aranga;
        
    }
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Nazwa: ").append(nazwa).append("|");
        sb.append("Kod: ").append(kod);
        return sb.toString();
    }
    
    public static ICD10 newInstance(String akod,String anazwa){
        return new ICD10(akod, anazwa);
    }
    
    @Override
    public boolean equals(Object o){
        
        if(o == this)
            return true;
        if(o == null)
            return false;
        if(!(o instanceof ICD10))
            return false;
        ICD10 icd9 = (ICD10)o;
        return kod.equals(icd9.kod) && 
               nazwa.equals(icd9.nazwa) ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 58 * hash + Objects.hashCode(this.kod);
        hash = 58 * hash + Objects.hashCode(this.nazwa);
        return hash;
    }
}
