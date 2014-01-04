package pl.ulit.prh;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author ulit6
 */
public class Katalog {

    private String kodkatalogu;
    private ArrayList<Taryfa> taList;

    public Katalog(String akodkatalogu)
    {
        kodkatalogu=akodkatalogu;
        taList = new ArrayList<Taryfa>();
    }
    public void setKodKatalogu(String akodkatalogu)
    {
        kodkatalogu=akodkatalogu;
    }
    public String getKodKatalogu()
    {
        return kodkatalogu;
    }
    public void addTaryfa(Taryfa taryfa)
    {
        taList.add(taryfa);
    }
    public ArrayList<Taryfa> getTaryfa()
    {
        return this.taList;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Katalog: ");
        sb.append("Kod katalogu: ").append(kodkatalogu);
        Iterator<Taryfa> tait = taList.iterator();
        while(tait.hasNext())
         {
             sb.append(tait.next().toString());
         }
        return sb.toString();
    }
}
