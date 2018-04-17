
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class Interrupt {


    //private  interruptType type;
 
private static Random random = new Random();
   
    
    public static interruptType interruptTerminateGenerator() {
        if(random.nextInt(10 - 1 + 1) + 1==1){
            return interruptType.Interrupt;
        }
        if(random.nextInt(5 - 1 + 1) + 1==1){
            return interruptType.IOInterrupt;
        }
        if(random.nextInt(20 - 1 + 1) + 1==1){
            return interruptType.normallTerminate;
        }
        if(random.nextInt(100 - 1 + 1) + 1==1){
            return interruptType.abnormallTerminate;
        }
        return null;
    }
    public static boolean IOinterrupt(){
        if(random.nextInt(5 - 1 + 1) + 1==1)
            return true;
        return false;
    }
}
