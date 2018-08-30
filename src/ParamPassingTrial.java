
public class ParamPassingTrial {
	public static void main(String args[]) {
		int x[] = new int[2];
		
		incrementX(x);
		//System.out.println("x: " + x[]);
		
		for (int i=0; i<x.length; i++) {
			System.out.println("x: " + x[i]);
		}
		
	}
	
	private static void incrementX(int x[]) {
		x[0] = 1;
		x[1] = 2;
	}
}
