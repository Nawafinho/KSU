
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
public class IO {

    private LinkedList<PCB> IOs = new LinkedList<>();
    private int IOclock = 0;

    public IO(LinkedList<PCB> IOs) {
        this.IOs = IOs;
    }

    public void Trans() {
        IOs.peek().IOCInc();
        if (Interrupt.IOinterrupt() || IOs.peek().getIOC() == IOs.peek().getIOTime()) {
            IOremove();
        }
    }

    private void IOremove() {
        OS.insJob(IOs.poll());
    }
}
