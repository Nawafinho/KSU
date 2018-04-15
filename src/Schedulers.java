
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
    private static final int RAMsIZE = 163840;
    private static PriorityQueue<PCB> pcbs = new PriorityQueue<>();
    private static int currentSize = 0;

    public static void JobScheduler(PriorityQueue<Program> programs) {
        
        while (!programs.isEmpty() && currentSize + programs.peek().getSize() <= RAMsIZE ) {
            Program program = programs.poll();
            PCB pcb = new PCB(program.getID(), program.getEETime(), program.getSize());
            pcb.setState(State.READY);
            insJob(pcb);
            System.out.println("Schedulers.JobScheduler() " + currentSize);
        }
    }
    
    public static PCB CPUScheduler() {
        currentSize -= pcbs.peek().getSize();
        return pcbs.poll();
    }
    
    public static void insJob(PCB pcb){
        currentSize+= pcb.getSize();
        pcbs.add(pcb);
    }
    
    public static PriorityQueue<PCB> getPcbs() {
        return pcbs;
    }

    public static void setPcbs(PriorityQueue<PCB> pcbs) {
        Schedulers.pcbs = pcbs;
    }

    public static int getCurrentSize() {
        return currentSize;
    }

    public static void setCurrentSize(int currentSize) {
        Schedulers.currentSize = currentSize;
    }
}
