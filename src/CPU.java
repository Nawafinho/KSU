/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class CPU {
    
    public static void Run(PCB pcb){
        while (true) {  
            if(pcb.getEETime()<pcb.getPC())
                break;
            OS.clockInc();
            //System.out.println(pcb.getPC());
            pcb.PCInc();
            
        }
    }
    
}
