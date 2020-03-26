package newsfjf;
import java.util.Scanner;
public class NewSFJF {
    public static Scanner sc = new Scanner (System.in);
    static int n, i, j, temp, awt, atat, ins, bt[], Q[], Q1[], Q2[], at[], p[], ff[], wt[], tat[];
    static boolean Q1E, Q2E;
    static float throughput=0;
    
    public static int computations (int n){
        //Checks the BT of each task and arrange them in ascending order
        for (i = 0; i < n; i++) {
            for (j = 0; j < n-1; j++) {
                if (bt[j] > bt[j+1]) {
                    temp = Q[j];
                    Q[j] = Q[j+1];
                    Q[j+1] = temp;
                    
                    temp = bt[j];
                    bt[j] = bt[j+1];
                    bt[j+1] = temp;
                    
                    temp = at[j];
                    at[j] = at[j+1];
                    at[j+1] = temp;
                    
                    temp = p[j];
                    p[j] = p[j+1];
                    p[j+1] = temp;
                }
                //If there are processes with the same burst time FCFS is applied and the AT will be compared
                else if (bt[j] == bt[j+1]) {
                    if (at[j] > at[j+1]) {
                        temp = Q[j];
                        Q[j] = Q[j+1];
                        Q[j+1] = temp;
                    
                        temp = at[j];
                        at[j] = at[j+1];
                        at[j+1] = temp;
                    
                        temp = p[j];
                        p[j] = p[j+1];
                        p[j+1] = temp;
                    }
                }
            }
        }
        //Transfer from ORIGINAL QUEUE to the READY QUEUE
        if (Q1E = true) {
            if (Q2E = false) {
                for (i = 0; i < n; i++) {
                Q1[i] = Q2[i];
                }
            Q1E = false;
            Q2E = true;
            } else {
                for (i = 0; i < n; i++) {
                    Q1[i] = Q[i];
                }
                Q1E = false;
            }
        } else {
            for (i = 0; i < n; i++) {
                Q2[i] = Q[i];
            }
        }
         //Calculate the FITFACTOR of each process
        for (i = 0; i < n; i++) {
            ff[i] = p[i] + at [i] + bt[i];
        }
        //Sort Q1 in ascending order according to their ff
        for (i = 0; i < n-1; i++) {
            for (j = 0; j < n-i-1; j++) {
                if (ff[j] > ff[j+1]) {
                    temp = Q1[j];
                    Q1[j] = Q[j+1];
                    Q1[j+1] = temp;
                    
                    temp = bt[j];
                    bt[j] = bt[j+1];
                    bt[j+1] = temp;
                    
                    temp = at[j];
                    at[j] = at[j+1];
                    at[j+1] = temp;
                    
                    temp = ff[j];
                    ff[j] = ff[j+1];
                    ff[j+1] = temp;
                }
            }
        }
        //Execute the tasks in Q1
        wt[0] = 0;
        awt = 0;
        tat[0] = 0;
        atat = 0;
        for (i = 1; i < n; i++) {
            wt[i] = tat[i-1];
            awt += wt[i];
            tat[i] = wt[i] + bt[i];
            atat += tat[i];
        }
        awt /= n;
        atat /= n;
    return n;
    }
    public static float throughput (){
        long startTime = System.nanoTime();
        long estimatedTime = startTime + 1000;
        throughput = (float) (5.0 / estimatedTime);
        return throughput;
    }
    public static int display (int n){
        System.out.print("\n\nProcess \t Burst Time \t Arrival Time \t Priority \tFit Factor \n");
        for(i = 0; i < n; i++) {
            System.out.print("\n   " + Q1[i] + "\t\t   " + bt[i] + "\t\t     " +at[i]+ "\t\t     " +p[i]+ "\t\t     " + ff[i] + "\n");
        }
        System.out.print("\n Average Wait Time : "+ awt
                +"\n Average Turnaround Time : "+ atat
                +"\n Throughput : "+ throughput());
        return n;
    }
    
    public static void main(String[] args) {
    int loop = 1;
    while (loop == 1){    
    //INPUT
        System.out.print("Input number of tasks: ");
        n = sc.nextInt();
        System.out.println("-----------------------------------------------------------------------");
        Q= new int [n]; //holds id for ORIGINAL QUEUE
        for (i = 0; i < n; i++) {
            Q[i] = i+1;
        }
        //READY QUEUE INITIALIZATION
        Q1= new int[n]; Q1E = true;
        //WAITING QUEUE INITIALIZATION
        Q2= new int[n]; Q2E = true;
        //BURST, ARRIVAL TIME AND PRIORITY
        bt= new int[n]; at= new int[n]; p = new int[n]; 
        for (i = 0; i < n; i++) {
            System.out.print("Input burst time for Task " + Q[i] +":");
                bt[i] = sc.nextInt();
            System.out.print("Input arrival time for Task " + Q[i] +":");
                at[i] = sc.nextInt();
            System.out.print("Input memory requirement for Task " + Q[i] +":");
                p[i] = sc.nextInt();
            System.out.println(); 
        }
        // FIT FACTOR (p + at + bt) (lowest ff = fittest)
        ff = new int[n]; 
        //WAITING TIME AND TURNAROUND TIME
        wt = new int[n]; tat = new int[n];
        computations (n);
        display (n);
    //PROMPT    
        System.out.println ("\n\n-----------------------------------------------------------------------");
        System.out.println ("Finish executing current queue before receiving a new one? \n[1] Yes\n[2] No \nAnswer:");
        ins = sc.nextInt();
            if (ins == 1) {
                Q1E = false;
                System.out.println ("Loading...");
            } else if (ins == 2) {
                Q1E = true;
            }
        System.out.println ("-----------------------------------------------------------------------"+
            "\nCreate new queue? \n[1] Yes\n[2] No\nAnswer: \n");
        loop = sc.nextInt();
            if (loop == 2){
                System.out.println("Goodbye.");
            } else {
                System.out.println ("-----------------------------------------------------------------------");
            }
        }
    }
}
