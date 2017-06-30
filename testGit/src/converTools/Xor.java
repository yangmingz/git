package converTools;
//异或
public class Xor {
	public static void main(String[] args) {
		int a=4;
		int b=6;
		int c = a^b;//c的值是a和b的异或。值为2.
		System.out.println(c);
		System.out.println(Integer.toHexString(c));
	}
}
