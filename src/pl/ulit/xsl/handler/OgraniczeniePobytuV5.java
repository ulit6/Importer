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
public class OgraniczeniePobytuV5 {
    private final int kod;
    private final int ponizejGornaGranica;
    private final int pomiedzyGornaGranica;
    private final int pomiedzyDolnaGranica;
    private final int powyzejDolnaGranica;
            
    public OgraniczeniePobytuV5(int kod, int ponizejGornaGranica,int pomiedzyGornaGranica,
            int pomiedzyDolnaGranica,int powyzejDolnaGranica){
        
        this.kod = kod;
        this.ponizejGornaGranica = ponizejGornaGranica;
        this.pomiedzyDolnaGranica = pomiedzyDolnaGranica;
        this.pomiedzyGornaGranica = pomiedzyGornaGranica;
        this.powyzejDolnaGranica = powyzejDolnaGranica;
    }
    public String toString(){
          StringBuilder sb = new StringBuilder();
          sb.append("Kod: ").append(kod).append("|");
          sb.append("Poniżej górna granica: ").append(ponizejGornaGranica).append("|");
          sb.append("Pomiedzy górna granica: ").append(pomiedzyGornaGranica).append("|");
          sb.append("Pomiędzy dolna granica: ").append(pomiedzyDolnaGranica).append("|");
          sb.append("Powyzej dolna granica: ").append(powyzejDolnaGranica);
          return sb.toString();
    }
    public static OgraniczeniePobytuV5 newInstance(int kod, int ponizejGornaGranica,int pomiedzyGornaGranica,
            int pomiedzyDolnaGranica,int powyzejDolnaGranica){
        return new OgraniczeniePobytuV5(kod, ponizejGornaGranica,
                pomiedzyGornaGranica, pomiedzyDolnaGranica, powyzejDolnaGranica);
    }
    public int getKod(){
        return this.kod;
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
}
