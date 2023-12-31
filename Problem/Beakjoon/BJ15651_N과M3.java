import java.util.Scanner;
/*
 * Date : 2021.11.11
 * Level : BaekJoon Sliver 3
 * Difficulty : 하하
 * URL : https://www.acmicpc.net/problem/15651
 * Method : 중복순열
 * Result : 출력이 많아서 시간초과 나길래 StringBuilder 사용
 */
public class BJ15651_N과M3 {
	static int N, M;
	static int[] data;
	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		sb = new StringBuilder();
		N = in.nextInt();
		M = in.nextInt();
		data = new int[M];
		
		perm(0);
		
		System.out.println(sb);
	}
	
	static void perm(int cnt) {
		if(cnt == M) {
			for(int i = 0 ; i < M ; i++) {
				sb.append(data[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for(int i = 0 ; i < N ; i++) {
			data[cnt] = i + 1;
			perm(cnt + 1);
		}
	}
}
