
public class ValueOfUninitializedArray {
	public static void main(String args[]) {
		int array[][] = new int[2][3];

		
		int i = 0;
		boolean b;
		//int points = ++i;
		if(i == 0){;System.out.println("what happened");}
		System.out.println(array[0].length);
	}
}
