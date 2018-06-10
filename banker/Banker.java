import java.util.ArrayList;
import java.util.Scanner;

public class Banker {
	static int[][] claim,alloc;
	static int[] res,avail;
	static ArrayList<Integer> seq=new ArrayList<>();
	public static void main(String[] args){
		int np,nr;
		int i,j;
		Scanner sc=new Scanner(System.in);
		System.out.print("\nEnter the number of process and resourse:");
		np=sc.nextInt();
		nr=sc.nextInt();
		int[][] claim=new int[np][nr];
		System.out.print("\nEnter the calim matrix:");
		for(i=0;i<np;i++){
			for(j=0;j<nr;j++){
				claim[i][j]=sc.nextInt();
			}
		}
		
		System.out.print("\nClaim Matrix:");
		for(i=0;i<np;i++){
			System.out.print("\n");
			for(j=0;j<nr;j++){
				System.out.print(" "+claim[i][j]);
			}
		}
		
		System.out.print("\nEnter the allocation Matrix:");
		int[][] alloc=new int[np][nr];
		for(i=0;i<np;i++){
			for(j=0;j<nr;j++){
				alloc[i][j]=sc.nextInt();
			}
		}
		System.out.print("\nAllocation Matrix :");
		for(i=0;i<np;i++){
			System.out.print("\n");
			for(j=0;j<nr;j++){
				System.out.print(" "+alloc[i][j]);
			}
		}
		int[] res=new int[nr];
		System.out.print("\nEnter the resource vector:");
		for(i=0;i<nr;i++){
			res[i]=sc.nextInt();
		}
		System.out.print("\nReource vector:");
		for(i=0;i<nr;i++){
			System.out.print(" "+res[i]);
		}
		int[] avail=new int[nr];
		System.out.print("\nAvailable resource at the begining:");
		for(i=0;i<nr;i++){
			avail[i]=res[i];
		}
		for(i=0;i<nr;i++){
			System.out.print(" "+avail[i]);
		}
		System.out.print("\nAvailable Resource after allocation:");
		for(i=0;i<nr;i++){
			for(j=0;j<np;j++){
				avail[i]=avail[i]-alloc[j][i];
			}
		}
		for(i=0;i<nr;i++){
			System.out.print(" "+avail[i]);
		}
		safe_sequence(claim,alloc,avail,np,nr);
		System.out.print("\nClaim Matrix:");
		for(i=0;i<np;i++){
			System.out.print("\n");
			for(j=0;j<nr;j++){
				System.out.print(" "+claim[i][j]);
			}
		}
		System.out.print("\nAllocation Matrix :");
		for(i=0;i<np;i++){
			System.out.print("\n");
			for(j=0;j<nr;j++){
				System.out.print(" "+alloc[i][j]);
			}
		}
		System.out.print("\nSequence of allocation:");
		System.out.println(seq);
	}
	static int execute(int[] claim,int[] avail,int[] alloc,int nr){
		int i;
		for(i=0;i<nr;i++){
			if(claim[i]-alloc[i]>avail[i])
				return 0;
		}
		for(i=0;i<nr;i++){
			avail[i]=avail[i]+alloc[i];
			claim[i]=alloc[i]=0;
		}
		return 1;
	}
	static void safe_sequence(int[][] claim,int[][] alloc,int[] avail,int np,int nr){
		int [] visited=new int[np];
		int count=0;
		int i,j,k;
		for(i=0;i<np;i++){
			visited[i]=0;
		}
		for(;count<np;){
			for(i=0;i<np;i++){
				if(visited[i]==0 && execute(claim[i],avail,alloc[i],nr)==1 ){
					count++;
					visited[i]=1;
					System.out.print("\nNext Process:"+(i+1));
					seq.add(i+1);
					System.out.print("\nAvailable Resource");
					for(int t=0;t<nr;t++){
						System.out.print(" "+avail[t]);
					}
					System.out.print("\nClaim Matrix:");
					for(j=0;j<np;j++){
						System.out.print("\n");
						for(k=0;k<nr;k++){
							System.out.print(" "+claim[j][k]);
						}
					}
					System.out.print("\nAlllocation Matrix:");
					for(j=0;j<np;j++){
						System.out.print("\n");
						for(k=0;k<nr;k++){
							System.out.print(" "+alloc[j][k]);
						}
					}
					break;
				}
			}
		}
	}
}
