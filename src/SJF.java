import java.util.*;

public class SJF {

    public void start(int mainArr[],int mainBrr[],Scanner input){
       // Scanner input = new Scanner(System.in);
        System.out.println ("Enter number of process: ");
        int n = mainArr.length;
        int ID[] = new int[n];
        int AT[] = new int[n];
        int BT[] = new int[n];
        int WT[] = new int[n];
        int TAA[] = new int[n];
        int S[] = new int[n];
        int ST=0, total=0;
        float avg_WT=0, avg_TTA=0;

        for(int i=0; i<n; i++)
        {
            AT[i] = mainArr[i];
            BT[i] = mainBrr[i];
            ID[i] = i+1;
        }
        boolean x = true;
        while(true)
        {
            int c=n, min=999;
            if (total == n)
                break;
            for (int i=0; i<n; i++)
            {

                if ((AT[i] <= ST) && (S[i] == 0) && (BT[i]<min))
                {
                    min=BT[i];
                    c=i;
                }
            }
            if (c==n)
                ST++;
            else
            {
                TAA[c]=ST+BT[c];
                ST+=BT[c];
                TAA[c]-=AT[c];
                WT[c]=TAA[c]-BT[c];
                S[c]=1;
                total++;
            }
        }
        System.out.println("\nID      Arrival Time      Brust Time      Waiting Time      Turn Time");
        for(int i=0;i<n;i++) {
            avg_WT+= WT[i];
            avg_TTA+= TAA[i];
            System.out.println(ID[i]+"\t"+"      "+"\t"+AT[i]+"\t"+"               "+"\t"+BT[i]+"\t"+"          "+"\t"+WT[i]+"\t"+"          "+"\t"+TAA[i]);
        }
        System.out.println ("\nAverage Turn Around Time is: "+ (float)(avg_TTA/n));
        System.out.println ("Average Waiting Time is: "+ (float)(avg_WT/n));
    }
}
