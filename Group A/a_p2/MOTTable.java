package pass2;

public class MOTTable 
{
	String mnemonic;
	int type;
	String opcode;
	MOTTable(String m,int t,String o)
	{
		this.mnemonic = m;
		this.type = t;
		this.opcode = o;	
	}
	@Override
	public String toString() {
		return "\n [mnemonic=" + mnemonic + ", type=" + type + ", opcode=" + opcode + "]";
	}
	
}
