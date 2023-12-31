package day0805;

import java.math.BigInteger;
import java.util.Scanner;
/*
 * Date : 210805
 */
public class BJ1914_HanoiTower
{
	private static int num = 0;
	private static StringBuilder sb;
	public static void main(String args[])	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sb = new StringBuilder();
		if(n <= 20) {
			hanoiTower(n, 1, 2, 3);
			System.out.println(num);
			sb.deleteCharAt(sb.length() - 1);
			System.out.print(sb);
		}
		else {	// 큰 수 저장할 때는 BigInteger 사용하더라구
			BigInteger bigInt = new BigInteger("2");
			System.out.println(bigInt.pow(n).add(new BigInteger("-1")));
		}
    }
	private static void hanoiTower(int n, int start, int temp, int end) {
		if(n == 0) return;
		// n - 1개를 temp로 옮김
		hanoiTower(n - 1, start, end, temp);
		// n번째를 end로 옮김
		num++;
		sb.append(start + " " + end + "\n");
		// n - 1개를 end로 옮김
		hanoiTower(n - 1, temp, start, end);
	}
}