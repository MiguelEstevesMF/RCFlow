/*
 * Data_Netflow.java
 *
 * Created on 1 de Novembro de 2007, 0:42
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
public class Data_Netflow {
    
    private ArrayList lista;
    private ArrayList list;
    /** Creates a new instance of Data_Netflow */
    public Data_Netflow(File file) {
        lista = new ArrayList();
        list = new ArrayList();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String f_str;
            while ((f_str = in.readLine()) != null) {
                if(!f_str.isEmpty() && !f_str.startsWith("#")) lista.add(f_str);
            }
            in.close();
              
            } catch (IOException e) {
                
            }
    }
    
    
    
    
    public int leitor_texto(){
        
        
        ArrayList info;
        ArrayList node = new ArrayList();
        for(int a=0;a<7;a++) node.add("N.D.");
        
        Pattern padrao;
        Pattern padrao_dns;
        Matcher pesquisa;
        Matcher pesquisa_dns;
        String str, str2, aux;
        String port1,port2,port1a,port2a;
        StringTokenizer strtok, strtok2;
    
        for(int i=0;i<lista.size();i++){
            info = new ArrayList();
            str=(String) lista.get(i);    
            strtok = new StringTokenizer(str);
            
            strtok.nextToken("[^,]");
            strtok.nextToken("[^,]");
            
            int ms = new Integer((String) strtok.nextToken("[^,]"));
            int d,h,m,s;
            d = ms/1000/3600/24;
            h = ms/1000/3600 - d*24;
            m = ms/1000/60 - h*60 - d*24*60;
            s = ms/1000 - m*60 - h*3600 - d*24*3600;
            ms = ms + 1000*(- m*60 - h*3600 - d*24*3600);
            
            node.set(0,d+":"+h+":"+m+":"+s+","+ms); //tempo

            info = new ArrayList();
            for(int a=0;a<7;a++) info.add("N.D.");    


            info.set(0,(String) strtok.nextToken("[^,]")); //info.0 exaddr
            info.set(4,(String) strtok.nextToken("[^,]")); //info.4 dpkts
            info.set(5,(String) strtok.nextToken("[^,]")); //info.5 doctets
            info.set(2,(String) strtok.nextToken("[^,]")); //info.2 first
            info.set(3,(String) strtok.nextToken("[^,]")); //info.3 last

            
            
            node.set(1,(String) strtok.nextToken("[^,]")); //src
            node.set(2,(String) strtok.nextToken("[^,]")); //dst
            if (((String) node.get(1)).contains(".".subSequence(0,0)))
                node.set(4,"IP"); //nivel de rede
            
            info.set(1,(String) strtok.nextToken("[^,]")); //info.1 nexthop

            strtok.nextToken("[^,]");
            strtok.nextToken("[^,]");
            
            port1 = strtok.nextToken("[^,]"); //src_port
            port2 = strtok.nextToken("[^,]"); //dst_port
            
            
            Data_portas dp = new Data_portas();
            
            port1a = dp.matchPorta(port1);
            port2a = dp.matchPorta(port2);
            if(port1a.equals("N.D.")) node.set(3,port2a);
            else node.set(3,port1a);
            
            
            
            aux = (String) strtok.nextToken("[^,]"); //transp
            if(aux.equals("6")) node.set(5,"TCP"+" ("+aux+")"); //TCP (6)
            else
            if(aux.equals("17")) node.set(5,"UDP"+" ("+aux+")"); //UDP (17)
            else node.set(5,"N.D."+" ("+aux+")"); //N.D. (X)
            
            
            info.set(6,(String) strtok.nextToken("[^,]")); //info.6 tos

            
            node.set(6,info); //info
           
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
