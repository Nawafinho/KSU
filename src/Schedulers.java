
import java.util.PriorityQueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author USER
 */
public class Schedulers {

    //private static final int RAMsIZE = 600;
    private PCB pcb ;
    //private static PriorityQueue<PCB> pcbs = new PriorityQueue<>();
    

    public void JobScheduler(PriorityQueue<Program> programs , PriorityQueue<PCB> pcbs ) {
        while (!programs.isEmpty() && OS.getCurrentSize() + programs.peek().getSize() <= OS.getRAMsIZESize() ) {
            Program program = programs.poll();
            pcb = new PCB(program.getID(), program.getEETime(), program.getSize());
            OS.insJob(pcb);
        }
    }
    
    public PCB CPUScheduler() {
        OS.setCurrentSize(OS.getCurrentSize()-OS.getPcbs().peek().getSize());
        return OS.getPcbs().poll();
    }
    

}
