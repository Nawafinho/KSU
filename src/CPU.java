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

    public static void Run(PCB pcb) {
        pcb.setState(State.RUNNING);
        while (true) {
            if (pcb.getEETime() < pcb.getPC()) {
                
                break;
            }
            pcb.PCInc();
            OS.clockInc();
            interruptType type = Interrupt.interruptTerminateGenerator();
            if(type != null){
                OS.ISRi(pcb , type);
                System.out.println("CPU.Run()"+ type);
                break;
            }
            
            
            

            //System.out.println(pcb.getPC());
        }
    }

}
