package pass1;

public class SymbolTable 
{
	String Symbol;
	int Address;
	int Size;
	SymbolTable(String sym,int ad,int sz)
	{
		this.Symbol=sym;
		this.Address=ad;
		this.Size=sz;
	}
	@Override
	public String toString() {
		
		return "\n[" + Symbol + ",\t" + Address + ", \t" + Size + "]";
	}
	
}
