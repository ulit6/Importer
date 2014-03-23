/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author pawel
 */
public class ListaProcedur {
    private final String kod;
    private final String typ;
    private final Map<ICD9,Integer> procedury;
    
    public ListaProcedur(String akod,String atyp,HashMap<ICD9,Integer> aprocedury){
        kod = akod;
        typ = atyp;
        procedury = aprocedury;
        
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Kod: ").append(kod).append("|");
        sb.append("Typ: ").append(typ).append("|");
        Set set = procedury.entrySet();
        Iterator setIterator = set.iterator();
        while(setIterator.hasNext()){
          Map.Entry mapEntry = (Map.Entry) setIterator.next();
          sb.append("Procedura: ");
          sb.append(mapEntry.getKey());
          sb.append("ranga: ").append(mapEntry.getValue());
          sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
    
    public static ListaProcedur newInstance(String akod,String atyp,HashMap<ICD9,Integer> aprocedury)
    {
        return new ListaProcedur(akod, atyp, aprocedury);
    }
            
    
}
