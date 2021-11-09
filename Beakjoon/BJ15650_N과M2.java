import java.util.Scanner;
/*
 * Date : 2021.11.10
 * Level : BaekJoon Sliver 3
 * Difficulty : 하하
 * Why : 순조부 연습
 * Time : 4분
 * URL : https://www.acmicpc.net/problem/15650
 * Method : 조합, 부분집합
 * Result : 연습 좀 많이 해야겠다. 바로 생각이 안나더라 배열 2개 써야되는게...
 */
public class BJ15650_N과M2 {
	static int N, M;
	static boolean[] choose;
	static int[] arr;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		choose = new boolean[N];
		arr = new int[M];
		
		//comb1(0, 0);
		comb2(0, 0);
	}
	static void comb1(int idx, int cnt) {
		if(cnt == M) {
			for(int i = 0 ; i < N ; i++) {
				if(choose[i]) System.out.print((i + 1) + " ");
			}
			System.out.println();
			return;
		}
		if(idx == N) {
			return;
		}
		choose[idx] = true;
		comb1(idx + 1, cnt + 1);
		choose[idx] = false;
		comb1(idx + 1, cnt);
	}
	
	static void comb2(int start, int cnt) {
		if(cnt == M) {
			for(int i = 0 ; i < M ; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		for(int i = start ; i < N ; i++) {
			arr[cnt] = i + 1;
			comb2(i + 1, cnt + 1);
		}
	}
}
