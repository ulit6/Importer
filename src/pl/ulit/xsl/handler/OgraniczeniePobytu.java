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
public class OgraniczeniePobytu {
    private final int kod;
    private final int ponizejGornaGranica;
    private final int pomiedzyGornaGranica;
    private final int pomiedzyDolnaGranica;
    private final int powyzejDolnaGranica;
            
    public OgraniczeniePobytu(int kod, int ponizejGornaGranica,int pomiedzyGornaGranica,
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
    public static OgraniczeniePobytu newInstance(int kod, int ponizejGornaGranica,int pomiedzyGornaGranica,
            int pomiedzyDolnaGranica,int powyzejDolnaGranica){
        return new OgraniczeniePobytu(kod, ponizejGornaGranica,
                pomiedzyGornaGranica, pomiedzyDolnaGranica, powyzejDolnaGranica);
    }
            
}
