/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class PCB implements Comparable<PCB>{

    private int size;
    private int EETime;
    private int ID;
    private State state;
    private int PC;

    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }
    

    public PCB(int inID, int inEETime, int inSize) {
        state = state.NEW;
        size = inSize;
        EETime = inEETime;
        PC = 0;
        state = state.READY;
    }
    
    public void PCInc() {
        PC++;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getEETime() {
        return EETime;
    }

    public void setEETime(int EETime) {
        this.EETime = EETime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public int compareTo(PCB t) {
        if (this.EETime-this.PC == t.EETime-PC) {
            return 0;
        }
        if (this.EETime-this.PC < t.EETime-PC) {
            return -1;
        }
        return 1;
    }
}
