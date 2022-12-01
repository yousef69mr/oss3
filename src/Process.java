public class Process {
    private String name;
    private String colour;
    private int ArrivalTime;
    private int BrustTime;
    private int Priority;

    private int quantum;
    private int factor;

    private boolean accessedState = false;

    public boolean isAccessedState() {
        return accessedState;
    }

    public void setAccessedState(boolean accessedState) {
        this.accessedState = accessedState;
    }

    Process(String name,String color,int arrivalTime,int brustTime,int priority,int quantum){

        setName(name);
        setColour(color);
        setArrivalTime(arrivalTime);
        setBrustTime(brustTime);
        setPriority(priority);
        setQuantum(quantum);
    }

    Process(){
        setName("");
        setColour("white");
        setArrivalTime(0);
        setBrustTime(0);
        setPriority(5);
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public int getBrustTime() {
        return BrustTime;
    }

    public void setBrustTime(int brustTime) {
        BrustTime = brustTime;
    }

    public int getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(float v1,float v2) {
        factor = (10 - Priority);
        factor += Math.ceil(ArrivalTime/v1);
        factor += Math.ceil(BrustTime/v2);
    }

}
