
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.max;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;
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

    static PriorityQueue<Program> programs = new PriorityQueue<>();
    private static int clock = 0;
    private static int HDSize = 2097152;

    public static void main(String[] args) throws IOException {

        programsGenerator();

        int ts = 0;
        programsReader();
        while (!programs.isEmpty()) {
            clockInc();
            if (Schedulers.getPcbs().isEmpty()) {
                System.out.println(++ts);
                Schedulers.JobScheduler(programs);
                System.out.println("OS.main()");
            }
            while (!Schedulers.getPcbs().isEmpty()) {
                CPU.Run(Schedulers.CPUScheduler());
                System.out.println("OS.main()1");
            }

        }
        System.out.println("END");
    }

    static private void programsReader() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("Programs.txt")));
        System.out.println(contents);
        String[] strings = contents.split("\\r?\\n");
        for (String i : strings) {
            Program program = new Program(Integer.parseInt(i.split(";")[0].split(":")[1]), Integer.parseInt(i.split(";")[1].split(":")[1]), Integer.parseInt(i.split(";")[2].split(":")[1]));
            programs.add(program);
        }
    }

    static public void clockInc() {
        clock++;
    }

    static private void programsGenerator() throws FileNotFoundException {
        int ID = 0;
        String fileContent = "";
        int currentSize = 0;
        Random random = new Random();
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
}
