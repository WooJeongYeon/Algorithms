package day0812;
// 조합 bit로 푸는거 진짜 비효율적

import java.util.Scanner;
/*
 * Date : 210812
 */
public class BJ3040_Dwarf7_BitMask {
	static int[] heights;	// 난쟁이 키
	static int N = 9;		// 총 난쟁이 수
	static int R = 7;		// 구하는 난쟁이 수
	static int ans;			// 난쟁이가 선택됬는지를 가지고있음
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();	
		heights = new int[N];
		for(int i = 0 ; i < N ; i++)	// 난쟁이 키를 저장
			heights[i] = in.nextInt();
		subset(0, 0, 0);		// 난쟁이를 구함
		for(int i = 0 ; i < N ; i++)
			if((ans & 1 << i) != 0)		// 해당 난쟁이가 선택되었다면
				sb.append(heights[i] + "\n");	// 키를 출력
		System.out.println(sb);
	}
	static void subset(int idx, int cnt, int flag) {		// 부분집합 방법으로 조합 구하기
		if(cnt == R) {		// 모든 난쟁이가 선택되었다면
			int sum = 0;
			for(int i = 0 ; i < N ; i++)	// 해당 난쟁이가 선택된경우 키를 더함
				if((flag & 1 << i) != 0)
					sum += heights[i];
			if(sum == 100) 					// 키가 100이면 이번 조합으로 선택
				ans = flag;
			return;
		}
		if(idx == N) {		// 모든 난쟁이를 검사했지만 7명이 선택되지 않았따면 반환
			return;
		}
		subset(idx + 1, cnt + 1, flag | 1 << idx);		// 이번 난쟁이 포함시킴
		subset(idx + 1, cnt, flag);						// 이번 난쟁이 포함안시킴
	}	
}