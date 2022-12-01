import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Process> processes =new ArrayList<>();

    public static void main(String[] args){
        try {



            Process p1=new Process("P1","red",0,9,4,2);
            Process p2=new Process("P2","green",3,11,6,2);
            Process p3=new Process("P3","violet",4,7,8,1);
            Process p4=new Process("P4","yellow",4,5,9,3);
            Process p5=new Process("P5","yellow",7,21,10,7);

            processes.add(p1);
            processes.add(p2);
            processes.add(p3);
            processes.add(p4);
            processes.add(p5);


            Scanner scan = new Scanner(System.in);
            boolean isRunning = true;
            while (isRunning) {

/*
                    System.out.println("Enter number of processes");
                    int number = scan.nextInt();
                    for (int i = 1; i <= number; i++) {

                        System.out.println("Enter Process ("+i+") Name");
                        String name = scan.next();
                        System.out.println("Enter Process ("+i+") Colour");
                        String colour = scan.next();
                        System.out.println("Enter Arrival Time");
                        int arrival = scan.nextInt();
                        System.out.println("Enter Brust Time");
                        int brust = scan.nextInt();
                        System.out.println("Enter Priorty ");
                        int priorty = scan.nextInt();
                        System.out.println("Enter Quantum Time");
                        int quantum = scan.nextInt();
                        Process p = new Process(name, colour, arrival, brust, priorty, quantum);
                        processes.add(p);

                    }
*/

                System.out.println("Menu:\n1)SJF\n2)Priority\n3)SRTF\n4)AGAT\n5)Exit");

                int input = scan.nextInt();
                switch (input) {

                    case (1):
                        SJF object = new SJF();
                        int Arr[] = new int[processes.size()];
                        int Brr[] = new int[processes.size()];

                        for (int i = 0; i < processes.size(); i++) {
                            Arr[i] = processes.get(i).getArrivalTime();
                            Brr[i] = processes.get(i).getBrustTime();
                        }
                        object.start(Arr, Brr, scan);

                        break;

                    case (2):
                        Priority scheduler2 = new Priority();

                        scheduler2.scheduleProcesses(processes);

                        scheduler2.display();


                        System.out.println("***> Average TurnAround Time : " + scheduler2.getAverageTurnAroundTime());
                        System.out.println("/***************************/");
                        System.out.println("***> Average Wait Time : " + scheduler2.getAverageWaitTime());
                        System.out.println("/***************************/");
                        break;

                    case (3):
                        SRTF scheduler = new SRTF();
                        scheduler.scheduleProcesses(processes);

                        scheduler.display();


                        System.out.println("***> Average TurnAround Time : " + scheduler.getAverageTurnAroundTime());
                        System.out.println("/***************************/");
                        System.out.println("***> Average Wait Time : " + scheduler.getAverageWaitTime());
                        System.out.println("/***************************/");
                        break;

                    case (4):

                        AGAT d = new AGAT();

                        d.createSchedule(processes);

                        break;
                    case (5):
                        isRunning = false;
                        break;


                }

            }
        } catch (Exception e) {
            //
            System.out.println("Finished");
        }
    }
}
