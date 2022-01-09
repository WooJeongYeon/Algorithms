import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * Date : 2022.01.09
 * Level : BaekJoon Gold 4
 * Difficulty : 중
 * Why : 버그 어려우...
 * URL : https://www.acmicpc.net/problem/17140
 * Select1 : 배열 돌려서 하던지 VS R, C일 때  VS R, C
 * Thinking : 100까지 + 1번 더 체크해야 함
 * Method : 순열, PQ
 * Error1 : pq를 얘를 매 시간마다 초기화하게 놨었음(매 행마다 해야 함)
 * Error2 : 
 * Result : 1시간 디버깅하뮤ㅠㅠ
 */
public class BJ17140_이차원배열과연산 {
	static int[][] arr;
	static int[] cntArr;
	static int r, c, k;
	static int time, R, C, newSize;
	static boolean isPossible;
	static PriorityQueue<Number> pq;
	static final int N = 100;
	static final int INPUT_N = 3;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>();
		arr = new int[N][N];
		for(int i = 0 ; i < INPUT_N ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0 ; j < INPUT_N ; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		R = INPUT_N; C = INPUT_N;
		
		if(arr[r][c] != k) {
			for(int i = 0 ; i < N ; i++) {
				time++;
				newSize = 0;
				int max = R >= C ? R : C;
				for(int n = 0 ; n < max ; n++) {
					cntArr = new int[N + 1];
					setCntArr(n);
					setPQ();
					for(int j = 0 ; j < N; j++) {
						if(R >= C) arr[n][j] = 0;
						else arr[j][n] = 0;
					}
					operate(n);
				}
				if(arr[r][c] == k) {
					isPossible = true;
					break;
				}
				if(R >= C) C = newSize;
				else R = newSize;
			}
			if(!isPossible) time = -1;
		}
		
		System.out.println(time);
	}
	private static void setCntArr(int n) {
		if(R >= C) {
			for(int i = 0 ; i < C ; i++) {
				if(arr[n][i] != 0) cntArr[arr[n][i]]++;
			}
		} else {
			for(int i = 0 ; i < R ; i++) {
				if(arr[i][n] != 0) cntArr[arr[i][n]]++;
			}
		}
	}
	private static void setPQ() {
		pq.clear();			// 얘를 매 시간마다 초기화하게 놨었음(그래도 틀리네...ㅠㅠㅠㅠ)
		for(int i = 1 ; i <= N ; i++) {
			if(cntArr[i] != 0) pq.offer(new Number(i, cntArr[i]));
		}
	}
	private static void operate(int n) {
		int idx = 0;
		if(R >= C) {
			while(!pq.isEmpty()) {
				Number number = pq.poll();
				arr[n][idx++] = number.num;
				arr[n][idx++] = number.cnt;
				if(idx == N) break;
			}
		} else {
			while(!pq.isEmpty()) {
				Number number = pq.poll();
				arr[idx++][n] = number.num;
				arr[idx++][n] = number.cnt;
				if(idx == N) break;
			}
		}
		newSize = Math.max(newSize, idx);
	}
	static class Number implements Comparable<Number>{
		int num, cnt;

		public Number(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
		
		public int compareTo(Number o) {
			if(this.cnt == o.cnt) return this.num - o.num;
			return this.cnt - o.cnt;
		}
	}
}
