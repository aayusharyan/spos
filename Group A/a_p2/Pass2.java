package pass2;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Pass2 
{
	static ArrayList<SymbolTable> ST = new ArrayList();
	static ArrayList<LiteralTable> LT = new ArrayList();
	public static void main(String[] args) 
	{
		String []token= {"abc1","abc2","abc3","abc4","abc5","abc6"};
		int LC=0;
		try
		{
			Scanner sc=new Scanner(System.in);			
			System.out.println("\n\t\tEnter Symbol Table entries:");
			System.out.print("\n\t\tEnter total no of entries:");
			int no=Integer.parseInt(sc.next());
			System.out.println("NO:"+no);		
			for(int i=0;i<no;i++)
			{
				System.out.print("Enter the address of the symbol:");
				int ad=Integer.parseInt(sc.next());
				SymbolTable st= new SymbolTable(" ",ad,0);
				ST.add(st);
				//ST.get(i).Address = ad;			
			}
			System.out.println("***********Displaying input ST**********");
			System.out.println(ST);
			System.out.println("Enter Literal table entries:");
			System.out.print("Enter no of entries:");
			int literal_count=Integer.parseInt(sc.next());
			System.out.println("Literal_Count:"+literal_count);		
			for(int i=0;i<literal_count;i++)
			{
				System.out.print("Enter the address of the Literal:");
				int add=Integer.parseInt(sc.next());
				LiteralTable lt= new LiteralTable(0,add);
				LT.add(lt);
			//	LT.get(i).Address = add;			
			}
			System.out.println("***********Displaying input LT**********");
			System.out.println(LT);		
			System.out.print("Enter Source file name:");
			String fname=sc.next();//D:\\Users\\Sudarshan\\eclipse-workspace\\pass2\\src\\pass2\\input.txt
			//System.out.println(fname);		
			File file = new File(fname);
			sc=new Scanner(file);
			System.out.println("Getting tokens & printing source file");
			while(sc.hasNextLine())
			{
				String line=sc.nextLine();				
				//System.out.println("line:"+line);
				String[] linea=line.split("\\(|\\)|,|\\ ");
				line=" ";
				for(String a1:linea)
				{					
					line=line+" "+a1;
				}
				//System.out.println("line:"+line);
				StringTokenizer st=new StringTokenizer(line, " ");
				int i=0;
				int count=st.countTokens();
				//System.out.println("count:"+count);
				while(st.hasMoreTokens())
				{
					token[i]=st.nextToken();
					i++;					
				}
				//for(i=0;i<count;i++)
				//System.out.println(" "+token[i]);
				if(token[0].equals("AD")&& token[1].equals("01"))
				{
					LC=Integer.parseInt(token[3]);
					continue;
				}
				if(token[0].equals("IS"))
				{
					System.out.print(" "+LC+" "+token[1]);
					if(count==2)
					{
						System.out.print(" 00 000");
						LC=LC+1;
						continue;
					}
					if(count==4)
					{
						token[4]=token[2];
						token[5]=token[3];
						System.out.print(" 00");
					}
					else
					{
						System.out.print(" "+token[3]);
					}
					if(token[4].equals("S"))
					{
						System.out.println(" "+ST.get(Integer.parseInt(token[5])).Address);
					}
					else
					{
						System.out.print(" "+LT.get(Integer.parseInt(token[5])).Address+"\n");
					}
					LC=LC+1;
					continue;
				}
				if(token[0].equals("DL"))
				{
					System.out.print(" "+LC);
					if(token[1].equals("01"))
					{
						LC=LC+Integer.parseInt(token[3]);
						System.out.println(" "+token[1]+" "+"00 "+token[3]);
					}
					else
					{
						System.out.print(" "+token[1]+" 00 "+token[3]+"\n");
						LC=LC+1;
					}
				}
			}
		}catch(Exception e)
		{	
			
		}
	}
}