import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ17140_이차원배열과연산 {
	static int[][] arr;
	static int[] cntArr;
	static int r, c, k;
	static int time = 1, R, C, newSize;
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
				if(arr[r][c] == k) break;
				if(R >= C) C = newSize;
				else R = newSize;
				pq.clear();
				time++;
			}
		}
		
		if(time > 100) time = -1;
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
