
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.max;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
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
public class OS {

    private static PriorityQueue<Program> programs = new PriorityQueue<>();
    private static PriorityQueue<PCB> pcbs = new PriorityQueue<>();
    private static LinkedList<PCB> Terminate = new LinkedList<>();
    private static LinkedList<PCB> IOs = new LinkedList<>();

    private static Schedulers schedulers = new Schedulers();
    public static IO io = new IO(IOs);
    private static Random random = new Random();

    private static int clock = 0;

    private static int IOclock = 0;
    private static final int RAMsIZE = 163840;
    private static int HDSize = 524288;
    private static int currentSize = 0;
    private static int ID = 0;
    private static int normallTerminate = 0;
    private static int abnormallTerminate = 0;
    
    public static int i = 0;

    public static void main(String[] args) throws IOException {

        programsGenerator();

        
        programsReader();
        while (!programs.isEmpty() || !IOs.isEmpty()) {
            clockInc();
            if (pcbs.isEmpty() && IOs.isEmpty()) {
                
                schedulers.JobScheduler(programs, pcbs);
                
            }
            while (!pcbs.isEmpty()) {
                
                CPU.Run(schedulers.CPUScheduler());

            }
            IORun();

        }
        System.out.println(ID);
        System.out.println(Terminate.size());
        System.out.println(Terminate.peek().getState());
        System.out.println("END");
    }

    public static void ISRi(PCB pcb, interruptType type) {
        switch (type) {
            case Interrupt:
                insJob(pcb);
                break;
            case IOInterrupt:
                pcb.setIOTime(random.nextInt(200 - 100 + 1) + 100);
                IOs.add(pcb);
                break;
            case normallTerminate:
                normallTerminate++;
                insTerminate(pcb);
                break;
            case abnormallTerminate:
                abnormallTerminate++;
                insTerminate(pcb);
                break;
            default:
                    ;
        }
    }

    static private void programsReader() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("Programs.txt")));
        //System.out.println(contents);
        String[] strings = contents.split("\\r?\\n");
        for (String i : strings) {
            Program program = new Program(Integer.parseInt(i.split(";")[0].split(":")[1]), Integer.parseInt(i.split(";")[1].split(":")[1]), Integer.parseInt(i.split(";")[2].split(":")[1]));
            programs.add(program);
        }
    }

    public static void insJob(PCB pcb) {
        pcb.setState(State.READY);
        currentSize += pcb.getSize();
        pcbs.add(pcb);
    }

    public static void insTerminate(PCB pcb) {
        System.out.println(++i);
        pcb.setState(State.TERMINATED);
        Terminate.add(pcb);
    }

    public static void IORun() {
        if (!IOs.isEmpty()) {
            io.Trans();
        }
    }

    static public void clockInc() {
        clock++;
    }

    static public void IOclockInc() {
        IOclock++;
    }

    static private void programsGenerator() throws FileNotFoundException {
        ID = 0;
        String fileContent = "";
        int currentSize = 0;
        String program = "";
        int EETime = 0;
        int Size = 0;

        while (HDSize - currentSize >= 16400) {
            Size = random.nextInt(16384 - 16 + 1) + 16;
            EETime = random.nextInt(512 - 16 + 1) + 16;
            currentSize += Size;
            program = "ID:" + ID++ + ";CPU:" + EETime + ";SZ:" + Size;
            fileContent = fileContent + program + System.lineSeparator();
        }
        Size = HDSize - currentSize;
        EETime = random.nextInt(512 - 16 + 1) + 16;
        program = "ID:" + ID + ";CPU:" + EETime + ";SZ:" + Size;
        currentSize += Size;
        fileContent = fileContent + program;

        try (PrintWriter out = new PrintWriter("Programs.txt")) {
            out.print(fileContent);
        }

    }

    public static PriorityQueue<Program> getPrograms() {
        return programs;
    }

    public static void setPrograms(PriorityQueue<Program> programs) {
        OS.programs = programs;
    }

    public static PriorityQueue<PCB> getPcbs() {
        return pcbs;
    }

    public static void setPcbs(PriorityQueue<PCB> pcbs) {
        OS.pcbs = pcbs;
    }

    public static LinkedList<PCB> getTerminate() {
        return Terminate;
    }

    public static void setTerminate(LinkedList<PCB> Terminate) {
        OS.Terminate = Terminate;
    }

    public static int getClock() {
        return clock;
    }

    public static void setClock(int clock) {
        OS.clock = clock;
    }

    public static int getHDSize() {
        return HDSize;
    }

    public static void setHDSize(int HDSize) {
        OS.HDSize = HDSize;
    }

    public static int getRAMsIZESize() {
        return RAMsIZE;
    }

    public static int getCurrentSize() {
        return currentSize;
    }

    public static void setCurrentSize(int currentSize) {
        OS.currentSize = currentSize;
    }

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        OS.ID = ID;
    }

    public static int getNormallTerminate() {
        return normallTerminate;
    }

    public static Schedulers getSchedulers() {
        return schedulers;
    }

    public static void setSchedulers(Schedulers schedulers) {
        OS.schedulers = schedulers;
    }

    public static LinkedList<PCB> getIOs() {
        return IOs;
    }

    public static void setIOs(LinkedList<PCB> IOs) {
        OS.IOs = IOs;
    }

    public static int getIOclock() {
        return IOclock;
    }

    public static void setIOclock(int IOclock) {
        OS.IOclock = IOclock;
    }

    public static int getAbnormallTerminate() {
        return abnormallTerminate;
    }

    public static void setAbnormallTerminate(int abnormallTerminate) {
        OS.abnormallTerminate = abnormallTerminate;
    }
}
