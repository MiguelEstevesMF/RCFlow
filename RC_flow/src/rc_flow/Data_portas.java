/*
 * Data_portas.java
 *
 * Created on 30 de Outubro de 2007, 22:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package rc_flow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Miguel
 */
public class Data_portas {
    
    private ArrayList portas;
    
    /** Creates a new instance of Data_portas */
    public Data_portas() {
    
        portas = new ArrayList();
        try {
            BufferedReader in = new BufferedReader(new FileReader("src/portas.txt"));
            String f_str;
            while ((f_str = in.readLine()) != null) {
                if(!f_str.isEmpty() && !f_str.startsWith("Port")) portas.add(f_str);
            }
            in.close();
              
            } catch (IOException e) {
                
            }
    }
    
    public String matchPorta(String numero_porta){
            StringTokenizer strtok;
            Pattern padrao = Pattern.compile(numero_porta + " .+");
            Matcher pesquisa;
            for(int i=0;i<portas.size();i++){
                pesquisa = padrao.matcher((String) portas.get(i));  
                if (pesquisa.matches()) {
                    String str_p =(String) portas.get(i);
                    
                    strtok = new StringTokenizer(str_p);

                    strtok.nextToken("[^ ]");
                    strtok.nextToken("[^ ]");
                    return ((String) strtok.nextToken("[^\n]")).trim();
                }
            }
    return "N.D.";
    }
    
    public ArrayList getPortas(){
        return this.portas;
    }
    
    
    public String matchPortaTrans(String numero_porta){
            StringTokenizer strtok;
            Pattern padrao = Pattern.compile(numero_porta + " .+");
            Matcher pesquisa;
            for(int i=0;i<portas.size();i++){
                pesquisa = padrao.matcher((String) portas.get(i));  
                if (pesquisa.matches()) {
                    String str_p =(String) portas.get(i);
                    
                    strtok = new StringTokenizer(str_p);

                    strtok.nextToken("[^ ]");
                    return ((String) strtok.nextToken("[^ ]")).trim();
                }
            }
    return "N.D.";
    }
}
