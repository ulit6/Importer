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
public class OgraniczenieWiekuV5 extends OgraniczenieWieku{
    private final int ponizejGornaGranica;
    private final int pomiedzyGornaGranica;
    private final int pomiedzyDolnaGranica;
    private final int powyzejDolnaGranica;
            
    private OgraniczenieWiekuV5(int kod, int ponizejGornaGranica,int pomiedzyGornaGranica,
            int pomiedzyDolnaGranica,int powyzejDolnaGranica){
        super(kod);
        this.ponizejGornaGranica = ponizejGornaGranica;
        this.pomiedzyDolnaGranica = pomiedzyDolnaGranica;
        this.pomiedzyGornaGranica = pomiedzyGornaGranica;
        this.powyzejDolnaGranica = powyzejDolnaGranica;
    }
   
    public int getPonizejGornaGranica(){
        return this.ponizejGornaGranica;
    }
    public int getPowyzejDolnaGranica(){
        return this.powyzejDolnaGranica;
    }
    public int getPomiedzyGornaGranica(){
        return this.pomiedzyGornaGranica;
    }
    public int getPomiedzyDolnaGranica(){
        return this.pomiedzyDolnaGranica;
    }
    @Override
    public String toString(){
          StringBuilder sb = new StringBuilder();
          sb.append("Kod: ").append(kod).append("|");
          sb.append("Poniżej górna granica: ").append(ponizejGornaGranica).append("|");
          sb.append("Pomiedzy górna granica: ").append(pomiedzyGornaGranica).append("|");
          sb.append("Pomiędzy dolna granica: ").append(pomiedzyDolnaGranica).append("|");
          sb.append("Powyzej dolna granica: ").append(powyzejDolnaGranica);
          return sb.toString();
    }
    public static OgraniczenieWiekuV5 newInstance(int kod, int ponizejGornaGranica,int pomiedzyGornaGranica,
            int pomiedzyDolnaGranica,int powyzejDolnaGranica){
        return new OgraniczenieWiekuV5(kod, ponizejGornaGranica,
                pomiedzyGornaGranica, pomiedzyDolnaGranica, powyzejDolnaGranica);
    }
            
}
