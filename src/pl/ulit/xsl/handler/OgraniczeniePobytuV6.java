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
public class OgraniczeniePobytuV6 extends OgraniczenieWieku{
    private final int ponizej;
    private final int powyzej;
    public OgraniczeniePobytuV6(int kod, String jednostka,int ponizej,int powyzej) {
        super(kod, jednostka);
        this.ponizej = ponizej;
        this.powyzej = powyzej;
    }
    public int getPonizej(){
        return this.ponizej;
    }
    public int getPowyzej(){
        return this.powyzej;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
          sb.append("Kod: ").append(kod).append("|");
          sb.append("Poniżej : ").append(ponizej).append("|");
          sb.append("Powyzej : ").append(powyzej).append("|");
          sb.append("Jednostka: ").append(jednostka);
          return sb.toString();
    }
    public static OgraniczeniePobytuV6 newInstance(int kod, String jednostka,int ponizej,int powyzej){
        return new OgraniczeniePobytuV6(kod, jednostka, ponizej, powyzej);
    }
}
