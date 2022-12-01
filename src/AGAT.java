
import java.util.ArrayList;


public class AGAT {
    private ArrayList<Process> processes = new ArrayList<Process>();
    private ArrayList<Process> queue = new ArrayList<Process>();
    private ArrayList<Process> deadList = new ArrayList<Process>();
    private int time = 0;
    private float v1, v2;

    void createSchedule(ArrayList<Process> ALL){
        Process a;
        processes = ALL;
        calcV1();
        addTheNewProcess();
        Process current = queue.get(0);
        int operationsNum;
        int quantum;
        System.out.println("time :" +time);
        calcAllFactors();
        while(deadList.size() < processes.size()) {
            operationsNum = (int)((40.0/100.0) * queue.get(0).getQuantum());
            quantum = queue.get(0).getQuantum();
            while(queue.get(0).getBrustTime() != 0){
                for (int i = 0; i < queue.size(); i++) {
                    System.out.println(queue.get(i).getName() + " quantum: " + queue.get(i).getQuantum() + "," + " Factor: " + queue.get(i).getFactor());
                }
                System.out.println("");

                operate(queue.get(0));
                System.out.println("time :" +time);
                queue.get(0).setBrustTime(queue.get(0).getBrustTime() - 1);
                quantum--;
                operationsNum--;
                addTheNewProcess();

                if (quantum == 0) {
                    queue.get(0).setQuantum(queue.get(0).getQuantum() + 2);
                    calcAllFactors();
                    moveQueue();
                    break;
                }
                if (getMinFactor() != 0 && operationsNum <= 0) {
                    queue.get(0).setQuantum(queue.get(0).getQuantum() + quantum);
                    calcAllFactors();
                    sortOnAgat();
                    break;
                }
                if (queue.get(0).getBrustTime() == 0) {
                    queue.get(0).setQuantum(0);
                    deadList.add(queue.get(0));
                    queue.remove(0);
                    calcAllFactors();
                    break;
                }
            }
        }
    }
    void calcV1(){
        int lastArrival;

        lastArrival = processes.get(0).getArrivalTime();
        for (int i = 1; i < processes.size(); i++) {
            if (lastArrival < processes.get(i).getArrivalTime())
                lastArrival = processes.get(i).getArrivalTime();
        }

        if(lastArrival > 10) v1 =  lastArrival/(float)10;
        else v1 = 1;
    }
    void calcV2(){
        int maxBurst = processes.get(0).getBrustTime();

        for (int i = 1; i < processes.size(); i++) {
            if(maxBurst < processes.get(i).getBrustTime())
                maxBurst = processes.get(i).getBrustTime();
        }

        if(maxBurst > 10) v2 = maxBurst/(float)10;
        else v2 = 1;
    }
    void addTheNewProcess(){
        calcV2();
        for (int i = 0; i < processes.size(); i++) {
            if(processes.get(i).getArrivalTime() == time){
                calcV2();
                processes.get(i).setFactor(v1,v2);
                queue.add(processes.get(i));
                System.out.println(processes.get(i).getName() + " has been added.");
            }
        }
    }
    void sortOnAgat(){
        int i, j;
        Process temp,lastRun;
        lastRun = queue.get(0);
        queue.remove(0);

        int n = queue.size();
        for (i = 1; i < n; i++) {
            temp = queue.get(i);
            j = i - 1;

            while(j>=0 && temp.getFactor() <= queue.get(j).getFactor())
            {
                queue.set(j+1,queue.get(j));
                j = j-1;
            }
            queue.set(j+1,temp);
        }
        queue.add(lastRun);
    }
    void moveQueue(){
        if(queue.size() > 1){
            Process temp = queue.get(0);
            for (int i = 0; i < queue.size() - 1; i++) {
                queue.set(i,queue.get(i+1));
            }
            queue.set(queue.size()-1,temp);
        }
    }
    void operate(Process running){
        System.out.println("|| proceded: " + running.getName());
        time++;
        for (int j = 0; j < queue.size(); j++) {
            System.out.println(queue.get(j).getName() + " Quantum: " + queue.get(j).getQuantum());
        }
    }
    ///////////////////////////////////////
    void calcFactor(int index){
        queue.get(index).setFactor(v1,v2);
    }
    void calcAllFactors(){
        calcV2();
        for (int j = 0; j < queue.size(); j++) {
            calcFactor(j);
        }
        for (int j = 0; j < queue.size(); j++) {
            System.out.println(queue.get(j).getName() + " Factor: " + queue.get(j).getFactor());
        }
    }
    /////////////////////
    int getMinFactor(){
        int temp = 0;
        for (int i = 0; i < queue.size(); i++) {
            if (queue.get(i).getFactor() < queue.get(temp).getFactor()) {
                temp = i;
            }
        }
        return temp;
    }
}