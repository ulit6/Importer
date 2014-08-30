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
public class OgraniczenieWiekuV7 extends OgraniczenieWieku{
    private final int ponizej;
    private final int powyzej;
    private final static String jednostkaV7="R";
    public OgraniczenieWiekuV7(int kod,int ponizej,int powyzej) {
        super(kod, jednostkaV7);
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
    public static OgraniczenieWiekuV7 newInstance(int kod,int ponizej,int powyzej){
        return new OgraniczenieWiekuV7(kod, ponizej, powyzej);
    }
}

    

