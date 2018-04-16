
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author USER
 */
public class IO extends Thread {

    private LinkedList<PCB> IOs = new LinkedList<>();

    public IO(LinkedList<PCB> IOs) {
        this.IOs = IOs;
    }

    @Override
    public void run() {
        while (!OS.getPcbs().isEmpty() || !OS.getPrograms().isEmpty() || !IOs.isEmpty()) {
            if (!IOs.isEmpty()) {
                
            }
        }
    }
    
    private void IOtrans(PCB pcb){
        
    }

}
