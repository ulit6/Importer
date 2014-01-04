package pl.ulit.prh;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author ulit6
 */
public class Taryfa extends Element {
    private String dataod;
    private String datado;
    private Double taryfabazowa;
    private Double lbroz;

    public Taryfa(String adataod,Double ataryfabazowa,Double albroz)
    {
        dataod=adataod;
        taryfabazowa=ataryfabazowa;
        lbroz=albroz;
    }
    public Taryfa(String adataod,String adatado,Double ataryfabazowa,Double albroz)
    {
        dataod=adataod;
        taryfabazowa=ataryfabazowa;
        lbroz=albroz;
        datado=adatado;
    }

    public void setDataOd(String adataod)
    {
        dataod=adataod;
    }
    public String getDataOd()
    {
        return dataod;
    }
    public void setDataDo(String adatado)
    {
        datado=adatado;
    }
    public String getDataDo()
    {
        return datado;
    }
    public void setTaryfaBazowa(Double ataryfabazowa)
    {
        taryfabazowa=ataryfabazowa;
    }
    public Double getTaryfaBazowa()
    {
        return taryfabazowa;
    }
    public void setLbRoz(Double albroz)
    {
        lbroz=albroz;
    }
    public Double getLbRoz()
    {
        return lbroz;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Taryfa: ");
        sb.append("Data od ").append(getDataOd());
        sb.append("Data do ").append(getDataDo());
        sb.append("Taryfa bazowa ").append(getTaryfaBazowa());
        sb.append("Lb jednostek ").append(getLbRoz());

        return sb.toString();
    }

}
