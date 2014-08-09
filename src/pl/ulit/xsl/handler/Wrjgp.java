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
public class Wrjgp {
    private final int wprm;
    private final int walg;
    private final int wicd;
    private final int roko;
    private final int msod;
    private final int msdo;
    
    public Wrjgp(int aWprm,int aWalg,int aWicd,int aRoko,int aMsod,int aMsdo)
    {
        wprm=aWprm;
        walg=aWalg;
        wicd=aWicd;
        roko=aRoko;
        msod=aMsod;
        msdo=aMsdo;
        
    }
    public int getWprm(){
        return wprm;
    }
    public int getWalg(){
        return walg;
    }
    public int getWicd(){
        return wicd;
    }
    public int getRoko(){
        return roko;
    }
    public int getMsod(){
        return msod;
    }
    public int getMsdo(){
        return msdo;
    }
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("WPRM: ").append(wprm).append("|");
        sb.append("WALG: ").append(walg).append("|");
        sb.append("WICD: ").append(wicd).append("|");
        sb.append("ROKO: ").append(roko).append("|");
        sb.append("MSOD: ").append(msod).append("|");
        sb.append("MSDO: ").append(msdo).append("|");
        return sb.toString();
    }
}
