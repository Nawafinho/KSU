
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
        boolean tst = !programs.isEmpty() && OS.getCurrentSize() + programs.peek().getSize() <= OS.getRAMsIZESize() ;
        System.out.println("Schedulers.JobScheduler() 1"+ tst);
        while (!programs.isEmpty() && OS.getCurrentSize() + programs.peek().getSize() <= OS.getRAMsIZESize() ) {
            Program program = programs.poll();
            pcb = new PCB(program.getID(), program.getEETime(), program.getSize());
            pcb.setState(State.READY);
            insJob(pcb);
            System.out.println("Schedulers.JobScheduler() " + OS.getCurrentSize());
        }
    }
    
    public PCB CPUScheduler() {
        OS.setCurrentSize(OS.getCurrentSize()-OS.getPcbs().peek().getSize());
        return OS.getPcbs().poll();
    }
    
    public void insJob(PCB pcb){
        OS.setCurrentSize(pcb.getSize());
        OS.getPcbs().add(pcb);
        System.out.println("Schedulers.insJob()");
    }
    

}
