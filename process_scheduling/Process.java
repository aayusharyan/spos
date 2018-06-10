
public class Process {

	double AT,BT,FT,WT,TAT,UAT,CBT;
	int Priority;
	public Process(double A, double B, double F, double W, double T,double CBT, double UAT,int Priority)
	{
		this.AT = A;
		this.BT = B;
		this.FT = F;
		this.WT = W;
		this.TAT = T;
		this.CBT = B;
		this.UAT = A;
		this.Priority = Priority;
	}
	
	public String toString()
	{
		return "\n[AT=" + AT + ", BT=" + BT + ", FT=" + FT + ", WT=" + 
	WT + ", TAT=" + TAT + ", Priority=" + Priority +"]";
	}
}
