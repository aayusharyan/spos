package pass1;

public class IntermediateCode 
{
	int LC;
	int Type1, Code1;
	int Type2, Code2;
	int Type3, Code3;
	IntermediateCode(int LC, int Type1,int Type2,int Type3,int Code1,int Code2,int Code3)
	{
		this.LC=LC;
		this.Type1=Type1;
		this.Type2=Type2;
		this.Type3=Type3;
		this.Code1=Code1;
		this.Code2=Code2;
		this.Code3=Code3;
	}
	@Override
	public String toString() {
		return "\n [LC=" + LC + ", Type1=" + Type1 + ", Code1=" + Code1 + ", Type2=" + Type2 + ", Code2="
				+ Code2 + ", Type3=" + Type3 + ", Code3=" + Code3 + "]";
	}
}

