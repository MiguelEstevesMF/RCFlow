/*
 * Par.java
 *
 * Created on 30 de Outubro de 2007, 22:48
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package rc_flow;

/**
 *
 * @author Miguel
 */
public class Par {
    
    int i;
    String str;
    /** Creates a new instance of Par */
    public Par() {
        this.str = new String();
    }
    
    public Par(int i, String str) {
        this.str = str;
        this.i = i;
    }
    
    public int getInt(){
        return this.i;
    }
    
    public String getStr(){
        return this.str;
    }
    
    public boolean maior_que(Par par){
        return this.i > par.getInt();
    }
    
    public String toString(){
        return "("+ this.i + ", " + this.str + ")";
    }
    
    public void ordena(Par[] v){
        for(int i = 0; i < v.length-1; i++){
            for(int j = 0; j < v.length-1;j++){                
                if(v[j].getInt() < v[j+1].getInt()){
                    Par aux = v[j];  v[j] = v[j+1]; v[j+1]=aux;
                }
            }
        }
    }
    
    
}
