
public class MysteryMethods {
public static void main(String[] args) {

		System.out.println("Welcome to Mystery Methods!");
		char[] hmm = { 'e', 'n', 'i', 'r', 't', 'S', 'g', 'l', 'p', 'm', 'i', 'S' };
		System.out.print("Initial array: ");
		System.out.println(hmm);System.out.println(" \nMethod Outputs:");

		int idk = FIRST('e', hmm);

		System.out.println("First call of the first Method:");System.out.println(idk);

		int idk2 = FIRST('g', hmm);

		System.out.println("Second call of the first Method:");System.out.println(idk2);

		SecondMethod(idk, idk2, hmm);

		System.out.println("Call of the second Method:");System.out.println(hmm);

		hmm = third(hmm);
		System.out.println("Call of the third Method:");System.out.println(hmm);

		char[] x = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
				'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		int[] uhhh = fourth(x, hmm);

		System.out.println("Call of the fourth Method:");printInput(
				"Fourth Method Output:", uhhh);
}

	/**
	 * This method prints the description followed by the contents of list. The list
	 * begins with '[', ends with ']' and each element is separated with ', '.
	 * Example: printInput( "Initial array:", new int[]{1,2,3}) Initial array: [1, 2, 3]
	 * <p>
	 * @param description The text printed out before the list of ints.
	 * @param list        The array of ints to be printed.
	 */
	public static void printInput(String description, int[] list) {

		System.out.print(description);
		System.out.print(" [");

		for (int i = 0; i < list.length; i++) {
			
			if (i > 0) {
				System.out.print(", ");
			}
			System.out.print(list[i]);
		}
		System.out.println("]");
	}

	public static int FIRST(char ok, char[] okAgain) {

	for (int i = 0; i < okAgain.length; i++) {

		if (ok == okAgain[i]) {

	return i;
		}
		}

		return -1;
	    }
	public static void SecondMethod(int a, int b, char[] c) {

	if (a < 0 || b < 0 || c.length <= Math.max(a, b)) {return;}
	char temp = c[a];
	c[a] = c[b];
	c[b] = temp;
	}
	public static char[] third(char[] zz) {

	if (zz.length == 0) {return zz;}

	char[] oCL = new char[zz.length];

	for (int i = 0; i < zz.length; i++) {oCL[zz.length - i - 1] = zz[i];}return oCL;
	}
	public static int[] fourth(char[] in1, char[] in2) {

	if (in1.length == 0 || in2.length == 0) {return new int[in1.length];}

	int[] o1 = new int[in1.length];

	for (int i = 0; i < in1.length; i++) {for (int j = 0; j < in2.length; j++) {if
	(Character.toUpperCase(in2[j]) == Character.toUpperCase(in1[i])) {o1[i] += 1;}}}

	return o1;
}
}
