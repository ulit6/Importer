package pl.ulit.prh;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author ulit6
 */
public class PozycjaPrh extends Element{
    private Integer kod;
    private String ean;
    private String symbol;
    private Integer gsl;
    private String nazwa;
    private String postac;
    private String dawka;
    private String opak;
    private String podmOdp;
    private Double ilosc;
    private Double sztuki;
    private String status;
    private Katalog katalog;

    public PozycjaPrh(Integer akod,String aean,String asymbol,Integer agsl,String anazwa,String apostac,
            String adawka,String aopak,String apodmOdp,Double ailosc,Double asztuki,String astatus)
    {
        kod=akod;
        ean=aean;
        symbol=asymbol;
        gsl=agsl;
        nazwa=anazwa;
        postac=apostac;
        dawka=adawka;
        opak=aopak;
        podmOdp=apodmOdp;
        ilosc=ailosc;
        sztuki=asztuki;
        status=astatus;
        katalog = null;
    }

    public void setKod(Integer akod)
    {
        kod=akod;
    }
    public Integer getKod()
    {
        return kod;
    }
    public void setEan(String aean)
    {
        ean=aean;
    }
    public String getEan()
    {
        return ean;
    }
    public void setSymbol(String asymbol)
    {
        symbol=asymbol;
    }
     public String getSymbol()
    {
        return symbol;
    }
    public void setGsl(Integer agsl)
    {
        gsl=agsl;
    }
    public Integer getGsl()
    {
        return gsl;
    }
    public void setNazwa(String anazwa)
    {
        nazwa=anazwa;
    }
    public String getNazwa()
    {
        return nazwa;
    }
    public void setPostac(String apostac)
    {
        postac=apostac;
    }
    public String getPostac()
    {
        return postac;
    }
    public void setDawka(String adawka)
    {
        dawka=adawka;
    }
    public String getDawka()
    {
        return dawka;
    }
    public void setOpak(String aopak)
    {
        opak=aopak;
    }
    public String getOpak()
    {
        return opak;
    }
    public void setPodmOdp(String apodmOdp)
    {
        podmOdp=apodmOdp;
    }
    public String getPodmOdp()
    {
        return podmOdp;
    }
    public void setIlosc(Double ailosc)
    {
        ilosc=ailosc;
    }
    public Double getIlosc()
    {
        return ilosc;
    }
    public void setSztuki(Double asztuki)
    {
        sztuki=asztuki;
    }
    public Double getSztuki()
    {
        return sztuki;
    }
    public void setStatus(String astatus)
    {
        status=astatus;
    }
    public String getStatus()
    {
        return status;
    }
    public void setKatalog(Katalog akatalog)
    {
        katalog = akatalog;
    }
    public Katalog getKatalog()
    {
        return this.katalog;
    }
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Pozycja prh: ");
        sb.append("kod ").append(getKod());
        sb.append("ean ").append(getEan());
        sb.append("symbol ").append(getSymbol());
        sb.append("gsl ").append(getGsl());
        sb.append("nazwa ").append(getNazwa());
        sb.append("postac ").append(getPostac());
        sb.append("dawka ").append(getDawka());
        sb.append("opak ").append(getOpak());
        sb.append("Podm odp ").append(getPodmOdp());
        sb.append("ilosc ").append(getIlosc());
        sb.append("sztuki ").append(getSztuki());
        sb.append("status ").append(getStatus());
        sb.append(katalog);
        return sb.toString();
    }
}
