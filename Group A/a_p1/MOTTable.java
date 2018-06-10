package pass1;

import java.util.ArrayList;

public class MOTTable 
{
	String mnemonic;
	int type;
	String opcode;

	MOTTable(String m,int t,String o)
	{
		this.mnemonic=m;
		this.type=t;
		this.opcode=o;
	}
	public String toString() 
	{
		return "MOTTable [mnemonic=" + mnemonic + ", type=" + type + ", opcode=" + opcode + "]";
	}
}


