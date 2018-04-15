/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class Program implements Comparable<Program> {

    private int size;
    private int EETime;
    private int ID;

    public Program(int inID, int inEETime, int inSize) {
        ID = inID;
        size = inSize;
        EETime = inEETime;
    }

    @Override
    public int compareTo(Program t) {
        if (this.size == t.size) {
            return 0;
        }
        if (this.size < t.size) {
            return -1;
        }
        return 1;
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
}
