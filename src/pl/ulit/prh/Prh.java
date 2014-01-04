package pl.ulit.prh;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author ulit6
 */
public class Prh  extends Element{
    private String typ ;
    private String wersja;
    private String czas_gen;
    private Integer wer_tech;
    private Integer oddz_nfz;

    public Prh(String atyp,String awersja,String aczas_gen,Integer awer_tech,Integer aoddz_nfz)
    {
        typ=atyp;
        wersja=awersja;
        czas_gen=aczas_gen;
        wer_tech=awer_tech;
        oddz_nfz=aoddz_nfz;
    }
    public void setTyp(String atyp)
    {
        typ=atyp;
    }
    public String getTyp()
    {
        return typ;
    }

    public void setWersja(String awersja)
    {
        wersja=awersja;
    }
     public String getWersja()
    {
        return wersja;
    }

    public void setCzasGen(String aczas_gen)
    {
        czas_gen=aczas_gen;
    }
    public String getCzasGen()
    {
        return czas_gen;
    }

    public void setWerTech(Integer awer_tech)
    {
        wer_tech=awer_tech;
    }
    public Integer getWerTech()
    {
        return wer_tech;
    }
    public Integer getOddzNfz()
    {
        return oddz_nfz;
    }
    public void setOddzNfz(Integer aoddz_nfz)
    {
        oddz_nfz=aoddz_nfz;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Prh: ");
        sb.append("Typ ").append(getTyp()).append("|");
        sb.append("Wersja ").append(getWersja()).append("|");
        sb.append("Czas gen ").append(getCzasGen()).append("|");
        sb.append("Wersja tech ").append(getWerTech().toString()).append("|");
        sb.append("Oddzial NFZ: ").append(getOddzNfz());
        return sb.toString();
    }
}
