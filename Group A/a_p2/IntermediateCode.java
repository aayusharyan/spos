package pass2;

public class IntermediateCode 
{
	int LC;
	int type1;
	int code1;
	int type2;
	int code2;
	int type3;
	int code3;
	IntermediateCode(int l,int t1,int c1,int t2,int c2,int t3,int c3)
	{
		this.LC = l;
		this.type1 = t1;
		this.code1 = c1;
		this.type2 = t2;
		this.code2 = c2;
		this.type3 = t3;
		this.code3 = c3;
	}
	@Override
	public String toString() {
		return "\n [LC=" + LC + ", type1=" + type1 + ", code1=" + code1 + ", type2=" + type2 + ", code2="
				+ code2 + ", type3=" + type3 + ", code3=" + code3 + "]";
	}
	
}
