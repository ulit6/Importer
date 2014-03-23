/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;

import java.util.ArrayList;


/**
 *
 * @author pawel
 */
public class ListaRozpoznan {
    private final String kod;
    private final String typ;
    private final ArrayList<ICD10> rozpoznania;
    
    public ListaRozpoznan(String akod,String atyp,ArrayList<ICD10> arozpoznania){
        kod = akod;
        typ = atyp;
        rozpoznania = arozpoznania;
        
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Kod: ").append(kod).append("|");
        sb.append("Typ: ").append(typ).append("|");
        for(ICD10 rozponanie:rozpoznania){
            sb.append(rozponanie.toString());
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
    
    public static ListaRozpoznan newInstance(String akod,String atyp,ArrayList<ICD10> arozpoznania)
    {
        return new ListaRozpoznan(akod, atyp, arozpoznania);
    }
            
    
}
