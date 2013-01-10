/*
 * Data_Tcpdump.java
 *
 * Created on 31 de Outubro de 2007, 0:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package rc_flow;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.*; 
import java.lang.String;


/**
 *
 * @author Miguel
 */
public class Data_Tcpdump {

    private ArrayList lista;
    private ArrayList list;

    
    /** Creates a new instance of Data_Tcpdump */
    public Data_Tcpdump(File file) {
        lista = new ArrayList();
        list = new ArrayList();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String f_str;
            while ((f_str = in.readLine()) != null) {
                if(!f_str.isEmpty()) lista.add(f_str);
            }
            in.close();
              
            } catch (IOException e) {
                
            }
    }
    
    
    public int leitor_texto(){
    
        ArrayList node = new ArrayList();
        for(int a=0;a<7;a++) node.add("N.D.");
        Pattern padrao;
        Pattern padrao_dns;
        Matcher pesquisa;
        Matcher pesquisa_dns;
        String str, str2, sp1, sp2, aux, sp1_a, sp2_a, sp1_t, sp2_t;
        StringBuffer strbuff;
        StringTokenizer strtok, strtok2;
    
        for(int i=0;i<lista.size();i++){
        str=(String) lista.get(i);
                
        strtok = new StringTokenizer(str);
        node.set(0,(String) strtok.nextToken("[^ ]")); //tempo
        aux = (String) strtok.nextToken("[^ ]");
        node.set(4,aux); //nivel de rede
        
        if(!aux.equals("arp")){
            strbuff = new StringBuffer ((String) strtok.nextToken("[^>]").trim());
            strtok2 = new StringTokenizer(strbuff.reverse().toString());

            strbuff = new StringBuffer((String) strtok2.nextToken("[^.]"));
            sp1 = new String(strbuff.reverse());

            strbuff = new StringBuffer((String) strtok2.nextToken("[^\n]"));
            aux = strbuff.reverse().toString();
            node.set(1,aux.substring(0,aux.length()-1)); //src
        
            
            
            strtok.nextToken("[^ ]");
            
            
            
            strbuff = new StringBuffer ((String) strtok.nextToken("[^:]").trim());
            strtok2 = new StringTokenizer(strbuff.reverse().toString());

            strbuff = new StringBuffer((String) strtok2.nextToken("[^.]"));
            sp2 = new String(strbuff.reverse());

            strbuff = new StringBuffer((String) strtok2.nextToken("[^\n]"));
            aux = strbuff.reverse().toString();
            node.set(2,aux.substring(0,aux.length()-1)); //dst
            
            
            
            strtok.nextToken("[^ ]");
            
            Data_portas dp = new Data_portas();
            
            sp1_a = dp.matchPorta(sp1);
            sp2_a = dp.matchPorta(sp2);
            if(sp1_a.equals("N.D.")) node.set(3,sp2_a);
            else node.set(3,sp1_a);
            
            
            sp1_t = dp.matchPortaTrans(sp1);
            sp2_t = dp.matchPortaTrans(sp2);
            if(sp1_t.equals("N.D.")) node.set(5,sp2_t);
            else node.set(5,sp1_t);
            
            
        } 
        
        node.set(6,(String) strtok.nextToken("[^\n]")); //info
        
        list.add(node);
        node = new ArrayList();
        for(int a=0;a<7;a++) node.add("N.D.");
        }
            
    return 0;
}
    
    
    
    
    public ArrayList getList(){
        return this.list;
    }
    
    
    
    
}
