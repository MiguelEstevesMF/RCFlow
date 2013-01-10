/*
 * Main.java
 *
 * Created on 29 de Outubro de 2007, 22:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package rc_flow;

import java.io.IOException;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jpcap.*;


/**
 *
 * @author Miguel
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        //Interfaces de rede
        NetworkInterface[] devices = JpcapCaptor.getDeviceList();
        for (int i = 0; i < devices.length; i++) {
            System.out.println(i+": "+devices[i].description);
        }
        try {
            
            JpcapCaptor captor=JpcapCaptor.openDevice(devices[1], 65535, false, 20);
            
            for(int i=0;i<10;i=i){
              //capture a single packet and print it out
                Object obj = captor.getPacket();
                if(obj!=null) System.out.println(obj);
            }

            captor.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        

        
       
        

    }
}
