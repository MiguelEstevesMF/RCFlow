/*
 * Data_Wireshark.java
 *
 * Created on 24 de Outubro de 2007, 23:16
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
public class Data_Wireshark {

    private ArrayList lista;
    private ArrayList list;

    /** Creates a new instance of Data_Wireshark */
    
    public Data_Wireshark(File file) {
        lista = new ArrayList();
        list = new ArrayList();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String f_str;
            while ((f_str = in.readLine()) != null) {
                if(!f_str.isEmpty() && !f_str.startsWith("Frame") ) lista.add(f_str);
            }
            in.close();
              
            } catch (IOException e) {
                
            }
    }
    

    public int leitor_texto(){

        ArrayList node = new ArrayList();
        Pattern padrao;
        Pattern padrao_dns;
        Matcher pesquisa;
        Matcher pesquisa_dns;
        String str, str2;
        int j=-1;
            for(int i=0;i<lista.size();i++){
                str=(String) lista.get(i);

                if(str.startsWith("No.") ){
                    if(j>-1) {
                        if(node.size()==6) node.add("N.D.");

                        String aux=(String) node.get(3);
                        node.set(3,node.get(6));
                        node.set(6,aux);

                        list.add(j,node);
                        node = new ArrayList();
                    }
                    j++;
                }

                if(str.startsWith(" ") ){
                    node.add(0,str.substring(8,19).trim()); //time
                    node.add(1,str.substring(20,41).trim()); //source
                    node.add(2,str.substring(42,63).trim()); //destination
                    node.add(3,str.substring(73).trim()); //info
                }


                padrao = Pattern.compile(".+Protocol, Src:.+");   
                pesquisa = padrao.matcher(str);  
                if (pesquisa.matches()){
                    StringTokenizer strtok = new StringTokenizer(str);
                    node.add(4,(String) strtok.nextToken("[^,]")); //nivel de rede
                }


                padrao = Pattern.compile(".+Protocol, Src Port:.+");   
                pesquisa = padrao.matcher(str);  
                if (pesquisa.matches()){
                    StringTokenizer strtok = new StringTokenizer(str);
                    node.add(5,(String) strtok.nextToken("[^,]")); //transporte
                }


                padrao = Pattern.compile(".+Protocol, Src Port:.+");   
                pesquisa = padrao.matcher(str);  
                if (pesquisa.matches()){
                    node.add(6,getAplicacao(str)); //aplicacao
                }

            }
        return 0;
    }
    
    public ArrayList getList(){
        return this.list;
    }
    
    public String getAplicacao(String str){
        
        Data_portas dat_portas = new Data_portas();
        
        String port = new String();
        String str2;
        Character ch;
        char c;
        
        
        StringTokenizer strtok = new StringTokenizer(str);
        strtok.nextToken("[^,]");
       
        str2 = (String) strtok.nextToken("[^,]");

        c = str2.substring(11).charAt(0);
        ch = new Character(c);
        if(!ch.isDigit(c))
            port = str2.substring(str2.indexOf('(')+1, str2.indexOf(')') );
        else{
            str2 = (String) strtok.nextToken("[^,]");

            c = str2.substring(11).charAt(0);
            ch = new Character(c);
            
            if(!ch.isDigit(c))
                port = str2.substring(str2.indexOf('(')+1, str2.indexOf(')') );
            else port="notfound";

        }
           
        return dat_portas.matchPorta(port);
    }
    
}
