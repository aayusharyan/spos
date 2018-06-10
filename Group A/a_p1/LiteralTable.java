package pass1;

public class LiteralTable 
{
	int Literal;
	int Address;
	LiteralTable(int l,int A)
	{
		this.Literal=l;
		this.Address=A;
	}
	@Override
	public String toString() {
		return "\n[" + Literal + ", \t\t" + Address + "]";
	}

}
