import java.util.Scanner;
/*
 * Date : 2021.12.22(재)
 * Level : Baekjoon Silver 2
 * URL : https://www.acmicpc.net/problem/17837
 * Thinking)
 * 		1. 재귀 사용
 * 		2. bitmask 사용
 * Method : 부분집합
 * Error1 : 1번방법에서 아무것도 선택 안하고 결과가 0나오는 경우도 있어서 따로 처리해줌
 * Result : 비트마스트로 푸는거 헷갈려서 어떻게 했었는지 복습좀 했다.
 * Pluts1 : 예전거 - 정렬 해놓고 마지막에 하지 않고 매번 선택할 때마다 S와 같거나 넘어간 경우 처리하고 리턴해주니까 시간 덜걸렸었네..
 */
public class BJ1182_부분수열의합_재 {
	static int N, S, ans, num;
	static int[] number;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		S = in.nextInt();
		number = new int[N];
		for(int i = 0 ; i < N ; i++) {
			number[i] = in.nextInt();
		}
		
		bitmask();
		//subset(0, 0, 0);
		System.out.println(ans);
	}
	
	static void bitmask() {
		for(int n = 1 ; n < 1 << N ; n++) {
			int sum = 0;
			for(int i = 0 ; i < N ; i++) {
				if((n & (1 << i)) != 0) sum += number[i];
			}
			if(sum == S) ans++;
		}
	}
	
	static void subset(int idx, int sum, int num) {
		if(idx == N) {
			if(sum == S && num > 0) ans++;
			return;
		}
		subset(idx + 1, sum + number[idx], num + 1);
		subset(idx + 1, sum, num);
	}
}
