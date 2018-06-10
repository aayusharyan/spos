package pass2;

public class LiteralTable 
{
	int Literal;
	int Address;
	LiteralTable(int Literal, int A)
	{
		this.Literal = Literal;
		this.Address = A;
	}
	@Override
	public String toString() {
		return "\n [Literal=" + Literal + ", Address=" + Address + "]";
	}

}
