package day0814;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/*
 * Date : 210814
 */
public class BJ3052_Remainder {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final int N = 10;
		Set<Integer> set = new HashSet<>();		// 중복을 제거하기 위해 set 사용
		for(int i = 0 ; i < N ; i++) {			// N개의 수에 대해
			set.add(in.nextInt() % 42);			// 42로 나눈 나머지를 set에 추가
		}
		System.out.println(set.size());			// 서로 다른 값의 나머지가 몇 개있는지 출력
	}
}
