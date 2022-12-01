import java.util.ArrayList;

public class SRTF {
    private ArrayList<Process> processes;
    private ArrayList<Process> tempArray;
    private int numOfProcesses;
    private int numOfObjects =0;

    SRTF(){
        processes=new ArrayList<Process>();
        tempArray=new ArrayList<Process>();
    }

    void scheduleProcesses(ArrayList<Process> p){

        int amountOfProcess = calculateTotalBrustTime(p);
        setNumOfProcesses(p.size());
        Sort(p);
        createTempArray(p,amountOfProcess);

        for(int i=0;i<amountOfProcess;i++) {

            if (tempArray.get(i).getArrivalTime() == numOfObjects) {
                p.add(tempArray.get(i));
            }

            deleteFinishedProcess(p);

            processes.add(getFirstProcess(p));

            numOfObjects++;
        }
    }

    void decrimentBrustTime(Process process,ArrayList<Process> p){

        for(int i=0;i<p.size();i++){
            if(p.get(i).getName().equals(process.getName())){
                if(process.getBrustTime()>0){
                    process.setBrustTime(process.getBrustTime()-1);
                }
            }
        }
    }


    Process getFirstArrivedProcess(ArrayList<Process> p){

        Process selectedProcess=p.get(0);

        for(int i=0;i<p.size();i++){

            if(p.get(i).getArrivalTime()<selectedProcess.getArrivalTime()){
                selectedProcess=p.get(i);
            }
        }

        return selectedProcess;

    }


    void Sort(ArrayList<Process> p){
        int i,counter=p.size();
        ArrayList<Process> temp=new ArrayList<>();

        for(i=0;i<counter;i++){
            temp.add(getFirstArrivedProcess(p));
            p.remove(getFirstArrivedProcess(p));
        }

        for(i=0;i< temp.size();i++){
            p.add(temp.get(i));
        }

    }

    void createTempArray(ArrayList<Process> p,int amount){
        int i;

        tempArray=new ArrayList<>(p);

        for(i=0;i<numOfProcesses;i++){
            p.remove(tempArray.get(i));
        }

        for(i=numOfProcesses;i<amount;i++){
            Process dumpObject =new Process();
            tempArray.add(dumpObject);

        }

    }


    void deleteFinishedProcess(ArrayList<Process> p){
        for(int i=0;i<p.size();i++){
            if(p.get(i).getBrustTime()==0){
                p.remove(p.get(i));
            }
        }
    }


    Process getFirstProcess(ArrayList<Process> p){
        int i;

        Process selectedProcess=p.get(0);
        if(selectedProcess!=null) {
            for (i = 0; i < p.size(); i++) {

                if (p.get(i).getBrustTime() < selectedProcess.getBrustTime()) {
                    selectedProcess = p.get(i);
                }

            }
            // System.out.println(selectedProcess.getName() +" "+selectedProcess.getBrustTime());
            decrimentBrustTime(selectedProcess, p);
            //System.out.println(selectedProcess.getName() +" "+selectedProcess.getBrustTime());
            //System.out.println(selectedProcess.getName());

        }
        return selectedProcess;
    }

    int calculateTotalBrustTime(ArrayList<Process> p){
        int sum=0;
        for(int i=0;i<p.size();i++){
            sum+=p.get(i).getBrustTime();
        }
        System.out.println("Total Brust Time : "+sum);
        return sum;
    }

    void setNumOfProcesses(int n){
        this.numOfProcesses=n;
    }

    int calculateCompletionTime(Process process){
        int lastIndex=processes.lastIndexOf(process);

        return lastIndex+1;
    }

    int getAverageTurnAroundTimeForEachProcess(Process process){

        int difference = calculateCompletionTime(process) - process.getArrivalTime();

        System.out.println(process.getName()+" --> TurnAround Time : "+difference);

        return difference;
    }

    float getAverageTurnAroundTime(){

        int sum=0;

        for(int i=0;i<numOfProcesses;i++){
            sum+=getAverageTurnAroundTimeForEachProcess(tempArray.get(i));
        }

        float average  = (float) sum/numOfProcesses;

        return average;
    }

    float getAverageWaitTime(){

        int sum=0;

        for(int i=0;i<numOfProcesses;i++){
            sum+=getAverageWaitTimeForEachProcess(tempArray.get(i));
        }

        float average  = (float) sum/numOfProcesses;

        return average;
    }

    int getLastWait(Process process){

        int lastIndex=processes.lastIndexOf(process);
        //System.out.println("Last Index : "+lastIndex);
        for(int i=lastIndex;i>=0;i--){
            if(!processes.get(i).getName().equals(process.getName())){
               // System.out.println("Last : "+i);
                return i+1;
            }
        }
        return -1;
    }

    int getFirstWait(Process process){

        for(int i=0;i<processes.size();i++){
            if(processes.get(i).getName().equals(process.getName())){
                return i+1;
            }
        }
        return -1;
    }

    int getAverageWaitTimeForEachProcess(Process process){

        int difference=0;

        if(getLastWait(process) - getFirstWait(process)>0) {
            difference = getLastWait(process) - getFirstWait(process);
        }else{
            difference = getLastWait(process) - process.getArrivalTime();
        }

        System.out.println(process.getName()+" --> Wait Time : "+difference);

        return difference;
    }

    void display(){

        System.out.println("/*********************************/\n/*********************************/");
        for(int i=0;i<processes.size();i++){
            System.out.print(processes.get(i).getName()+" ");
        }
        System.out.println("\n/*********************************/\n/*********************************/");
    }
}
