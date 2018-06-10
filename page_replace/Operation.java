import java.util.Scanner;

class Operation{
	 Scanner sc=new Scanner(System.in);
	 public int found(int[] frame,int fs,int page){
		 int i;
		 for(i=0;i<fs;i++){
			 if(frame[i]==page)
				 return 1;
		 }
		 return 0;
	 }
	 public int empty(int[] frame,int fs){
		 int i;
		 for(i=0;i<fs;i++){
			 if(frame[i]==99)
				 return i;
		 }
		 return -1;
	 }
	 public int max(int[] lrud,int fs){
		 int i,j=0;
		 for(i=1;i<fs;i++){
			 if(lrud[i]>lrud[j]){
				 j=i;
			 }
		 }
		 return j;
	 }
	 public void lru(int no,int[] stream){
		 int fs,i,j;
		 int hit=0,pf=0,loc;
		 System.out.print("\nEnter the size of Frame:");
		 fs=sc.nextInt();
		 int[] frame=new int[fs];
		 int[] lrud=new int[fs];
		 for(i=0;i<fs;i++){
			 frame[i]=99;
			 lrud[i]=0;
		 }
		 System.out.println("\nFrame \t Content");
		 System.out.print("\t");
		 for(i=0;i<fs;i++){
			 System.out.print("\t"+frame[i]);
		 }
		 for(i=0;i<no;i++){
			 if(found(frame,fs,stream[i])==0){
				 loc=empty(frame,fs);
				 if(loc!=-1){
					 frame[loc]=stream[i];
					 for(j=0;j<fs;j++)
						 lrud[j]++;
					 lrud[loc]=0;
				 }
				 else{
					 loc=max(lrud,fs);
					 frame[loc]=stream[i];
					 for(j=0;j<fs;j++)
						 lrud[j]++;
						 lrud[loc]=0;
				 }
			 }else{
				 hit=hit+1;
				 for(j=0;j<fs;j++){
					 if(frame[j]!=stream[i])
					 lrud[j]++;
					 else
						 lrud[j]=0;
				 }
			 }
			System.out.print("\n"+stream[i]+"\t");
			for(j=0;j<fs;j++){
				System.out.print(" "+frame[j]);
			}
		 }
		 System.out.println("\nPage hit:"+hit);
		 pf=no-hit;
		 System.out.println("Page Fault:"+pf);
	}
	 int futureD(int[] stream,int no,int begin,int page){
		 for(int i=begin+1;i<no;i++){
			 if(page==stream[i])
				 return(i-begin);
		 }
		 return(9999);
	 }
	 public void optimal(int no,int[] stream){
		 System.out.print("\nEnter frame size:");
		 int fs=sc.nextInt();
		 int i,j,pf=0,hit=0;
		 int[] frame=new int[fs];
		 int[] optd=new int[fs];
		 for(i=0;i<fs;i++){
			 frame[i]=99;
			 optd[i]=0;
		 }
		 System.out.println("Page\t Frame Content");
		 System.out.print("\t");
		 for(j=0;j<fs;j++){
			 System.out.print(" "+frame[j]);
		 }
         
		 for(i=0;i<no;i++){
			 if(found(frame,fs,stream[i])==0){
				 int loc=empty(frame,fs);
				 if(loc!=-1){
					 frame[loc]=stream[i];
				 }else{
					 loc=max(optd,fs);
					 frame[loc]=stream[i];
				 }
			 }else{
				 hit=hit+1;
			 }
			 for(int k=0;k<fs;k++){
				 optd[k]=futureD(stream,no,i,frame[k]);
			 }
			 System.out.print("\n"+stream[i]+"\t");
			 for(j=0;j<fs;j++){
				 System.out.print(" "+frame[j]);
			 }
		 }
		 pf=no-hit;
		 System.out.println("\nTotal number of page faults:"+pf);
	 }
}
