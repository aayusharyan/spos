import java.util.Scanner;

public class PageReplace {
	static int[] stream=new int[30];
	public static void main(String[] args){
		Operation op=new Operation();
		Scanner sc=new Scanner(System.in);
		int no,i,ch;
		System.out.println("Enter the number of page in stream:");
		no=sc.nextInt();
		System.out.print("\nEnter the page stream:");
		for(i=0;i<no;i++){
			stream[i]=sc.nextInt();
		}
		do{
			System.out.print("\n******MENU*******");
			System.out.print("\n1.LRU");
			System.out.print("\n2.Optimal");
			System.out.println("\n3.Exit");
			System.out.println("\nEnter your choice:");
			ch=sc.nextInt();
			switch(ch){
				case 1:op.lru(no,stream);
					break;
				case 2:op.optimal(no,stream);
					break;
				case 3:System.out.print("\nBye");
					break;
			}
		}while(ch!=3);
	}
}
 