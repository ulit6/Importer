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
public class ICD9 {
    private final String kod;
    private final String nazwa;
    
    public ICD9(String akod,String anazwa){
        kod = akod;
        nazwa = anazwa;        
    }
    public String getKod(){
        return this.kod;
    }
    public String getNazwa(){
        return this.nazwa;
    }
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Nazwa: ").append(nazwa).append("|");
        sb.append("Kod: ").append(kod);
        return sb.toString();
    }
    
    public static ICD9 newInstance(String akod,String anazwa){
        return new ICD9(akod, anazwa);
    }
    
    @Override
    public boolean equals(Object o){
        
        if(o == this)
            return true;
        if(o == null)
            return false;
        if(!(o instanceof ICD9))
            return false;
        ICD9 icd9 = (ICD9)o;
        return kod.equals(icd9.kod) && 
               nazwa.equals(icd9.nazwa) ;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.kod);
        hash = 53 * hash + Objects.hashCode(this.nazwa);
        return hash;
    }
}
