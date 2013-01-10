/*
 * Data_Manipulation.java
 *
 * Created on 25 de Outubro de 2007, 0:46
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package rc_flow;



import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author Miguel
 */


public class Data_Manipulation {
    
    private ArrayList list;
    /** Creates a new instance of Data_Manipulation */
    public Data_Manipulation() {
        list= new ArrayList();
    }
    
    public Data_Manipulation(ArrayList lista) {
        list= new ArrayList(lista);
    }
    
        
    public ArrayList filtragem(int i,String campo){
        ArrayList res= new ArrayList();
            for(Iterator it=list.iterator(); it.hasNext(); )
            {
                ArrayList arr = (ArrayList) it.next();
                if(arr.get(i).equals(campo)) res.add(arr);
            }
        return res;
    }

    public ArrayList filtragem_NiveldeRede(String campo){
        return filtragem(4,campo);
    }
    
    public ArrayList filtragem_Transporte(String campo){
        return filtragem(5,campo);
    }
    
    public ArrayList filtragem_Aplicacao(String campo){
        return filtragem(3,campo);
    }
    
    public ArrayList todos_elementos_campo(int i){
        ArrayList res= new ArrayList();
            for(Iterator it=list.iterator(); it.hasNext(); )
            {
                ArrayList arr = (ArrayList) it.next();
                res.add(arr.get(i));
            }
        return res;
    }
    
    public ArrayList elementos_campo(int i){
        ArrayList src = todos_elementos_campo(i);
        ArrayList res= new ArrayList();
            for(Iterator it=src.iterator(); it.hasNext(); )
            {
                String s = (String) it.next();
                if(!res.contains(s)) {
                    res.add(s);
                }
            }
        return res;
    }
    
    
    public ArrayList conta_elementos_campo(int i){
        ArrayList src = todos_elementos_campo(i);
        ArrayList res= new ArrayList();
        ArrayList cont= new ArrayList();
            for(Iterator it=src.iterator(); it.hasNext(); )
            {
                String s = (String) it.next();
                if(res.contains(s)) {
                    int index=res.indexOf(s);
                    int x = ((Integer) cont.get(index)).intValue();
                    cont.set(index,x+1);
                }
                else{
                    res.add(s);
                    int index=res.indexOf(s);
                    cont.add(index,1);
                }
            }
        return cont;
    }
    
    public int size(){
        return list.size();
    }
    
    public Object get(int i){
        return list.get(i);
    }
    
    public Object get(){
        return list;
    }
    
    public void add(ArrayList arr){
        this.list.add(arr);
    }
    
}
