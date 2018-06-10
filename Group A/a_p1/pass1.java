package pass1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class pass1 
{
	static MOTTable mot[];
	static SymbolTable st[];
	static LiteralTable lt[];
	static int[] pt= {0,0,0,0};
	static ArrayList<IntermediateCode> ICode=new ArrayList<IntermediateCode>();
	static ArrayList<SymbolTable> ST=new ArrayList();
	static ArrayList<LiteralTable> LT=new ArrayList();
	static ArrayList<MOTTable> list=new ArrayList();
	static int iST=0,iLT=0,count=0,iIC=0,iPT=0;
	static String []token= {"abc","abc","abc","abc"};
	static int firstpoolcount=0;
	
	public static void main(String[] args) 
	{
		File file1 = new File("D:\\Users\\Sudarshan\\eclipse-workspace\\assemblerpass1\\src\\pass1\\MOT.text");
		File file2 = new File("D:\\Users\\Sudarshan\\eclipse-workspace\\assemblerpass1\\src\\pass1\\Input.text");
		Scanner sc1,sc2;
		Operations o1=new Operations();
		int i=0,index=0;
		MOTTable m1;
		String cs="=";
		int nMOT=27;		
		try
		{
			sc1 = new Scanner(file1);
			mot= new MOTTable[28];
			lt=new LiteralTable[10];
			while (sc1.hasNextLine())
			{
				String s1 = sc1.next();
				int s2 = Integer.parseInt(sc1.next());
				String s3 = sc1.next();				
				m1 = new MOTTable(s1, s2, s3);
				list.add(m1);				
			}
			//System.out.println(list);
			sc2 = new Scanner(file2);
			while (sc2.hasNextLine())
			{
				String line= sc2.nextLine();								
			    StringTokenizer st2 = new StringTokenizer(line," ");
				count=st2.countTokens();				
				i=0;
				while(st2.hasMoreTokens())
				{					 
					token[i]=st2.nextToken();
					if(token[i].contains(cs))
					{
						token[i]=token[i].substring(2,3);						
					}					
					i++; 
				}
				i=0;
				if(o1.searchMOT(token[i])==-1)
				{					
					if(o1.searchST(token[i])==-1)
					{				
						//o1.insertST(token[i],ICode.get(i).LC,0);
						o1.insertST(token[i],o1.LC,0);
					}
				}
				for(i=0;i<count;i++)
				{					
					if(Operations.flag1==1)
					{						
						Operations.flag1=0;
						break;
					}			
					index=o1.searchMOT(token[i]);
					//System.out.println("token[i]"+token[i]+"index="+index);
			        if(index==-1)
			        {
//			        	System.out.println("\n\t\tWrong Opcode");
			        	continue;
			        }
			        switch (list.get(index).type)
			        {
			        	case 1:o1.imperative();break;
			            case 2:o1.declarative(token[i-1],token[i],token[i+1]);break;
			            case 3:o1.directive(token[i],index,token[i+1]);break;	
			            default://System.out.println("In default case");
			            	break;
			        }					
				}
			}	
		}		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		System.out.println("**********Symbol Table***********");
		System.out.println("[Symbol"+"\t"+"Address"+"\t"+"Size]");
		System.out.println(ST);
		System.out.println("**********Literal Table***********");
		System.out.println("[Literal"+"\t"+"Address"+"]");
		System.out.println(LT);
		System.out.println("**********Pool Table***********");
		o1.printPoolTable();
		/*System.out.println("Index");
		for(i=0;i<=iPT;i++)
		{
			System.out.println("i="+i+" "+"pt[i]"+pt[iPT]);
		}*/
		o1.intermediate();
		
	}	
}
 class Operations
 {
	 int code1,type1,code2,type2,code3,type3,LC;
	 static int flag1=0;
	 int[] pt= {0,0,0,0};
	 int iPT=0;
	 pass1 p1=new pass1();	 
	 public void printPoolTable()
	 {
		 for(int k=0;k<p1.firstpoolcount;k++)
		 {
			// System.out.println("iLT "+p1.iLT);
			 System.out.println(" "+pt[k]);
		 }
	 }
	 public int searchMOT(String str)
	 {
		 int nMOT=27;
		 int j;				
		 for(j=0;j<=nMOT;j++)
		 {
			 if(str.equals(pass1.list.get(j).mnemonic))
			 {
				return j;
			 }
		 }
		 return -1;
	 }
	 	public int searchST(String str)
	 	{
	 		int k;	 		
	 		for(k=0;k<p1.iST;k++)
	 		{
	 				if(pass1.ST.get(k).Symbol.equals(str))
	 				{
	 					return k;
	 				}
	 		}
	 		return -1;
	 	}
	 	public int insertST(String str,int address,int size)
	 	{
	 		SymbolTable st1;
	 		pass1.st=new SymbolTable[10];
	 		st1 = new SymbolTable(str,address,size);
	 		pass1.ST.add(st1);
	 		//System.out.println(pass1.ST);
	 		p1.iST++;	 	
	 		return p1.iST-1;
	 	}
	 	public void directive(String str1,int in,String token2)
		 {
			 LiteralTable lt;
			 IntermediateCode IC;
			 int index;
			 
			 if(str1.equals("START"))
			 {
				 index=in;
				 code1=index;
				 type1=pass1.list.get(index).type;
				 code2=Integer.parseInt(token2);
				 type2=6;
				 LC=0;
				 type3=0;
				 code3=0;
				 //LC=code2;
				 IC=new IntermediateCode(LC,type1,type2,type3,code1,code2,code3);
				 pass1.ICode.add(IC);
				 LC=code2;
				 pass1.iIC++;
			 }		 
			 if(str1.equals("LTORG"))
			 {
				 int k;
				 pass1.firstpoolcount=p1.iLT;
				 for(k=0;k<p1.iLT;k++)
				 {				 
					 int Literal=pass1.LT.get(k).Literal;
					 //lt=new LiteralTable(Literal,LC);
					 pass1.LT.get(k).Address=LC;
					 //pass1.LT.add(lt);
					 //System.out.println("**Literal Table** "+lt);
					 type1=type2=type3=code3=0;
					 index=searchMOT("DC");
					 type1=p1.list.get(index).type;
					 code2=Literal;
					 type2=6;				 
					 code1=index;
					 IC=new IntermediateCode(LC,type1,type2,type3,code1,code2,code3);
					 LC++;
					 pass1.ICode.add(IC);
					 //LC=LC+code2;		
					 pass1.iIC++;
				 }			 
				 iPT++;
				 pt[iPT]=pass1.iLT;
				 //System.out.println("P1.PT "+pass1.pt[pass1.iPT]+"P1.iPT "+ pass1.iPT);
			 }
			 if(str1.equals("END"))
			 {
				 //System.out.println("P1.ILT "+p1.iLT);
				// System.out.println("FIRSTPOOLCOUNT "+pass1.firstpoolcount);
				 int lastpoolcount=(p1.iLT-pass1.firstpoolcount);
				// System.out.println("LASTPOOLCOUNT "+lastpoolcount);
				 int limit=pass1.firstpoolcount+lastpoolcount;
				 for(int k=pass1.firstpoolcount;k<limit;k++)
				 {				 
					 int Literal=pass1.LT.get(k).Literal;
					 //lt=new LiteralTable(Literal,LC);
					 pass1.LT.get(k).Address =LC;
					 //pass1.LT.add(lt);
					 index=searchMOT("DC");
					 type1=p1.list.get(index).type;		 	
					 type2=6;				 
					 code1=index;
					 code2=Literal;
					 IC=new IntermediateCode(LC,type1,type2,type3,code1,code2,code3);
					 pass1.ICode.add(IC);
					 pass1.iIC++;
				 }
				 // p1.iPT++;
				 //p1.pt[p1.iPT]=p1.iLT;

				 flag1=1;
			 }
		 }

	 public void imperative()
	 {
		 int j,tmp,index=0;
		 LiteralTable lt;	 
		 
		 for(j=0;j<p1.count;j++)
		 {			
			 tmp=searchST(pass1.token[j]);			
			 if(tmp!=-1)
			 {
				 	j++;				 	
			 }
			 index=searchMOT(pass1.token[j]);				
			 code1=index;
			 type1=pass1.list.get(index).type;			 
			 j++;
			 if(p1.count>1)
			 {
				 index=searchMOT(pass1.token[j]);
				 if(index!=-1)
				 {
					 code2=index;
					 type2=pass1.list.get(index).type;					 
					 if(p1.count==3)
					 {
						 j=j+1;
					 }					 
				 }
				 else
				 {
					 index=searchST(pass1.token[j]);
					 if(index==-1)
					 {
						 index=insertST(pass1.token[j],0,0);
						 code2=index;
						 type2=7;
					 }					 
					 if(p1.count==3)
					 {
						 j=j+1;
					 }
				 }
			 }
			 if(p1.count>2)
			 {				
				if(p1.count==4)
					j=j+1;
				if(isNumeric(p1.token[j]))
				{			
					int Address=0;
					int Literal=Integer.parseInt(p1.token[j]);
					lt=new LiteralTable(Literal,Address);
					pass1.LT.add(lt);					
					code3=p1.iLT;
					type3=8;
					j=j+2;					
					p1.iLT=p1.iLT+1;
				//	System.out.println("p1.iLT "+p1.iLT);
					break;
				}
				else
				{					
					index=searchST(pass1.token[j]);
					if(index==-1)
					index=insertST(pass1.token[j],0,0);
					code3=index;
					type3=7;
					j=j+2;				
					break;
				}					
			 }				
		 }
		 IntermediateCode IC=new IntermediateCode(LC,type1,type2,type3,code1,code2,code3);
		 pass1.ICode.add(IC);		 
		 LC++;
		 pass1.iIC++;
		 
	 }
	 public boolean isNumeric(String s) {  
		    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
		}  
	 public void declarative(String token0, String str1,String token2)
	 {		 
		 if(str1.equals("DC"))
		 {
			 type1=type2=type3=code3=0;
			 int index=searchMOT(str1);
			 code1=index;			 
			 type1=pass1.list.get(index).type;
			 type2=6;
			 code2=Integer.parseInt(token2);
			 index=searchST(token0);			 
			 pass1.ST.get(index).Address=LC;
			 pass1.ST.get(index).Size=1;			
			 IntermediateCode IC=new IntermediateCode(LC,type1,type2,type3,code1,code2,code3);
			 pass1.ICode.add(IC);			 
			 //System.out.println(IC);
			 pass1.iIC++;
			 LC++;
		 }
		 if(str1.equals("DS"))
		 {
			 type1=type2=type3=code3=0;			 
			 int index=searchMOT(str1);
			 code1=index;
			 type1=pass1.list.get(index).type;
			 type2=6;
			 code2=Integer.parseInt(token2);
			 index=searchST(token0);
			 pass1.ST.get(index).Address=LC;
			 pass1.ST.get(index).Size=Integer.parseInt(token2);
			 IntermediateCode IC=new IntermediateCode(LC,type1,type2,type3,code1,code2,code3);
			 pass1.ICode.add(IC);			 
			 LC=LC+pass1.ST.get(index).Size;
			 //System.out.println(IC);
			 //System.out.println(LC);
			 pass1.iIC++;
		 }
	 }
	 void intermediate()
	 {
		 int i;
		 String[] decode= {"","IS","DL","AD","RG","CC","C","S","L"};
		 System.out.println("*********INTERMEDIATE CODE********");
		 //System.out.println(pass1.ICode);
		 //System.out.println("iIC "+pass1.iIC);
		 for(i=0;i<pass1.iIC;i++)
		 {
			 if(pass1.ICode.isEmpty())
				 break;
			 System.out.print(pass1.ICode.get(i).LC+") "+"("+decode[pass1.ICode.get(i).Type1]+
				","+pass1.list.get((pass1.ICode.get(i).Code1)).opcode+")");
			 if(pass1.ICode.get(i).Type2!=0)
			 {
				 if((pass1.ICode.get(i).Type2) < 6)
				 {
					 System.out.print(" ("+decode[pass1.ICode.get(i).Type2] +","+
							 pass1.list.get((pass1.ICode.get(i).Code2)).opcode+") ");					 
				 }
				 else
				 {
					 System.out.print(" ("+decode[pass1.ICode.get(i).Type2] +","+
							 pass1.ICode.get(i).Code2+") ");
				 }				 
				 if((pass1.ICode.get(i).Type3)!=0)
				 {
					 System.out.print(" ("+decode[pass1.ICode.get(i).Type3] +","+
							 pass1.ICode.get(i).Code3+") ");
				 }
					System.out.println(""); 
			 }
		 }
		 
	 }
	  }
 