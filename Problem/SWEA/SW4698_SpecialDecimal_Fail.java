package day0810;

import java.util.Scanner;
/*
 * Date : 210810
 */
public class SW4698_SpecialDecimal_Fail {
	static int D;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final int TC = in.nextInt();
		for(int tc = 1 ; tc <= TC ; tc++) {
			D = in.nextInt();
			int A = in.nextInt();
			int B = in.nextInt();
			int result = 0;
			if(A == 1) A++;
			for(int i = A; i <= B ; i++) {
				boolean isDecimal = true;
				if(check(i)) {
					for(int j = 2 ; j <= (int)Math.sqrt(i) ; j++) {
						if(i % j == 0) {
							isDecimal = false;
						}
					}
					if(isDecimal) result++;
				}
			}
			System.out.println("#" + tc + " " + result);
		}
	}
	static boolean check(int n) {
		while(n > 0) {
			if(n % 10 == D)
				return true;
			n /= 10;
		}
		return false;
	}
}
