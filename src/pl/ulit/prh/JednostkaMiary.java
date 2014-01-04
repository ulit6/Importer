package pl.ulit.prh;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author ulit6
 */
public class JednostkaMiary extends Element{

    private Integer kod ;
    private String nazwa;
    private String status;

    public JednostkaMiary(Integer akod,String anazwa,String astatus)
    {
        kod=akod;
        nazwa=anazwa;
        status=astatus;
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
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Jednostka-miary: ");
        sb.append("Kod ").append(getKod());
        sb.append("Nazwa ").append(getNazwa());
        sb.append("Status ").append(getStatus());
        return sb.toString();
    }
}
