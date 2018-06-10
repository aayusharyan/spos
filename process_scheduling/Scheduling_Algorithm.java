import java.util.ArrayList;
import java.util.Scanner;


public class Scheduling_Algorithm {

	static ArrayList<Process> PS = new ArrayList();
	public static void main(String args[])
	{
		Process P;
		Scanner sc = new Scanner(System.in);
		double at,bt;
		int no_p,ch;
		System.out.println("Enter the total no. processes: ");
		no_p = Integer.parseInt(sc.next());
		
		System.out.println("Enter Arrival Time for Process in increasing order: ");
		for(int i=0;i<no_p;i++)
		{
			System.out.println("Enter Arrival and Burst Time for Process: P"+(i+1));
			at = Double.parseDouble(sc.next());
			bt = Double.parseDouble(sc.next());
			P = new Process(at, bt, 0.0, 0.0, 0.0,0.0,0.0,0);
			PS.add(P);
		}
		System.out.println(PS);
		
		do{
			System.out.println("\t\t***Menu***");
			System.out.println("\t1: FCFS");
			System.out.println("\t2: SJF");
			System.out.println("\t3: Priority Non Preemptive");
			System.out.println("\t4: Round Robin Preemptive");
			System.out.println("\t5: EXIT");
			System.out.println("Enter your Choice: ");
			ch = sc.nextInt();
			
			switch (ch)
			{
				case 1:FCFS(no_p);
					break;
				case 2:SJF(no_p);
				break;
				case 3:Priority_Non_Pre(no_p);
				break;
				case 4:Round_Robin(no_p);
				break;
				case 5:System.out.println("!!!Good Bye!!!");
					break;

				default:System.out.println("Invalid Choice !!! Try Again");
					break;
			}
		}while(ch!=5);
	}
	
	public static void FCFS(int no_p)
	{
		double avg_wt = 0.0, avg_tat = 0.0;
		for(int i=0;i<no_p;i++)
		{
			if(i==0)
			{
				PS.get(i).FT = PS.get(i).AT + PS.get(i).BT;
				PS.get(i).WT = 0.0;
			}
			else
			{
				PS.get(i).FT = PS.get(i-1).FT + PS.get(i).BT;
				PS.get(i).WT = PS.get(i-1).FT - PS.get(i).AT;
			}
			PS.get(i).TAT = PS.get(i).BT + PS.get(i).WT;
		}
		System.out.println("Process Scheduling by using FCFS: ");
		System.out.println(PS);
		
		for(int i=0;i<no_p;i++)
		{
			avg_wt = avg_wt + PS.get(i).WT;
			avg_tat = avg_tat + PS.get(i).TAT;
		}
		System.out.println("Average Waiting Time is : "+avg_wt/no_p);
		System.out.println("Average Turn Around Time is : "+avg_tat/no_p);
	}
	
	public static int arrived(double time, int no_p)
	{
		for(int i=0;i<no_p;i++)
		{
			if(PS.get(i).UAT<=time&&PS.get(i).CBT!=0)
			{
				return 1;
			}
		}
		return 0;
	}
	
	public static int getmin(double time,int no_p)
	{
		int mini=0;
		double min=99.0;
		for(int i=0;i<no_p;i++)
		{
			if(PS.get(i).AT<=time&&PS.get(i).CBT<min&&PS.get(i).CBT!=0)
			{
				mini = i;
				min = PS.get(i).CBT;
			}
		}
		return mini;
	}
	
	public static void SJF(int no_p)
	{
		int finish=0;
		double time=0.0;
		int i;
		double avg_tat=0.0,avg_wt=0.0;
		while(finish!=no_p)
		{
			if(arrived(time,no_p)!=0)
			{
				i = getmin(time,no_p);
				time++;
				PS.get(i).CBT--;
				PS.get(i).FT = time;
				
				if(PS.get(i).CBT==0)
				{
					finish++;
					PS.get(i).TAT = time - PS.get(i).AT;
					PS.get(i).WT = PS.get(i).TAT - PS.get(i).BT;
				}
			}
			else
			{
				time++;
			}
		}
		System.out.println(PS);
		for(int j=0;j<no_p;j++)
		{
			avg_tat =avg_tat + PS.get(j).TAT;
			avg_wt =avg_wt + PS.get(j).WT;
		}
		System.out.println("The Average Turn Around Time by using SJF is: "+avg_tat/no_p);
		System.out.println("The Average Waiting Time by using SJF is: "+avg_wt/no_p);
	}
	
	public static int getmin_priority(double time,int no_p)
	{
		int mini=0;
		double min=99.0;
		for(int i=0;i<no_p;i++)
		{
			if(PS.get(i).UAT<time&&PS.get(i).CBT!=0)
			{
				if(PS.get(i).Priority<=min)
				{
					min = PS.get(i).Priority;
					mini = i;
				}
			}
		}
		return mini;
	}
	
	public static void Priority_Non_Pre(int no_p)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Priority For Each Process: ");
		for(int k=0;k<no_p;k++)
		{
			PS.get(k).Priority = sc.nextInt();
		}
		int finish=0,i;
		double time=0.0,avg_wt=0.0,avg_tat=0.0;
		while(finish!=no_p)
		{
			if(arrived(time, no_p)!=0)
			{
				i = getmin_priority(time,no_p);
				time = time + PS.get(i).BT;
				PS.get(i).FT = time;
				PS.get(i).CBT = 0.0;
				if(PS.get(i).CBT==0)
				{
					finish++;
					PS.get(i).TAT = time - PS.get(i).AT;
					PS.get(i).WT = PS.get(i).TAT - PS.get(i).BT;
				}
			}
			else
			{
				time++;
			}
		}
		System.out.println(PS);
		for(int j=0;j<no_p;j++)
		{
			avg_tat =avg_tat + PS.get(j).TAT;
			avg_wt =avg_wt + PS.get(j).WT;
		}
		System.out.println("The Average Turn Around Time by using SJF is: "+avg_tat/no_p);
		System.out.println("The Average Waiting Time by using SJF is: "+avg_wt/no_p);
	}
	
	public static int getmin_round_robin(double time,int no_p)
	{
		int mini=0;
		double min=99.0;
		for(int i=0;i<no_p;i++)
		{
			if(PS.get(i).UAT<=time&&PS.get(i).CBT!=0)
			{
				if(PS.get(i).UAT<min)
				{
					min = PS.get(i).UAT;
					mini = i;
				}
				if(PS.get(i).UAT==min && i!=0 &&PS.get(i-1).AT<PS.get(i-1).UAT)
				{
					min = PS.get(i).UAT;
					mini = i;					
				}
			}
		}
		return mini;
		}
	
	public static void Round_Robin(int no_p)
	{
		Scanner sc = new Scanner(System.in);
		int Finish=0,i=0;
		double time=0,avg_tat=0.0,avg_wt=0.0,TS;
		System.out.println("Enter the time slice: ");
		TS = sc.nextDouble();
		
		while(Finish!=no_p)
		{
			if(arrived(time, no_p)!=0)
			{
				i=getmin_round_robin(time,no_p);
				
				if(PS.get(i).CBT<=TS)
				{
					time = time+PS.get(i).CBT;
					PS.get(i).CBT = 0.0;
				}
				if(PS.get(i).CBT>TS)
				{
					time = time + TS;
					PS.get(i).CBT = PS.get(i).CBT - TS;
				}
				PS.get(i).FT = time;
				PS.get(i).UAT = time;
				if(PS.get(i).CBT==0)
				{
					Finish++;
					PS.get(i).TAT = time - PS.get(i).AT;
					PS.get(i).WT = PS.get(i).TAT - PS.get(i).BT; 
				}
			}
			else
			{
				time=time+TS;
			}
		}
		System.out.println(PS);
		for(int j=0;j<no_p;j++)
		{
			avg_tat =avg_tat + PS.get(j).TAT;
			avg_wt =avg_wt + PS.get(j).WT;
		}
		System.out.println("The Average Turn Around Time by using Round Robin is: "+avg_tat/no_p);
		System.out.println("The Average Waiting Time by using Round Robin is: "+avg_wt/no_p);
	}
}