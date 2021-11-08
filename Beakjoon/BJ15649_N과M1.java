import java.util.Scanner;
/*
 * Date : 2021.11.09
 * Level : BaekJoon Sliver 3
 * Difficulty : 하하
 * Why : 순조부 연습
 * Time : 10분...!!
 * URL : https://www.acmicpc.net/status?user_id=wjygogogo&problem_id=15649&from_mine=1
 * Method : 순열
 * Result : 연습 좀 많이 해야겠다. 바로 생각이 안나더라 배열 2개 써야되는게...
 */
public class BJ15649_N과M1 {
	static int N, M;
	static int[] arr;
	static boolean[] visit;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		arr = new int[M];
		visit = new boolean[N];
		
		perm(0);
	}
	static void perm(int cnt) {
		if(cnt == M) {
			for(int i = 0 ; i < M ; i++) {
				System.out.print(arr[i]+ " ");
			}
			System.out.println();
			return;
		}
		for(int i = 0 ; i < N ; i++) {
			if(visit[i]) continue;
			arr[cnt] = i + 1;
			visit[i] = true;
			perm(cnt + 1);
			visit[i] = false;
		}
	}
}
