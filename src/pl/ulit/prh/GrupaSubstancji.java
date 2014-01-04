package pl.ulit.prh;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author ulit6
 */
public class GrupaSubstancji extends Element {
    private Integer kod ;
    private String nazwa;
    private String status;
    private Integer jednostka;


    public GrupaSubstancji(Integer akod,String anazwa,String astatus,Integer ajednostka)
    {
        kod=akod;
        nazwa=anazwa;
        status=astatus;
        jednostka=ajednostka;

    }
    public void setKod(Integer akod)
    {
        kod=akod;
    }
    public Integer getKod()
    {
        return kod;
    }
    public void setNazwa(String anazwa)
    {
        nazwa=anazwa;
    }
    public String getNazwa()
    {
        return nazwa;
    }
    public void setStatus(String astatus)
    {
        status=astatus;
    }
    public String getStatus()
    {
        return status;
    }
    public void setJednostka(Integer ajednostka)
    {
        jednostka=ajednostka;
    }
    public Integer getJednostka()
    {
        return jednostka;
    }
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Grupa-substancji: ");
        sb.append("Kod ").append(getKod());
        sb.append("Nazwa ").append(getNazwa());
        sb.append("Jednostka ").append(getJednostka());
        sb.append("Status ").append(getStatus());

        return sb.toString();
    }
}
