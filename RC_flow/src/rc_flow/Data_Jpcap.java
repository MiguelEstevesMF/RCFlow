/*
 * Data_Jpcap.java
 *
 * Created on 1 de Novembro de 2007, 20:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package rc_flow;


import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.*; 
import java.lang.String;


/**
 *
 * @author Miguel
 */
public class Data_Jpcap {
    
    ArrayList node;
    ArrayList portas_antigas;
    
    /** Creates a new instance of Data_Jpcap */
    public Data_Jpcap(ArrayList portas_antigas2) {
        this.node = new ArrayList();
        this.portas_antigas = new ArrayList();
        
        ArrayList no2 = new ArrayList();
        
        for(int i=0;i<portas_antigas2.size();i++){
            no2= (ArrayList) portas_antigas2.get(i);
            no2.get(0);no2.get(1);no2.get(2);
            this.portas_antigas.add(no2);
        }
        
    }
    
    public int leitor_texto(String source){
       
        ArrayList no;
        StringTokenizer strtok;
        String aux, sp1,sp2,sp1_a,sp2_a;
        String src,dst,port;
        
        node = new ArrayList();
        for(int a=0;a<7;a++) node.add("N.D.");
        
        strtok = new StringTokenizer(source);
        
        if(source.startsWith("ARP")) {
            aux = (String) strtok.nextToken("[^ ]"); //ARP
            node.set(4,aux); //nivel de rede
            node.set(6,(String) strtok.nextToken("[^\n]")); //info
        }
        else{
            node.set(4,"IP"); //nivel de rede
            
            node.set(0,(String) strtok.nextToken("[^/]").trim()); //tempo
            strtok.nextToken("[^[0123456789]]");
            
            src = (String) strtok.nextToken("[^-]");
            node.set(1,src); //src
            strtok.nextToken("[^/]");
            strtok.nextToken("[^[0123456789]]");
            
            dst = (String) strtok.nextToken("[^ ]");
            node.set(2,dst); //dst
            
            
            
            aux = strtok.nextToken("[^ ]")+", ";
            
            if(!aux.startsWith("protocol(17)") && !aux.startsWith("protocol(6)")){
                aux += strtok.nextToken("[^\n]");
                node.set(6,aux); //info-defensivo
                return -1;
            }
            
            aux += strtok.nextToken("[^ ]")+", ";
            aux += strtok.nextToken("[^ ]")+", ";
            aux += strtok.nextToken("[^ ]")+", ";
            aux += strtok.nextToken("[^ ]");
            
            
            
            node.set(5,(String) strtok.nextToken("[^ ]")); //trans

            
            sp1 =(String) strtok.nextToken("[^ ]"); //aplicacao
            strtok.nextToken("[^>]");
            strtok.nextToken("[^ ]");
            sp2 =(String) strtok.nextToken("[^ ]").trim(); //aplicacao
            
            
            Data_portas dp = new Data_portas();
            
            sp1_a = dp.matchPorta(sp1);
            sp2_a = dp.matchPorta(sp2);
            
            no = new ArrayList();
            
            if(!sp2_a.equals("N.D.")) {
                node.set(3,sp2_a);
                if(sp1_a.equals("N.D.")){
                    no.add(src);
                    no.add(sp1);
                    no.add(sp2_a);
                    if(!portas_antigas.contains(no)) portas_antigas.add(no);
                }
            }
            else
            if(!sp1_a.equals("N.D.")){
                node.set(3,sp1_a);
                if(sp2_a.equals("N.D.")){
                    no.add(dst);
                    no.add(sp2);
                    no.add(sp1_a);
                    if(!portas_antigas.contains(no)) portas_antigas.add(no);
                }
            }
            else{
            
                for(int i=0;i<portas_antigas.size();i++)
                    no = new ArrayList((ArrayList) portas_antigas.get(i));
                    if(no.get(0).toString().equals(src) &&
                        no.get(1).toString().equals(sp1))
                    {
                        port = no.get(2).toString();
                    }
                    else
                    {
                        if(no.get(0).toString().equals(dst) &&
                        no.get(1).toString().equals(sp2))
                        {
                            port = no.get(2).toString();
                        }
                        else 
                        {
                            port="N.D.";
                        }
                    }
                node.set(3,port);
            }

            
            aux += ", srcport("+sp1+"), dstport("+sp2+")";
            
            if(strtok.hasMoreTokens())
                aux += ", " + strtok.nextToken("[^\n]");
            
            node.set(6,aux); //info
            
        }
        
    return 0;
    }
    
    
    public ArrayList getNode(){
        return this.node;
    }
    
    public ArrayList getPortas_Antigas(){
        return this.portas_antigas;
    }
    
}
