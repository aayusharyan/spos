package pass2;

public class SymbolTable 
{
	String Symbol;
	int Address;
	int Size;
	SymbolTable(String sym,int A,int Sz)
	{
		this.Symbol = sym;
		this.Address = A;
		this.Size = Sz;
	}
	@Override
	public String toString() {
		return "\n [Symbol=" + Symbol + ", Address=" + Address + ", Size=" + Size + "]";
	}
	
}
