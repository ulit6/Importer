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
public abstract class OgraniczeniePobytu {
    protected final int kod;
    protected final String jednostka;
    
    public OgraniczeniePobytu(int kod,String jednostka){
        this.kod = kod;
        this.jednostka = jednostka;
    }
    public OgraniczeniePobytu(int kod){
        this.kod = kod;
        this.jednostka = "R";
    }
    public int getKod(){
        return this.kod;
    }
    public String getJednostka(){
        return this.jednostka;
    }
}
